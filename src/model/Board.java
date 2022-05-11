package model;

import graphs.AdjacencyGraph;

/**
 * A class to represent a connect 4 board (6x7)
 */
public class Board {
    
    private final AdjacencyGraph<Piece> board;
    
    public Board(AdjacencyGraph<Piece> board) {
        this.board = board; 
    }
}
