package model;

import graphs.AdjacencyGraph;

/**
 * A class to represent a connect 4 board (6x7)
 */
public class Board {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private final AdjacencyGraph<Piece> board;
    
    public Board(AdjacencyGraph<Piece> board) {
        this.board = board; 
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLS; j++) {
                sb.append("[ ]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Board board = new Board(new AdjacencyGraph<>());
        System.out.println(board.toString());
    }

}
