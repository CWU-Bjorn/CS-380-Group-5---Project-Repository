/**
 * Unit tests for CurrentPlayerSessionHelperClass.
 * These tests verify that the current player session can be set, retrieved,
 * and cleared correctly during gameplay.
 */
package UserInterface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrentPlayerSessionHelperClassTest {

    @Test
    void testSetAndGetPlayer() {
        Player p = new Player(
                1,
                "TestPlayer",
                25,
                "pass",
                true,
                false,
                true,
                false,
                100,
                2
        );

        CurrentPlayerSessionHelperClass.setCurrentPlayer(p);

        assertNotNull(CurrentPlayerSessionHelperClass.getCurrentPlayer());
        assertEquals("TestPlayer",
                CurrentPlayerSessionHelperClass.getCurrentPlayer().getPlayerName());
    }

    @Test
    void testClearCurrentPlayer() {
        Player p = new Player(
                1,
                "TestPlayer",
                25,
                "pass",
                true,
                false,
                true,
                false,
                100,
                2
        );

        CurrentPlayerSessionHelperClass.setCurrentPlayer(p);
        CurrentPlayerSessionHelperClass.clearCurrentPlayer();

        assertNull(CurrentPlayerSessionHelperClass.getCurrentPlayer());
    }
}