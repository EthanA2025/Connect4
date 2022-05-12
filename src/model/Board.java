package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A class to represent a connect 4 board (6x7)
 */
public class Board {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final Piece[][] boardArray = new Piece[6][7];
    
    public Board() {

    }

    /**
     * Method to insert a piece onto the connect 4 board
     * @param piece - piece that will be added to the board
     *
     */
    public void placePiece(Piece piece, int column) {
        // first check if the position is valid that the piece is being added too, then add it to the 2D array and set
        boolean valid = false;
        int row = 0;
        for (int i=0; i<ROWS; i++) {
            if (boardArray[i][column] == null) {
                boardArray[i][column] = piece;
                row = i;
                valid = true;
                break;
            }
        }
        if (!valid) {
            System.out.println("Invalid placement!");
        } else {
            System.out.println("Piece placed at row: " + row + " column: " + column);
        }
        // if all of them are not null then the column is full
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
        Board board = new Board();
        System.out.println(board.toString());
        board.placePiece(new Piece(Type.RED), 0);
        System.out.println(board.toString());
        board.placePiece(new Piece(Type.RED), 0);
        System.out.println(board.toString());

    }

}
