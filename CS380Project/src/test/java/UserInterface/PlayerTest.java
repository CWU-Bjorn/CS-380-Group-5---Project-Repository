/**
 * Unit tests for the Player class.
 * These tests verify that player data is correctly stored and retrieved,
 * including constructor initialization, password validation, health updates,
 * and display output formatting.
 */

package UserInterface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void testConstructorAndGetters() {
        Player p = new Player(
                1,
                "Ameer",
                25,
                "pass123",
                true,
                false,
                true,
                false,
                100,
                5
        );

        assertEquals("Ameer", p.getPlayerName());
        assertEquals(25, p.getPlayerHP());
        assertEquals("pass123", p.getPasswordForSaveToSave());
        assertEquals(100, p.getCurrencyAmount());
        assertEquals(5, p.getNumberOfCompleatedObstacles());
        assertEquals(1, p.getSaveslotRotation());
    }

    @Test
    void testPasswordCorrect() {
        Player p = new Player(1, "A", 25, "secret", true, false, true, false, 0, 0);
        assertTrue(p.isPasswordCorrect("secret"));
    }

    @Test
    void testPasswordIncorrect() {
        Player p = new Player(1, "A", 25, "secret", true, false, true, false, 0, 0);
        assertFalse(p.isPasswordCorrect("wrong"));
    }

    @Test
    void testHPChanges() {
        Player p = new Player(1, "A", 25, "p", true, false, true, false, 0, 0);

        p.setPlayerHP(-5);
        assertEquals(20, p.getPlayerHP());

        p.setPlayerHP(10);
        assertEquals(30, p.getPlayerHP());
    }

    @Test
    void testDisplayContainsName() {
        Player p = new Player(1, "Hero", 25, "p", true, false, false, false, 0, 0);
        assertTrue(p.getDisplayToGUI().contains("Hero"));
    }

    @Test
    void testInventoryShowsSword() {
        Player p = new Player(1, "A", 25, "p", true, false, false, false, 0, 0);
        assertTrue(p.getDisplayToGUI().contains("Sword"));
    }

    @Test
    void testEmptyInventoryMessage() {
        Player p = new Player(1, "A", 25, "p", false, false, false, false, 0, 0);
        assertTrue(p.getDisplayToGUI().toLowerCase().contains("inventory"));
    }

    @Test
    void testEmptySaveCheck() {
        Player p = new Player(1, "", 25, "", false, false, false, false, 0, 0);
        assertTrue(p.emptySaveCheck());
    }
}