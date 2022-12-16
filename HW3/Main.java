import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        System.out.println("HOMEWORK 3");
        System.out.println("Christian Rua");
        System.out.println();

        // Part 1: Palindromes
        System.out.println("PART 1: Palindromes");
        System.out.println();

        Palindromes p = new Palindromes();

        System.out.println("abcdefedcba : " + p.palindromecheck("abcdefedcba"));
        System.out.println("notapalindrome : " + p.palindromecheck("notapalindrome"));

        // Bonus inputs
        System.out.println("never odd or even : " + p.palindromecheck("never odd or even"));
        System.out.println("Able was I ere I saw Elba. : " + p.palindromecheck("Able was I ere I saw Elba."));
        System.out.println("A man, a plan, a canal: Panama! : " + p.palindromecheck("A man, a plan, a canal: Panama!"));

        System.out.println();

        // Part 2: Inversions
        System.out.println("PART 2: Inversions");
        System.out.println();

        Inversions i = new Inversions();

        int[] set1 = {3, 2, 1};
        int[] set2 = {5, 4, 3, 2, 1};
        int[] set3 = {0, 1, 2, 3, 4};
        int[] set4 = {1, 4, 281, 7, 8};
        int[] set5 = {0};
        int[] set6 = {1, 2, 4, 5, 6, 3, 7, 9, 8};
        int[] set7 = {1, 2, 4, 5, 6, 3, 7, 9, 8, 10};

        System.out.println("Finding inversions with EASY method [O(n^2), nested FOR loops]:");
        System.out.println(Arrays.toString(set1) + " has " + i.easyinversioncount(set1) + " inversions");
        System.out.println(Arrays.toString(set2) + " has " + i.easyinversioncount(set2) + " inversions");
        System.out.println(Arrays.toString(set3) + " has " + i.easyinversioncount(set3) + " inversions");
        System.out.println(Arrays.toString(set4) + " has " + i.easyinversioncount(set4) + " inversions");
        System.out.println(Arrays.toString(set5) + " has " + i.easyinversioncount(set5) + " inversions");
        System.out.println(Arrays.toString(set6) + " has " + i.easyinversioncount(set6) + " inversions");
        System.out.println(Arrays.toString(set7) + " has " + i.easyinversioncount(set7) + " inversions");

        System.out.println();

        System.out.println("Finding inversions with FAST method [O(n log n), MergeSort w/ counter]:");
        System.out.println(Arrays.toString(set1) + " has " + i.fastinversioncount(set1) + " inversions");
        System.out.println(Arrays.toString(set2) + " has " + i.fastinversioncount(set2) + " inversions");
        System.out.println(Arrays.toString(set3) + " has " + i.fastinversioncount(set3) + " inversions");
        System.out.println(Arrays.toString(set4) + " has " + i.fastinversioncount(set4) + " inversions");
        System.out.println(Arrays.toString(set5) + " has " + i.fastinversioncount(set5) + " inversions");
        System.out.println(Arrays.toString(set6) + " has " + i.fastinversioncount(set6) + " inversions");
        System.out.println(Arrays.toString(set7) + " has " + i.fastinversioncount(set7) + " inversions");

        System.out.println();

        // Part 3: Gray Codes
        System.out.println("PART 3: Gray Codes");
        System.out.println();

        GrayCodes g = new GrayCodes();

        System.out.println("[n = 4]");
        g.graycodesarefun(4);
        System.out.println();

        System.out.println("[n = 5]");
        g.graycodesarefun(5);
        System.out.println();

        System.out.println("[n = 6]");
        g.graycodesarefun(6);

        System.out.println("                                                             ^");
        System.out.println("This order right here...                              ABACABADABACABA!");
    }
}
