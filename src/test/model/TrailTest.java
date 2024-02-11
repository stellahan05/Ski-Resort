package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Trail class
class TrailTest {
    private Trail collins;
    private Trail horizon;
    private Trail blaster;

    @BeforeEach
    void runBefore() {
        collins = new Trail("Collins", "Easy");
        horizon = new Trail("Horizon", "Intermediate");
        blaster = new Trail("Blaster", "Advanced");
    }

    @Test
    void testGetName() {
        assertEquals("Collins", collins.getName());
        assertEquals("Horizon", horizon.getName());
        assertEquals("Blaster", blaster.getName());
    }

    @Test
    void testGetDifficulty() {
        assertEquals("Easy", collins.getDifficulty());
        assertEquals("Intermediate", horizon.getDifficulty());
        assertEquals("Advanced", blaster.getDifficulty());
    }

    @Test
    void testIsOpen() {
        assertTrue(collins.isOpen());
        collins.setStatus(false);
        assertFalse(collins.isOpen());
        collins.setStatus(true);
        assertTrue(collins.isOpen());
        assertTrue(horizon.isOpen());
        horizon.setStatus(true);
        assertTrue(horizon.isOpen());
        assertTrue(blaster.isOpen());
    }

    @Test
    void testSetStatus() {
        assertTrue(collins.isOpen());
        collins.setStatus(false);
        assertFalse(collins.isOpen());
        collins.setStatus(true);
        assertTrue(collins.isOpen());
    }

    @Test
    void testToString() {
        assertEquals("Trail Name: Collins, Difficulty: Easy, Status: Open",
                collins.toString());
        collins.setStatus(false);
        assertEquals("Trail Name: Collins, Difficulty: Easy, Status: Closed",
                collins.toString());
        assertEquals("Trail Name: Horizon, Difficulty: Intermediate, Status: Open",
                horizon.toString());
        assertEquals("Trail Name: Blaster, Difficulty: Advanced, Status: Open",
                blaster.toString());
    }
}