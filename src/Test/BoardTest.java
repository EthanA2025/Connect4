package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import model.Board;
import model.Piece;
import model.Type;

@Testable
public class BoardTest {
    @Test
    public void addPiece() {
        // setup
        Board board = new Board();
        
        // invoke
        board.placePiece(new Piece(Type.RED), 0);

        // analyze
        String expected = "";
        String actual = board.toString();

        assertEquals(expected, actual);
    }
}
