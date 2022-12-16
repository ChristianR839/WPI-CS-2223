import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

public class ClosedHashing {

    private final Hashtable<Integer, HashData> hashTable = new Hashtable<>();

    private final int c;
    private final int m;

    public ClosedHashing(int c, int m) {
        this.c = c;
        this.m = m;
    }

    private boolean hashTableContains(String word) {
        int h = hashFcn(word);
        while (hashTable.containsKey(h)) {
            if (hashTable.get(h).word.equals(word)) {
                return true;
            }
            h += 1;
        }
        return false;
    }

    private int hashFcn(String word) {
        int h = 0;
        int s = word.length();

        // The algorithm
        for (int i = 0; i < s; i++) {
            h = (h * c + (int) word.charAt(i)) % m;
        }

        return h;
    }

    private void hashWord(String word) {
        // Remove all non-ASCII and punctuation (except ' and -)
        word = word.replaceAll("[^\\x00-\\x7F]", "").replaceAll("[\\p{Punct}&&[^'-]]+", "");
        if (word.equals("") || word.matches("\\p{Punct}") || word.startsWith("-") || word.endsWith("-")) return;

        // If the word isn't already in the table...
        if (!hashTableContains(word)) {

            // Determine the hash value for the word, put it in table (class field)
            int h = hashFcn(word);

            HashData hData = new HashData(h, -1, word);

            // Find the next available key, if data exists in key h
            while (hashTable.containsKey(h)) {
                h += 1;
                // Account for wrap-around
                if (h == m) h = 0;
            }

            hData.key = h;

            // Put the data in the table
            hashTable.put(h, hData);
        }
    }

    public void hashAllWords() {

        try {
            // Setup file reading
            InputStream fis = new FileInputStream("EdgarAllanPoeBellsB2022groomed.txt"); // EdgarAllanPoeBellsB2022groomed.txt RavenD2020.txt
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.US_ASCII);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (String s : words) {
                    // Hash each word individually
                    hashWord(s);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printTable() {

        System.out.println("Key  Word             Value");
        System.out.println("---------------------------");

        for (int i = 0; i < m; i++) {
            if (hashTable.containsKey(i)) {
            System.out.format("%-5s", hashTable.get(i).key);
            System.out.format("%-17s", hashTable.get(i).word);
            System.out.print(hashTable.get(i).value);
            System.out.println();
            }
            else {
                System.out.format("%-5s", i);
                System.out.format("%-17s", -1);
                System.out.println();
            }
        }

        System.out.println();
    }

    public void hashingQuestions() {
        System.out.println("a) How many non-empty addresses are there in the table?\n" +
                "What does that make the load factor, α, for our table?\n");

        emptyAddresses();

        System.out.println("b) What is the longest empty area in the table, and where is it?\n");

        longestEmpty();

        System.out.println("c) What is the longest (largest) cluster in the table, and where is it?\n" +
                "Note: It might wrap from the end of the table back to the beginning.\n");

        longestCluster();

        System.out.println("d) What hash value results from the greatest number of distinct words,\n" +
                "and how many words have that hash value?\n");

        greatestDistinct();

        System.out.println("e) What word is placed in the table farthest from its actual hash value,\n" +
                "and how far away is it from its actual hash value?\n");

        farthestAway();
    }

    private void emptyAddresses() {
        int count = 0;

        for (int i = 0; i < m; i++) {
            if (!hashTable.containsKey(i)) count += 1;
        }

        float loadFactor = (m - (float) count) / m;

        System.out.println("\tThere are " + count + " empty addresses in our table.");
        System.out.println("\tThis means that our load factor is " + loadFactor + ".\n");
    }

    private void longestEmpty() {
        int count = 0;
        int highest = 0;
        int highestKey = 0;

        for (int i = 0; i < m; i++) {
            if (!hashTable.containsKey(i)) count += 1;
            else {
                if (count > highest) {
                    highest = count;
                    highestKey = i;
                }
                count = 0;
            }
        }

        System.out.println("\tThe longest empty area in the table is " + highest + " keys long.\n" +
                "\tIt can be found from keys " + (highestKey - highest) + " to " + (highestKey - 1) + " (inclusive).\n");
    }

    private void longestCluster() {
        int count = 0;
        int highest = 0;
        int highestKey = 0;

        for (int i = 0; i < m; i++) {
            if (hashTable.containsKey(i)) count += 1;
            else {
                if (count > highest) {
                    highest = count;
                    highestKey = i;
                }
                count = 0;
            }
            if (highestKey == m-1) i = 1;
        }

        System.out.println("\tThe longest cluster in the table is " + highest + " keys long.\n" +
                "\tIt can be found from keys " + (highestKey - highest) + " to " + (highestKey - 1) + " (inclusive).\n");
    }

    private void greatestDistinct() {
        int[] valueCounter = new int[m];

        for (int i = 0; i < m; i++) {
            if (hashTable.containsKey(i)) {
                valueCounter[hashTable.get(i).value] += 1;
            }
        }

        int highest = 0;
        int value = 0;
        for (int i = 0; i < m; i++) {
            if (valueCounter[i] > highest) {
                highest = valueCounter[i];
                value = hashTable.get(i).value;
            }
        }

        System.out.println("\tThe hash value of " + value + " results in the greatest number of distinct words.\n" +
                "\t" + highest + " words have this hash value.\n");
    }

    private void farthestAway() {
        String word = "";
        int value = 0;
        int temp = 0;

        for (int i = 0; i < m; i++) {
            if (hashTable.containsKey(i)) {
                temp = hashTable.get(i).key - hashTable.get(i).value;
                if (temp > value) {
                    value = temp;
                    word = hashTable.get(i).word;
                }
            }
        }

        System.out.println("\tThe word '" + word + "' is placed farthest from its actual hash value.\n" +
                "\tIt is " + value + " keys away.");
    }
}
