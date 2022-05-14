package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import model.Board;
import model.Gamestate;
import model.Piece;
import model.Type;

@Testable
public class BoardTest {
    @Test
    public void addPiece() {
        // setup
        Board board = new Board(Gamestate.IN_PROGRESS);
        
        // invoke
        board.placePiece(0);

        // analyze
        String expected = "[RED]";
        String actual = board.getBoardArray()[5][0].toString();

        assertEquals(expected, actual);
    }
}
