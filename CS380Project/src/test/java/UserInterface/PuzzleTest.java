/**
 * Unit tests for the Puzzle class.
 * These tests check that puzzle objects are properly initialized and that
 * their default states are correctly set when created.
 */

package UserInterface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PuzzleTest {

    @Test
    void testPuzzleExists() {
        Puzzle p = new Puzzle();
        assertNotNull(p);
    }

    @Test
    void testInitialState() {
        Puzzle p = new Puzzle();
        assertFalse(p.puzzleStatus);
    }
}