package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import graphs.AdjacencyGraph;
import javafx.geometry.Pos;

/**
 * A class to represent a connect 4 board (6x7)
 */
public class Board {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private final AdjacencyGraph<Piece> boardGraph;
    private final Set<Piece> placedPieces;
    
    public Board(AdjacencyGraph<Piece> boardGraph, Set<Piece> placedPieces) {
        this.boardGraph = boardGraph; 
        this.placedPieces = placedPieces;
    }

    public boolean checkConnected(int row, int col) {
        return false;
    }

    public void placePiece(Piece piece, int row, int col) {
        this.boardGraph.add(piece);

        placedPieces.add(piece);
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
        Board board = new Board(new AdjacencyGraph<>(), new HashSet<>());
        System.out.println(board.toString());
    }

}
