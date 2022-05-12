package model;

/**
 * A class to represent a connect 4 board (6x7)
 */
public class Board {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private final Piece[][] boardArray;
    private Gamestate state;
    
    public Board(Gamestate state) {
        this.boardArray = new Piece[6][7];
        this.state = state;
    }

    public Piece[][] getBoardArray() {
        return this.boardArray;
    }

    /**
     * Checks the connections of the pieces. If either color has 4 pieces in a row of the same color, diagonally, 
     * vertically, horizontally then the gamestate is equal to won. Otherwise the connections don't equate to 4 so the game will
     * continue to be in progress.
     * @return - whether the game is won or not
     */
    public boolean checkConnections() {
        int connections = 0;
        int rowPossible[] = {};
        int colPossible[] = {};

        for (int row=0; row<ROWS; row++) {
            for (int col=0; col<COLS; col++) { // check if piece exists first
                if (boardArray[row][col] != null) {
                    // now check for surrounding pieces for all combinations, if the type is the same it contributes to a connection

                }
            }
        }
        if (connections == 4) {
            this.state = Gamestate.WON;
            return true; // this means the player has won
        }
        return false;
    }

    /**
     * Method to insert a piece onto the connect 4 board. First checks if the move is valid
     * then puts the piece into the appropriate column. The piece will be added to the bottom of the 
     * board and the pieces will stack onto each other.
     * @param piece - piece that will be added to the board
     * @param column - column the piece will be added too
     */
    public void placePiece(Piece piece, int column) {
        // first check if the position is valid that the piece is being added too, then add it to the 2D array and set
        boolean valid = false;
        int row = 0;

        if (column >= COLS) {
            System.out.println("Invalid column!");
            return;
        }
        for (int i=1; i<=ROWS; i++) {
            if (boardArray[ROWS-i][column] == null) {
                boardArray[ROWS-i][column] = piece;
                row = i;
                valid = true;
                break;
            }
        }
        if (!valid) {
            System.out.println("Invalid placement!");
        } else {
            System.out.println("Piece placed at row: " + row + " column: " + column);
        } // if all of them are not null then the column is full
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLS; j++) {
                if (boardArray[i][j] == null) {
                    sb.append("[  ]");
                } else {
                    sb.append(boardArray[i][j].toString());
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Board board = new Board(Gamestate.IN_PROGRESS);
        System.out.println(board.toString());
        for (int i=0; i<8; i++) {
            board.placePiece(new Piece(Type.YELLOW), i);
            System.out.println(board.toString());
        }
    }
}
