/**
 * Unit tests for DatabaseConnection.
 * These tests ensure that player data can be correctly loaded from the database
 * and that invalid save slots are handled safely without crashing the program.
 */

package UserInterface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @Test
    void testValidLoad() {
        Player p = DatabaseConnection.loadPlayer(1);
        assertNotNull(p);
    }

    @Test
    void testInvalidLoadHandled() {
        assertThrows(IllegalArgumentException.class, () -> {
            DatabaseConnection.loadPlayer(999);
        });
    }
}