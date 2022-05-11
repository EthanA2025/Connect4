package model;

import javafx.geometry.Pos;

/**
 * A class that represents a piece on the connect 4 board
 * It has a type enumeration as well as a position associated with it
 */
public class Piece {
    
    private final Type type;
    private final Position position;

    public Piece(Type type, Position position) {
        this.type = type;
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

}