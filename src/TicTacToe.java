import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        boolean playAgain;

        // Repeat games until the players choose to quit.
        do {
            // Clear the board and start with X.
            clearBoard();
            String player = "X";
            int moveCount = 0;
            boolean gameOver = false;

            while (!gameOver) {
                display();
                System.out.println("Player " + player + "'s turn.");

                int row;
                int col;

                // Get coordinates and repeat until the move is valid.
                do {
                    row = SafeInput.getRangedInt(
                            console, "Enter row", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(
                            console, "Enter column", 1, COLS) - 1;

                    if (!isValidMove(row, col)) {
                        System.out.println("That space is occupied. Try again.");
                    }
                } while (!isValidMove(row, col));

                // Record the move and increase the move counter.
                board[row][col] = player;
                moveCount++;

                // Check for a win before checking for a tie.
                if (moveCount >= 5 && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (moveCount >= 5 && isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    gameOver = true;
                }

                // Switch players if the game is still going.
                if (!gameOver) {
                    player = player.equals("X") ? "O" : "X";
                }
            }

            // Ask whether to start another game.
            playAgain = SafeInput.getYNConfirm(
                    console, "Would you like to play again?");
        } while (playAgain);

        System.out.println("Thanks for playing!");
        console.close();
    }

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println();
        System.out.print("   ");

        for (int col = 0; col < COLS; col++) {
            System.out.print(" " + (col + 1) + "  ");
        }
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            System.out.print((row + 1) + "  ");

            for (int col = 0; col < COLS; col++) {
                System.out.print(" " + board[row][col] + " ");

                if (col < COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (row < ROWS - 1) {
                System.out.print("   ");

                for (int col = 0; col < COLS; col++) {
                    System.out.print("---");

                    if (col < COLS - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player)
                || isColWin(player)
                || isDiagnalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            boolean win = true;

            for (int col = 0; col < COLS; col++) {
                if (!board[row][col].equals(player)) {
                    win = false;
                }
            }

            if (win) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            boolean win = true;

            for (int row = 0; row < ROWS; row++) {
                if (!board[row][col].equals(player)) {
                    win = false;
                }
            }

            if (win) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        boolean leftToRight = true;
        boolean rightToLeft = true;

        for (int row = 0; row < ROWS; row++) {
            if (!board[row][row].equals(player)) {
                leftToRight = false;
            }

            if (!board[row][COLS - 1 - row].equals(player)) {
                rightToLeft = false;
            }
        }

        return leftToRight || rightToLeft;
    }

    private static boolean isTie() {
        if (isWin("X") || isWin("O")) {
            return false;
        }

        return !hasPossibleWin("X") && !hasPossibleWin("O");
    }

    private static boolean hasPossibleWin(String player) {
        String opponent = player.equals("X") ? "O" : "X";

        // Check whether any row remains available.
        for (int row = 0; row < ROWS; row++) {
            boolean possible = true;

            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(opponent)) {
                    possible = false;
                }
            }

            if (possible) {
                return true;
            }
        }

        // Check whether any column remains available.
        for (int col = 0; col < COLS; col++) {
            boolean possible = true;

            for (int row = 0; row < ROWS; row++) {
                if (board[row][col].equals(opponent)) {
                    possible = false;
                }
            }

            if (possible) {
                return true;
            }
        }

        // Check whether either diagonal remains available.
        boolean leftToRight = true;
        boolean rightToLeft = true;

        for (int row = 0; row < ROWS; row++) {
            if (board[row][row].equals(opponent)) {
                leftToRight = false;
            }

            if (board[row][COLS - 1 - row].equals(opponent)) {
                rightToLeft = false;
            }
        }

        return leftToRight || rightToLeft;
    }
}