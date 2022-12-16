import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("HOMEWORK 6");
        System.out.println("CHRISTIAN RUA");
        System.out.println();

        Board b = new Board();

        int[] set0 = {0, 0, 0, 0, 0, 0, 0, 0}; // True
        int[] set1 = {1, 6, 8, 3, 7, 4, 2, 5}; // True
        int[] set2 = {1, 6, 8, 3, 7, 0, 0, 0}; // True
        int[] set3 = {1, 6, 8, 3, 5, 0, 0, 0}; // False
        int[] set4 = {1, 6, 8, 3, 7, 0, 2, 0}; // False (invalid)
        int[] set5 = {8, 8, 8, 8, 8, 8, 8, 8}; // False (invalid)

        System.out.println("PART 1: isLegalPosition");
        System.out.println(Arrays.toString(set1) + " : " + b.isLegalPosition(set1, 8));
        System.out.println(Arrays.toString(set2) + " : " + b.isLegalPosition(set2, 8));
        System.out.println(Arrays.toString(set3) + " : " + b.isLegalPosition(set3, 8));
        System.out.println(Arrays.toString(set4) + " : " + b.isLegalPosition(set4, 8));

        System.out.println();

        System.out.println("PART 2: nextLegalPosition");
        System.out.println(Arrays.toString(set0) + " -> " + Arrays.toString(b.nextLegalPosition(set0, 8))); // {1 0 0 0 0 0 0 0}
        System.out.println(Arrays.toString(set1) + " -> " + Arrays.toString(b.nextLegalPosition(set1, 8))); // {1 6 8 5 0 0 0 0}
        System.out.println(Arrays.toString(set2) + " -> " + Arrays.toString(b.nextLegalPosition(set2, 8))); // {1 6 8 3 7 4 0 0}
        System.out.println(Arrays.toString(set3) + " -> " + Arrays.toString(b.nextLegalPosition(set3, 8))); // {1 6 8 3 7 0 0 0}
        System.out.println(Arrays.toString(set4) + " -> " + Arrays.toString(b.nextLegalPosition(set4, 8))); // {1 6 8 3 7 0 2 0} (invalid)
        System.out.println(Arrays.toString(set5) + " -> " + Arrays.toString(b.nextLegalPosition(set5, 8))); // {-1 0 0 0 0 0 0 0}

        System.out.println();

        System.out.println("PART 3: firstSolutions");
        b.firstSolutions(14);

        System.out.println();

        System.out.println("PART 4: allSolutions");
        b.allSolutionsBatch(12);

        System.out.println("\nSee Canvas submission for an output that fulfills Bonus requirements.");
    }
}
