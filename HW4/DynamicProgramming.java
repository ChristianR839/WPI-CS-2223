import java.text.DecimalFormat;
import java.util.Arrays;

public class DynamicProgramming {

    int[][] room = {
    // Vault 1   2   3   4   5   6   7   8
            {35, 89, 52, 66, 82, 20, 95, 21}, // Row 1
            {79, 5,  14, 23, 78, 37, 40, 74}, // Row 2
            {32, 59, 17, 25, 31, 4,  16, 63}, // Row 3
            {91, 11, 77, 48, 13, 71, 92, 15}, // Row 4
            {56, 70, 47, 64, 22, 88, 67, 12}, // Row 5
            {83, 97, 94, 27, 65, 51, 30, 7},  // Row 6
            {10, 41, 1,  86, 46, 24, 53, 93}, // Row 7
            {96, 33, 44, 98, 75, 68, 99, 84}  // Row 8
    };

    public DynamicProgramming() {

    }

    private void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.format("%7s", ints[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Output:
    //   Starting square
    //   Representation of path
    //   Total number of gems
    //   Ending square
    public void mostPreciousPath(int[][] matrix) {
        System.out.println("The Treasure Room:");
        System.out.println("NOTE: Top left = (1, 1) | Bottom right = (8, 8)");
        printMatrix(room);

        int[][] optimized = optimizedSums(matrix);
        int[] path = findPath(matrix, optimized);
        printPath(path);

        printGems(path, optimized);
    }

    public int[][] optimizedSums(int[][] matrix) {

        int[][] optimized = new int[matrix.length][matrix[0].length];

        for (int r = 0; r < matrix.length-1; r++) {
            for (int v = 0; v < matrix[0].length; v++) {
                if (r == 0) {
                    optimized[r][v] = matrix[r][v];
                }
                if (v > 0) {
                    if (matrix[r+1][v-1] + optimized[r][v] > optimized[r+1][v-1]) {
                        optimized[r+1][v-1] = matrix[r+1][v-1] + optimized[r][v];
                    }
                }
                if (v < 7) {
                    if (matrix[r+1][v+1] + optimized[r][v] > optimized[r+1][v+1]) {
                        optimized[r+1][v+1] = matrix[r+1][v+1] + optimized[r][v];
                    }
                }
                if (matrix[r+1][v] + optimized[r][v] > optimized[r+1][v]) {
                    optimized[r+1][v] = matrix[r+1][v] + optimized[r][v];
                }
            }
        }

        System.out.println("After finding optimized sums for each space:");
        printMatrix(optimized);

        return optimized;
    }

    // Returns index
    public int greatestInRow(int[] row) {
        int index = 0;
        int temp = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] > temp) {
                temp = row[i];
                index = i;
            }
        }
        return index;
    }

    public int[] findPath(int[][] orig, int[][] optimized) {
        int[] path = new int[optimized.length];
        int v = greatestInRow(optimized[optimized.length-1]);

        path[optimized.length-1] = v;

        int dif;
        for (int r = optimized.length-1; r > 0; r--) {
            dif = optimized[r][v] - orig[r][v];
            if (v > 0) {
                if (optimized[r-1][v-1] == dif) {
                    path[r-1] = v-1;
                    v -= 1;
                }
            }
            if (v < 7) {
                if (optimized[r-1][v+1] == dif) {
                    path[r-1] = v+1;
                    v += 1;
                }
            }
            if (optimized[r-1][v] == dif) {
                path[r-1] = v;
            }
        }

        return path;
    }

    public void printPath(int[] path) {
        System.out.println("Most Precious Path:");
        System.out.print("Start");
        for (int i = 0; i < path.length; i++) {
            System.out.print(" -> (" + (i+1) + ", " + (path[i]+1) + ")");
        }
        System.out.println();
    }

    public void printGems(int[] path, int[][] optimized) {
        int v = path[path.length-1];
        int gems = optimized[optimized.length-1][v];
        System.out.println("Upon reaching the door to vault " + (v+1) + ", Bilbo has picked up " + gems + " gems!");
    }
}
