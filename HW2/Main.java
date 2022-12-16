import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        LucasNumber lucasNumber = new LucasNumber();
        SubirachsSquare subirachsSquare = new SubirachsSquare();
        int maxIndex = 40;
        TimedCalculation[] tCalcArr = new TimedCalculation[maxIndex+2];
        TimedCalculation[] tCalcArrCustom = new TimedCalculation[maxIndex+2];

        System.out.println("CHRISTIAN RUA");
        System.out.println("CS 2223 HOMEWORK 2: RECURRENCES, LUCAS NUMBERS, & BRUTE FORCE / EXHAUSTIVE SEARCH\n");
        System.out.println("PART 1: LUCAS NUMBERS\n");

        System.out.println("Calculating Lucas Numbers up to " + maxIndex + "...");
        System.out.println("KEY <n>: <Lucas Number>    [<elapsed time> ms]    V: <value ratio>    T: <time ratio>");

        // Calculate Lucas Numbers up to maxIndex
        for (int i = 0; i <= maxIndex+1; i++) {
            tCalcArr[i] = lucasNumber.findTimed(i);
        }

        // Prints results & ratios
        for (int j = 0; j <= maxIndex; j++) {
            System.out.print(j + ": " + tCalcArr[j].value + "\t\t\t");
            System.out.print("[" + (tCalcArr[j].elapsed / 1E6) + " ms]\t\t\t");

            System.out.print("V: " + lucasNumber.valueRatio(tCalcArr[j], tCalcArr[j+1]) + "\t\t\t");
            System.out.print("T: " + lucasNumber.timeRatio(tCalcArr[j], tCalcArr[j+1]) + "\n");
        }

        System.out.println("\nTime and value increase at (more-or-less) a rate of 1.618034 (the Golden Ratio).");
        System.out.println("This means the order of growth is exponential: n^1.618034");
        System.out.println("This is interesting, as it corresponds with the same growth as the Fibonacci Sequence.\n");

        System.out.println("Custom Sequence: N(0) = 7, N(1) = 22");
        System.out.println("Calculating Custom Numbers up to " + maxIndex + "...");
        System.out.println("KEY <n>: <Custom Number>    [<elapsed time> ms]    V: <value ratio>    T: <time ratio>");

        // Calculate Custom Numbers up to maxIndex
        for (int k = 0; k <= maxIndex+1; k++) {
            tCalcArrCustom[k] = lucasNumber.findTimedCustom(k);
        }

        // Prints results & ratios
        for (int l = 0; l <= maxIndex; l++) {
            System.out.print(l + ": " + tCalcArrCustom[l].value + "\t\t\t");
            System.out.print("[" + (tCalcArrCustom[l].elapsed / 1E6) + " ms]\t\t\t");

            System.out.print("V: " + lucasNumber.valueRatio(tCalcArrCustom[l], tCalcArrCustom[l+1]) + "\t\t\t");
            System.out.print("T: " + lucasNumber.timeRatio(tCalcArrCustom[l], tCalcArrCustom[l+1]) + "\n");
        }

        System.out.println("\nIt appears as if the growth of my Custom Numbers is exactly the same as the Lucas Numbers.");
        System.out.println("The Golden Ratio is kinda neat! I wonder what it is about this type of calculation that causes it to appear?");

        System.out.println("\nPART 2: SUBIRACHS SQUARE");

        System.out.println("\nNumber of 4-element combinations that have the same sum as the rows & columns: "
                + subirachsSquare.rowSumCount(4));
        System.out.println("Number of all possible combinations with this sum: "
                + subirachsSquare.rowSumCountAll());
        System.out.println("Distribution of all possible sums (0 to 132): "
                + Arrays.toString(subirachsSquare.countAllSum()));

        int[] greatestSumCount = subirachsSquare.greatestSumCount();
        System.out.println("\nSum with the greatest number of combinations: " + greatestSumCount[0]
                + " (" + greatestSumCount[1] + " combinations)");
        System.out.println("This number is interesting because it is exactly double the sum that every row & column has (33).");
    }
}