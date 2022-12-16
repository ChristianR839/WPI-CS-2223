import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    public Board() {
    }

    public void allSolutionsBatch(int n) {

        Instant instant = Instant.now();

        for (int i = 4; i <= n; i++) {
            allSolutions(i);
        }

        System.out.println("Total elapsed time (ms): " + (Instant.now().toEpochMilli() - instant.toEpochMilli()));
    }

    public void allSolutions(int n) {

        Instant instant = Instant.now();
        Instant loop;

        // For debugging, set to true to see all solutions
        boolean print = false;

        // Create a new board
        int[] board = new int[n];

        // Setup variables
        boolean searching = true;
        int count = 0;

        // Begin searching for all saturated legal positions
        while (searching) {

            loop = Instant.now();

            // Keep finding the next legal position until the board is saturated
            do {
                board = nextLegalPosition(board, n);
            } while (isUnsaturated(board));

            // If the flag has been set by successor()
            if (board[0] == -1) {
                searching = false;
            } else {

                // Increase count (easier to read printouts)
                count += 1;

                if (print) {
                    // Print solution
                    System.out.format("%-5s", count + ":");
                    System.out.print(Arrays.toString(board) + " : ");
                    System.out.println((Instant.now().toEpochMilli() - loop.toEpochMilli()) + "ms");
                }
            }
        }
        System.out.printf("There are %d solutions to the %d-Queens Problem (", count, n);
        System.out.println((Instant.now().toEpochMilli() - instant.toEpochMilli()) + "ms)");
    }

    public void firstSolutions(int n) {

        Instant instant = Instant.now();
        Instant loop;

        // Find the first solution for each board of size n
        for (int i = 4; i <= n; i++) {

            // Get loop timestamp
            loop = Instant.now();

            // Create a new board
            int[] board = new int[i];

            // Keep finding the next legal position until the board is saturated
            do {
                board = nextLegalPosition(board, i);
            } while (isUnsaturated(board));

            // Print solution (saturated legal position)
            System.out.format("%-7s", "n(" + i + "):");
            System.out.print(Arrays.toString(board) + " (");
            System.out.println((Instant.now().toEpochMilli() - loop.toEpochMilli()) + "ms)");
        }

        System.out.println("Total elapsed time (ms): " + (Instant.now().toEpochMilli() - instant.toEpochMilli()));
    }

    private boolean isUnsaturated(int[] board) {

        // If any of the values in board are zero, it's not a complete (saturated) solution
        for (int i : board) {
            if (i == 0) return true;
        }

        return false;
    }

    public int[] nextLegalPosition(int[] board, int n) {

        // Before anything, make sure the input is valid (there are no zeros that are not tailing nonzero values)
        if (invalidInput(board, n)) return board;

        // Keep looking for the next legal position until it's found
        do {
            board = successor(board, n);
        } while (!isLegalPosition(board, n));

        return board;
    }

    public int[] successor(int[] board, int n) {

        // What index (from the right) is the first non-zero value?
        int k = n-1;
        for (int i = n-1; i > -1; i--) {
            if (board[i] != 0) break;
            k = i-1;
        }

        if (k == -1) {
            board[0] += 1;
            return board;
        }

        if (isLegalPlacement(board) && k < n-1) {
            k += 1;
        }

        // Is the value at k at the max value? To the left of k?
        while (board[k] == n) {

            // Roll value over to 0
            board[k] = 0;

            // If we've made it to the end of all possible combinations, return {-1, x, x, ... } as a flag
            if (k == 0) {
                board[k] = -2;
                break;
            }

            // Check the value left of k
            k -= 1;
        }

        // Increment value at k by 1
        board[k] += 1;

        return board;
    }

    public boolean isLegalPlacement(int[] board) {

        int n = board.length;
        for (int i = n-1; i > -1; i--) {
            if (board[i] != 0) {
                n = i+1;
                break;
            }
        }

        // Get the current queen's column
        int queen = board[n-1];

        // Is the current queen in a legal position?

        // Check column above for queens
        for (int i = n-2; i > -1; i--) {
            if (board[i] == queen) return false;
        }

        // Check the upper left diagonal for queens
        int tempCol = queen-2;
        int tempRow = n-2;
        while (tempCol > -1 && tempRow > -1) {
            if (board[tempRow]-1 == tempCol) return false;
            tempCol -= 1;
            tempRow -= 1;
        }

        // Check the upper right diagonal for queens
        tempCol = queen;
        tempRow = n-2;
        while (tempCol <= board.length && tempRow > -1) {
            if (board[tempRow]-1 == tempCol) return false;
            tempCol += 1;
            tempRow -= 1;
        }

        return true;
    }

    public boolean isLegalPosition(int[] board, int n) {

        // Before anything, make sure the input is valid (there are no zeros that are not tailing nonzero values)
        if (n == board.length) {
            if (invalidInput(board, n)) return false;
        }

        // Check for finished flag
        if (board[0] == -1) return true;

        // Get the current queen's column
        int queen = board[n-1];

        // If this is the last queen on a list, the position is legal
        if (n == 1) return true;

        // If there exists no queen, the position is legal, recursively call the function (n-1)
        if (queen == 0) {
            return isLegalPosition(board, n-1);
        }

        // Is the current queen in a legal position?

        // Check column above for queens
        for (int i = n-2; i > -1; i--) {
            if (board[i] == queen) return false;
        }

        // Check the upper left diagonal for queens
        int tempCol = queen-2;
        int tempRow = n-2;
        while (tempCol > -1 && tempRow > -1) {
            if (board[tempRow]-1 == tempCol) return false;
            tempCol -= 1;
            tempRow -= 1;
        }

        // Check the upper right diagonal for queens
        tempCol = queen;
        tempRow = n-2;
        while (tempCol <= board.length && tempRow > -1) {
            if (board[tempRow]-1 == tempCol) return false;
            tempCol += 1;
            tempRow -= 1;
        }

        // If there is no queen in any of the three checked lines (legal position), recursively call the function
        return isLegalPosition(board, n-1);
    }

    private boolean invalidInput(int[] board, int n) {

        // Valid input?
        boolean incomplete = false;
        boolean nonZero = false;
        for (int i = n-1; i >= 0; i--) {
            if (board[i] == 0) {
                incomplete = true;
            } else {
                nonZero = true;
            }
            if (incomplete && nonZero && board[i] == 0) {
                // System.out.println("Invalid board input: " + Arrays.toString(board));
                return true;
            }
        }

        return false;
    }
}
