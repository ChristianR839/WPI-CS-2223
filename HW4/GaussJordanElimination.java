import java.math.RoundingMode;
import java.text.DecimalFormat;

public class GaussJordanElimination {
    float[][] system = {
            {1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  364},
            {1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4},
            {0,  0,  1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  16},
            {0,  0,  0,  0,  1,  1,  0,  0,  0,  0,  0,  0,  36},
            {0,  0,  0,  0,  0,  0,  1,  1,  0,  0,  0,  0,  64},
            {0,  0,  0,  0,  0,  0,  0,  0,  1,  1,  0,  0,  100},
            {1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  79},
            {0,  0,  1,  0,  0,  0,  0,  0,  0,  1,  0,  0,  61},
            {0,  0,  0,  0,  0,  4, -3,  0,  0,  0,  0,  0,  0},
            {0,  0,  0,  3, -2,  0,  0,  0,  0,  0,  0,  0,  0},
            {0,  0,  0,  1,  0,  0,  0,  0,  1, -1,  0,  0,  0},
            {1, -1,  1, -1,  1, -1,  1, -1,  1, -1,  1, -1, -42}
    };

    // x = 1, y = 2, z = 3
    float[][] example1 = {
            {1,  1,  1,  6},
            {1,  1,  2,  9},
            {1,  2,  3,  14}
    };

    // x = 1, y = 2, z = 3
    float[][] example2 = {
            {1,  1,  1,  6},
            {1,  1,  2,  9},
            {2,  2,  3,  15}
    };

    public GaussJordanElimination() {

    }

    public void answerQuestions() {
        System.out.println("PART 1: FORWARD ELIMINATION");
        System.out.println();
        printMatrix(example1);
        System.out.println("The ForwardElimination method fails here because there is no way to avoid A[i][i] == 0.\n" +
                "This algorithm has no way of computing a solution if such a scenario occurs.\n" +
                "The textbook provides more context as to why this happens: If A[i][i] == 0, then we cannot\n" +
                "obtain divide by 0 and return a row that exists. The BetterForwardElimination algorithm\n" +
                "remedies this by exchanging the ith row with one below it such that A[new i][new i] != 0.");
        System.out.println();
        System.out.println("PART 2: BETTER FORWARD ELIMINATION");
        System.out.println();
        printMatrix(example2);
        System.out.println("Somewhat similarly, the BetterForwardElimination algorithm fails here because, when\n" +
                "going through row operations, it creates a row [0, 0, 0, x] such that x is an element of the reals.\n" +
                "Such a row implies that 0 = x, which is not possible and, as a result, returns some of the values\n" +
                "as NaN (not a number).");
        System.out.println();
    }

    private void printMatrix(float[][] matrix) {
        DecimalFormat f = new DecimalFormat("#0.0");
        for (float[] floats : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                float val = floats[j];
                if (val > -0.0001 && val < 0.0001) val = 0f;
                System.out.format("%7s", f.format(val));
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printSolutions(float[] arr) {
        DecimalFormat f = new DecimalFormat("#0");
        for (int i = 0; i < arr.length; i++) {
            float val = arr[i];
            if (val > -0.0001 && val < 0.0001) val = 0f;
            System.out.print("x" + (i+1) + " = ");
            System.out.print(f.format(val));
            if (i < arr.length-1) System.out.print(",  ");
        }
        System.out.println("\n");
    }

    private void swapVals(float[][] matrix, int srcX, int srcY, int destX, int destY) {
        float destVal = matrix[destX][destY];
        float srcVal = matrix[srcX][srcY];
        matrix[destX][destY] = srcVal;
        matrix[srcX][srcY] = destVal;
    }

    private void swapRows(float[][] matrix, int srcRow, int destRow) {
        float[] dest = matrix[destRow];
        float[] src = matrix[srcRow];
        matrix[destRow] = src;
        matrix[srcRow] = dest;
    }

    private void multRows(float[][] matrix, int row, float coeff) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] *= coeff;
        }
    }

    private void addRows(float[][] matrix, int srcRow, int destRow, int coeff) {
        for (int i = 0; i < matrix[destRow].length; i++) {
            matrix[destRow][i] += (matrix[srcRow][i] * coeff);
        }
    }

    public float[][] betterForwardElimination(float[][] matrix) {
        float n = matrix[0].length-1;
        int pivotrow;
        float temp;

        for (int i = 0; i < n; i++) {
            pivotrow = i;
            for (int j = i+1; j < n; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[pivotrow][i])) {
                    pivotrow = j;
                }
            }
            for (int k = i; k < n+1; k++) {
                swapVals(matrix, i, k, pivotrow, k);
            }
            for (int j = i+1; j < n; j++) {
                temp = matrix[j][i] / matrix[i][i];
                for (int k = i; k < n+1; k++) {
                    matrix[j][k] = matrix[j][k] - (matrix[i][k] * temp);
                }
            }
        }
        return matrix;
    }

    public float[] gaussJordanElimination(float[][] matrix) {

        System.out.println("Starting matrix:");
        printMatrix(matrix);

        float[][] matrixUT = betterForwardElimination(matrix);

        System.out.println("After BetterForwardElimination:");
        printMatrix(matrixUT);

        float n = matrixUT[0].length-1;
        float r;

            for (int i = 0; i < n; i++) {
                if (matrixUT[i][i] == 0) {
                    System.out.println("System is unsolvable, matrix is inconsistent.");
                    break;
                }
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        r = matrixUT[j][i] / matrixUT[i][i];
                        for (int k = 0; k <= n; k++) {
                            matrixUT[j][k] = matrixUT[j][k] - (r * matrixUT[i][k]);
                        }
                    }
                }
            }

            int nInt = matrixUT[0].length-1;
            float[] solutions = new float[nInt];

            for (int i = 0; i < matrixUT.length; i++) {
                multRows(matrixUT, i, (1/matrixUT[i][i]));
                solutions[i] = matrixUT[i][nInt];
            }

            System.out.println("After Gauss-Jordan Elimination:");
            printMatrix(matrixUT);

            System.out.println("Solutions:");
            printSolutions(solutions);

            return solutions;
    }
}