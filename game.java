import java.util.Scanner;

public class game {
    public String[][] board = {{" ", "a", "b", "c"}, {"1", "-", "-", "-"}, {"2", "-", "-", "-"}, {"3", "-", "-", "-"}};

    public void renderBoard() {
        String printBoard = "";
        
        System.out.println();
        int i = 0;
        for (String[] row : board) {

            int j = 0;
            for (String tile : row) {
                if (j == 0) {
                    printBoard += tile;
                } else {
                    printBoard = String.join(" | ", printBoard, tile);
                }
                j++;
            }
            
            if (i < 3) {
                printBoard += "\n--------------\n";
            }
            i++;
        }

        System.out.println(printBoard);
    }

    public void choice() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nWhere would you like to go?");
        String userChoice = input.nextLine().toLowerCase().trim();

        if (userChoice.equals("a1") || userChoice.equals("1a")) {
            place(1, 1, "O");
        } else if (userChoice.equals("b1") || userChoice.equals("1b")) {
            place(1, 2, "O");
        } else if (userChoice.equals("c1") || userChoice.equals("1c")) {
            place(1, 3, "O");
        } else if (userChoice.equals("a2") || userChoice.equals("2a")) {
            place(2, 1, "O");
        } else if (userChoice.equals("b2") || userChoice.equals("2b")) {
            place(2, 2, "O");
        } else if (userChoice.equals("c2") || userChoice.equals("2c")) {
            place(2, 3, "O");
        } else if (userChoice.equals("a3") || userChoice.equals("3a")) {
            place(3, 1, "O");
        } else if (userChoice.equals("b3") || userChoice.equals("3b")) {
            place(3, 2, "O");
        } else if (userChoice.equals("c3") || userChoice.equals("3c")) {
            place(3, 3, "O");
        } else {
            System.out.println("\nSorry I couldn't quite get that, please try again.");
            choice();
        }
    }
    
    public void place(int row, int column, String player) {
        if (board[row][column] == "-") {
            board[row][column] = player;

            if (player == "X") {
                String AIColumn = "";

                if (column == 1) {
                    AIColumn = "a";
                } else if (column == 2) {
                    AIColumn = "b";
                } else if (column == 3) {
                    AIColumn = "c";
                }

                System.out.println("\nThe AI went to " + AIColumn + row);
            }
        } else {
            if (player == "O") {
                System.out.println("\nSomebody's already moved to that tile, please try again.");
                choice();
            } else {
                AI();
            }
        }
    }

    public boolean AI() {
        int count = 0;
        for (String[] row : board) {
            for (String tile : row) {
                if (tile == "-") {
                    count += 1;
                }
            }
        }

        if (count == 0) {
            return true;
        }

        int randomRow = (int) Math.floor(Math.random() * 4);
        if (randomRow != 0) {
            int randomColumn = (int) Math.floor(Math.random() * 4);

            if (randomColumn != 0) {
                place(randomRow, randomColumn, "X");
                return false;
            }
        }

        AI();
        return false;
    }

    public boolean checkWin(String player) {
        if (board[1][1] == player && board[1][2] == player && board[1][3] == player) {
            System.out.println(player + " WINS!");
        } else if (board[2][1] == player && board[2][2] == player && board[2][3] == player) {
            System.out.println(player + " WINS!");
        } else if (board[3][1] == player && board[3][2] == player && board[3][3] == player) {
            System.out.println(player + " WINS!");
        } else if (board[1][1] == player && board[2][1] == player && board[3][1] == player) {
            System.out.println(player + " WINS!");
        } else if (board[1][2] == player && board[2][2] == player && board[3][2] == player) {
            System.out.println(player + " WINS!");
        } else if (board[1][3] == player && board[2][3] == player && board[3][3] == player) {
            System.out.println(player + " WINS!");
        } else if (board[1][1] == player && board[2][2] == player && board[3][3] == player) {
            System.out.println(player + " WINS!");
        } else if (board[1][3] == player && board[2][2] == player && board[3][1] == player) {
            System.out.println(player + " WINS!");
        } else {
            return false;
        }

        renderBoard();

        return true;
    }

    public static void main(String[] args) {
        game renderGame = new game();

        while (true) {
            renderGame.renderBoard();
            renderGame.choice();
            boolean playerWin = renderGame.checkWin("O");

            if (playerWin) {
                break;
            }

            boolean tie = renderGame.AI();

            if (tie) {
                System.out.println("TIE!");
                renderGame.renderBoard();
                break;
            }

            boolean AIWin = renderGame.checkWin("X");

            if (AIWin) {
                break;
            }
        }
    }
}