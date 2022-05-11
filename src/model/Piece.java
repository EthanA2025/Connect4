package model;

/**
 * A class that represents a piece on the connect 4 board
 */
public class Piece {
    
    private final Type type;
    private final int position;

    public Piece(Type type, int position) {
        this.type = type;
        this.position = position;
    }
    
}