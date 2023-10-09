import java.util.*;
public class Connect4 {
    // Global variables
    String[][]board;
    Boolean winner;
    Boolean draw;
    int winningPlayer;
    int playerTurn;


    // Constructor
    public Connect4(){
        winningPlayer = 0;
        draw = false;
        playerTurn = 1;
        board = new String[6][7];
        newBoard();
        displayBoard();
    }

    // Making a good looking board on console
    private void newBoard(){
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                board[i][j] = " - ";
            }
        }
    }

    // It displays the board
    private void displayBoard(){
        System.out.print(" ");
        System.out.println("   *  CONNECT 4 *");
        System.out.println(" ");
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // It checks wthether input of user
    // True if valid otherwise false
    private boolean validInput(String input){
        return ((Objects.equals(input, "1") ||
                Objects.equals(input, "2") ||
                Objects.equals(input, "3") ||
                Objects.equals(input, "4") ||
                Objects.equals(input, "5") ||
                Objects.equals(input, "6") ||
                Objects.equals(input, "7")));
    }


    private boolean isColumnFull(int column){
        return(board[0][column-1] == " X " || board[0][column-1] == " 0 " );
    }

    // Get next Available slot
    // returns the next available row position of a given column
    private int getNextAvailableslot(int column){
        int position = 5;
        boolean found = false;
        while(position>=0 && !found)
        {
            if(!Objects.equals(board[position][column]," X ") && !Objects.equals(board[position][column]," 0 "))
            {
                found = true;
            }
            else
            {
                position--;
            }
        }
        return position;
    }

    // Swap turn for current player
    private void swapPlayerTurn(){
        if(playerTurn==1)
        {
            playerTurn = 2;
        }
        else
        {
            playerTurn = 1;
        }
    }

    // Place Piece
    // Place piece onto the board in chosen and available position
    private void placePiece(){
        // Get available column
        Scanner sc  =new Scanner(System.in);
        System.out.println("Player " + playerTurn + " - Please select which column to place your piece(1-7): ");
        String input = sc.nextLine();
        while(!validInput(input) || isColumnFull(Integer.parseInt(input)))
        {
            while(!validInput(input))
            {
                System.out.println("Invalid Input - please select a column from 1-7");
                input = sc.nextLine();
            }
            while(isColumnFull(Integer.parseInt(input)))
            {
                System.out.println("Column full, choose another column");
                input = sc.nextLine();
                if(!validInput(input))
                {
                    break;
                }
            }
        }
        int columnchoice = Integer.parseInt(input) - 1; // Player column choice


        // Get Available row
        System.out.println("Next available row in column: " + getNextAvailableslot(columnchoice));

        // Player piece
        String pieceToPlace;
        if(playerTurn == 1)
        {
            pieceToPlace = " X ";
        }
        else
        {
            pieceToPlace = " O ";
        }
        board[getNextAvailableslot(columnchoice)][columnchoice]  = pieceToPlace;
        displayBoard();
        swapPlayerTurn();
    }


    // Is Board full
    // returns true if board is full otherwise false
    private boolean isBoardFull(){
        boolean full = true;
        for(int i=0;i<1;i++)
        {
            for(int j=0;j<7;j++)
            {
                if(board[i][j]!= " X " && board[i][j]!= " O ")
                {
                    full = false;
                }
            }
        }
        return full;
    }


    // Chck the vertical winner
    // returns symbol of the winner(X or O)
    private String checkVerticalWinner(){
        String symbol = null;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<7;j++)
            {
                if((board[i][j]==board[i+1][j]) && (board[i][j]==board[i+2][j]) && (board[i][j]==board[i+3][j]))
                {
                    if(board[i][j]==" X " || board[i][j]==" O ")
                    {
                        symbol = board[i][j];
                    }
                }
            }
        }
        return symbol;
    }



    // Chck the horizontal winner
    // returns symbol of the winner(X or O)
    private String checkHorizontalWinner(){
        String symbol = null;
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<4;j++)
            {
                if((board[i][j]==board[i][j+1]) && (board[i][j]==board[i][j+2]) && (board[i][j]==board[i][j+3]))
                {
                    if(board[i][j]==" X " || board[i][j]==" O ")
                    {
                        symbol = board[i][j];
                    }
                }
            }
        }
        return symbol;
    }



    // Chck the left diagonal winner
    // returns symbol of the winner(X or O)
    private String checkLeftDiagonalWinner(){
        String symbol = null;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<4;j++)
            {
                if((board[i][j]==board[i+1][j+1]) && (board[i][j]==board[i+2][j+2]) && (board[i][j]==board[i+3][j+3]))
                {
                    if(board[i][j]==" X " || board[i][j]==" O ")
                    {
                        symbol = board[i][j];
                    }
                }
            }
        }
        return symbol;
    }



    // Chck the right diagonal winner
    // returns symbol of the winner(X or O)
    private String checkRightDiagonalWinner(){
        String symbol = null;
        for(int i=0;i<3;i++)
        {
            for(int j=3;j<7;j++)
            {
                if((board[i][j]==board[i+1][j-1]) && (board[i][j]==board[i+2][j-2]) && (board[i][j]==board[i+3][j-3]))
                {
                    if(board[i][j]==" X " || board[i][j]==" O ")
                    {
                        symbol = board[i][j];
                    }
                }
            }
        }
        return symbol;
    }


    // Check for winning player
    // retuns 1 or 2
    private int checkforWinner(){
        int winner = 0;
        String symbol = " ";
        if(checkVerticalWinner()==" X " || checkVerticalWinner()==" O ")
        {
            symbol = checkVerticalWinner();
        }
        else if(checkHorizontalWinner()==" X " || checkHorizontalWinner()==" O ")
        {
            symbol = checkHorizontalWinner();
        }
        else if(checkLeftDiagonalWinner()==" X " || checkLeftDiagonalWinner()==" O ")
        {
            symbol = checkLeftDiagonalWinner();
        }
        else if(checkRightDiagonalWinner()==" X " || checkRightDiagonalWinner()==" O ")
        {
            symbol = checkRightDiagonalWinner();
        }
        if(symbol==" X ")
        {
            winner = 1;
        }
        else if(symbol==" O ")
        {
            winner = 2;
        }
        return winner;
    }


    // Check for draw
    // returns true if game is draw - otherwise false
    private boolean checkforDraw(){
        return(isBoardFull()==true && (checkforWinner()!=1 && checkforWinner()!=2));
    }


    // Displays Winner graphics in console
    private void showWinner(){
        System.out.println(" ");
        System.out.println(" **********");
        System.out.println("                                ");
        System.out.println("        PLAYER " + winningPlayer + " WINS !!! ");
        System.out.println("     *                    *      ");
        System.out.println("         *   \\O/     *        ");
        System.out.println("    *    *    |     *     *    ");
        System.out.println("      *      / \\       *      ");
        System.out.println("  ~~~~~~~~~~    ");
        System.out.println(" *********");
    }


    // Play 1 round of connect 4
    public void gamePlay(){
        while(winningPlayer==0)
        {
            winningPlayer = checkforWinner();
            draw = checkforDraw();
            if(winningPlayer == 1)
            {
                showWinner();
                break;
            }
            else if(winningPlayer == 2)
            {
                showWinner();
                break;
            }
            else if(draw == true)
            {
                System.out.println("It's a draw");
                break;
            }
            placePiece();
        }
    }
    public static void main(String[] args) {
        Connect4 a4 = new Connect4();
        a4.gamePlay();
    }
}
