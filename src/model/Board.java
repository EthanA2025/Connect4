package model;

import java.util.Arrays;

import view.Connect4Observer;

/**
 * A class to represent a connect 4 board (6x7).
 * Each board has a boardArray associated with it that
 * contains the pieces placed by the players. It also has
 * the number of turns played and a current state to regulate
 * the flow of the game.
 * @author Ethan Abbate 2022
 */
public class Board {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private Piece[][] boardArray;
    private int turnsPlayed;
    private Gamestate state;
    private Connect4Observer observer;
    
    public Board(Gamestate state) {
        this.boardArray = new Piece[ROWS][COLS];
        this.state = state;
        this.turnsPlayed = 0;
    }

    /**
     * Setter for an observer object
     * @param observer 
     */
    public void register(Connect4Observer observer) {
        this.observer = observer;
    }

    /**
     * Sets the state of the game using the Gamestate enumeration
     * @param state
     */
    public void setGamestate(Gamestate state) {
        this.state = state;
    }

    /**
     * Gets the boardArray - the array of pieces that represents 
     * the connect 4 board.
     * @return the board array
     */
    public Piece[][] getBoardArray() {
        return this.boardArray;
    }

    /**
     * Gets the number of turns played in the game
     * @return number of turns
     */
    public int getTurnsPlayed() {
        return this.turnsPlayed;
    }

    /**
     * Gets the current state of the game
     * @return state of the game
     */
    public Gamestate getGamestate() {
        return this.state;
    }

    /**
     * Gets number of rows in the board
     * @return num rows
     */
    public int getRows() {
        return ROWS;
    }

    /**
     * Gets number of cols in the board
     * @return num cols
     */
    public int getCols() {
        return COLS;
    }

    /**
     * Method that changes the state of the game accordingly and prints messages
     * once the game has been won.
     */
    public String gameWon() {
        this.state = Gamestate.WON;
        System.out.println(this.toString());
        if ((turnsPlayed-1)%2 == 0) {
            System.out.println("Red wins!"); // since turns are incremented before this, subtract 1 from turnsPlayed to get correct winner
            return "RED wins!";

        } else {
            System.out.println("Yellow wins!");
            return "YELLOW wins!";
        }
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
     */
    public void checkConnections() {
        // see if there are 3 more connections associated with this particular piece

        // check horizontal
        for (int col=0; col<=COLS-1; col++) {
            for (int row=0; row<=ROWS-1; row++) {
                if (row - 1 > 0 && col + 1 < COLS && boardArray[row][col] != null) {
                    Type currentType = boardArray[row][col].getType();
                    try {
                        Type Type2 = boardArray[row][col+1].getType();
                        Type Type3 = boardArray[row][col+2].getType();
                        Type Type4 = boardArray[row][col+3].getType();

                        if (currentType.equals(Type2) && currentType.equals(Type3) && currentType.equals(Type4)) {
                            gameWon();
                        }
                    } catch (Exception e) {
                        // ignore out of bounds exceptions
                    }
                }
            }
        }

        // check vertical
        for (int col=0; col<=COLS-1; col++) {
            for (int row=0; row<=ROWS-1; row++) {
                if (boardArray[row][col] != null) {
                    Type currentType = boardArray[row][col].getType();
                    try {
                        Type Type2 = boardArray[row+1][col].getType();
                        Type Type3 = boardArray[row+2][col].getType();
                        Type Type4 = boardArray[row+3][col].getType();

                        if (currentType.equals(Type2) && currentType.equals(Type3) && currentType.equals(Type4)) {
                            gameWon();
                            break;
                        }
                    } catch (Exception e) {
                        // ignore out of bounds exceptions
                    }
                }
            }
        }

        // check ascending diagonal
        for (int col=0; col<=COLS-1; col++) {
            for (int row=0; row<=ROWS-1; row++) {
                if (row - 1 > 0 && col + 1 < COLS && boardArray[row][col] != null) {
                    Type currentType = boardArray[row][col].getType();
                    try {
                        Type Type2 = boardArray[row-1][col+1].getType();
                        Type Type3 = boardArray[row-2][col+2].getType();
                        Type Type4 = boardArray[row-3][col+3].getType();

                        if (currentType.equals(Type2) && currentType.equals(Type3) && currentType.equals(Type4)) {
                            gameWon();
                        }
                    } catch (Exception e) {
                        // ignore out of bounds exceptions
                    }
                }
            }
        }
        // check ascending diagonal
        for (int col=0; col<=COLS-1; col++) {
            for (int row=0; row<=ROWS-1; row++) {
                if (row - 1 > 0 && col - 1 > 0 && boardArray[row][col] != null) {
                    Type currentType = boardArray[row][col].getType();
                    try {
                        Type Type2 = boardArray[row-1][col-1].getType();
                        Type Type3 = boardArray[row-2][col-2].getType();
                        Type Type4 = boardArray[row-3][col-3].getType();

                        if (currentType.equals(Type2) && currentType.equals(Type3) && currentType.equals(Type4)) {
                            gameWon();
                        }
                    } catch (Exception e) {
                        // ignore out of bounds exceptions
                    }
                }
            }
        }
    }

    /**
     * Given a column number this is a method to insert a piece onto the connect 4 board. First checks if the move is valid
     * then puts the piece into the appropriate column. The piece will be added to the bottom of the 
     * board and the pieces will stack onto each other.
     * @param column - column the piece will be added too
     */
    public int placePiece(int column) {
        // first check if the position is valid that the piece is being added too, then add it to the 2D array and set
        boolean valid = false;
        int row = 0;

        if (column >= COLS) {
            System.out.println("Invalid column!");
            return -1;
        }

        for (int i=1; i<=ROWS; i++) {
            if (boardArray[ROWS-i][column] == null) {
                Piece piece = new Piece(Type.RD);
                if (turnsPlayed%2 == 1) {
                    piece.setType(Type.YW);
                }
                boardArray[ROWS-i][column] = piece;
                row = i-1;
                valid = true; // inserts into the 2D array
                break;
            }
        }
        if (!valid) {
            System.out.println("Invalid placement!");
        } else {
            System.out.println("Piece placed at row: " + row + " column: " + column);
        } // if all of them are not null then the column is full
        turnPlayed();
        // check connections to see if the game is won
        return row;
    }

    /**
     * Prints out a representation of the board to the console
     * @return - String representation of the board.
     */
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

    public void resetBoard() {
        this.state = Gamestate.IN_PROGRESS;
        this.boardArray = new Piece[ROWS][COLS];
        this.turnsPlayed = 0;

        // observer.resetBoard();
    }
}