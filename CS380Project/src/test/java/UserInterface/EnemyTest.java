/**
 * Unit tests for the Enemy class.
 * These tests verify that enemy objects are created correctly and that their
 * basic properties (such as name and string output) behave as expected.
 */

package UserInterface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    void testEnemyCreation() {
        Enemy e = new Enemy("Goblin", 20, 2);

        assertNotNull(e);
    }

    @Test
    void testEnemyToStringContainsName() {
        Enemy e = new Enemy("Goblin", 20, 2);

        String output = e.toString();

        assertTrue(output.contains("Goblin"));
    }
}