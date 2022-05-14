package model;

/**
 * A class to represent a connect 4 board (6x7)
 */
public class Board {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private final Piece[][] boardArray;
    private int turnsPlayed;
    private Gamestate state;
    
    public Board(Gamestate state) {
        this.boardArray = new Piece[6][7];
        this.state = state;
        this.turnsPlayed = 0;
    }

    public void setGamestate(Gamestate state) {
        this.state = state;
    }

    public Piece[][] getBoardArray() {
        return this.boardArray;
    }

    public int getTurnsPlayed() {
        return this.turnsPlayed;
    }

    public Gamestate getGamestate() {
        return this.state;
    }

    /**
     * Increments the turn number of the game
     */
    public void turnPlayed() {
        this.turnsPlayed++;
    }

    /**
     * Checks the connections of the pieces. If either color has 4 pieces in a row of the same color, diagonally, 
     * vertically, horizontally then the gamestate is equal to won. Otherwise the connections don't equate to 4 so the game will
     * continue to be in progress.
     * @return - whether the game is won or not
     */
    public void checkConnections() {
        int connections = 0;
        int rowPossible[] = {-1, -1, 0, 1, 1, 1, 0, -1};
        int colPossible[] = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int row=0; row<ROWS; row++) {
            for (int col=0; col<COLS; col++) { // check if piece exists first
                if (boardArray[row][col] != null) {
                    // now check for surrounding pieces for all combinations, if the type is the same it contributes to a connection
                    for (int i=0; i<rowPossible.length; i++) {
                        int surroundingCol = col + colPossible[i];
                        int surroundingRow = row + rowPossible[i];
                        
                        // check to see if the surrounding piece is the same type
                        if (boardArray[surroundingRow][surroundingCol].getType() == boardArray[row][col].getType()) {
                            connections++;
                        }
                    }
                }
            }
        }
        if (connections == 4) {
            this.state = Gamestate.WON;
            System.out.println(this.toString());
            if (turnsPlayed%2 == 0) {
                System.out.println("RED wins!");
            } else {
                System.out.println("Yellow wins!");
            }
        }
    }

    /**
     * Given a column number this is a method to insert a piece onto the connect 4 board. First checks if the move is valid
     * then puts the piece into the appropriate column. The piece will be added to the bottom of the 
     * board and the pieces will stack onto each other.
     * @param column - column the piece will be added too
     */
    public void placePiece(int column) {
        // first check if the position is valid that the piece is being added too, then add it to the 2D array and set
        boolean valid = false;
        int row = 0;
        Piece piece = new Piece(Type.RED);

        if (column >= COLS) {
            System.out.println("Invalid column!");
            return;
        }
        if (turnsPlayed%2 == 1) {
            piece.setType(Type.YELLOW);
        }

        for (int i=1; i<=ROWS; i++) {
            if (boardArray[ROWS-i][column] == null) {
                boardArray[ROWS-i][column] = piece;
                row = i;
                valid = true; // inserts into the 2D array
                break;
            }
        }
        if (!valid) {
            System.out.println("Invalid placement!");
        } else {
            System.out.println("Piece placed at row: " + row + " column: " + column);
        } // if all of them are not null then the column is full
        checkConnections();
        // check connections to see if the game is won
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
            board.placePiece(i);
            System.out.println(board.toString());
        }
    }
}
