package model;

/**
 * A class that represents a piece on the connect 4 board
 * It has a type enumeration as well as a position associated with it
 */
public class Piece {
    
    private Type type;

    public Piece(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String toString() {
        return "[" + this.type.toString() + "]";
    }

}