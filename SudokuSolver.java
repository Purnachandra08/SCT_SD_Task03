public class SudokuSolver {

    private static final int SIZE = 9; // 9x9 Sudoku

    public static void main(String[] args) {
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Original Sudoku Puzzle:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given puzzle.");
        }
    }

    // Recursive Backtracking Solver
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Find an empty cell
                if (board[row][col] == 0) {
                    // Try all numbers from 1 to 9
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            // Recur to check further
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false; // No valid number found, backtrack
                }
            }
        }
        return true; // Puzzle solved
    }

    // Check if placing num at board[row][col] is valid
    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Print the Sudoku board
    private static void printBoard(int[][] board) {
        for (int r = 0; r < SIZE; r++) {
            if (r % 3 == 0 && r != 0) {
                System.out.println("------+-------+------");
            }
            for (int c = 0; c < SIZE; c++) {
                if (c % 3 == 0 && c != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[r][c] == 0 ? ". " : board[r][c] + " ");
            }
            System.out.println();
        }
    }
}
