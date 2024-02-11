package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

// Test class for the Trails class
public class TrailsTest {
    private Trails trails;
    private Trail collins;
    private Trail horizon;
    private Trail blaster;

    @BeforeEach
    void runBefore() {
        trails = new Trails();
        collins = new Trail("Collins", "Easy");
        horizon = new Trail("Horizon", "Intermediate");
        blaster = new Trail("Blaster", "Advanced");
    }

    @Test
    void testAddTrail() {
        trails.addTrail(collins);
        assertEquals(1, trails.numOfTrails());
        assertEquals(collins, trails.findTrail("Collins"));
        trails.addTrail(horizon);
        assertEquals(2, trails.numOfTrails());
        assertEquals(horizon, trails.findTrail("Horizon"));
        trails.addTrail(blaster);
        assertEquals(3, trails.numOfTrails());
        assertEquals(blaster, trails.findTrail("Blaster"));
    }

    @Test
    void testRemoveTrail() {
        trails.addTrail(collins);
        assertEquals(1, trails.numOfTrails());
        trails.removeTrail(collins);
        assertEquals(0, trails.numOfTrails());
        trails.addTrail(horizon);
        trails.addTrail(blaster);
        assertEquals(2, trails.numOfTrails());
        trails.removeTrail(horizon);
        assertEquals(1, trails.numOfTrails());
    }

    @Test
    void testGetAllTrailNames() {
        trails.addTrail(collins);
        trails.addTrail(horizon);
        List<String> allTrailNames = trails.getAllTrailNames();
        assertEquals(2, allTrailNames.size());
        assertTrue(allTrailNames.contains("Collins"));
        assertTrue(allTrailNames.contains("Horizon"));
        assertFalse(allTrailNames.contains("Blaster"));
    }

    @Test
    void testNumOfTrails() {
        assertEquals(0, trails.numOfTrails());
        trails.addTrail(collins);
        assertEquals(1, trails.numOfTrails());
        trails.addTrail(horizon);
        trails.addTrail(blaster);
        assertEquals(3, trails.numOfTrails());
        trails.removeTrail(blaster);
        assertEquals(2, trails.numOfTrails());
    }

    @Test
    void testFindTrail() {
        assertNull(trails.findTrail("Collins"));
        trails.addTrail(collins);
        assertEquals(collins, trails.findTrail("Collins"));
        assertEquals(collins, trails.findTrail("collins"));
        assertNull(trails.findTrail("Colin"));
        trails.addTrail(horizon);
        trails.addTrail(blaster);
        assertEquals(horizon, trails.findTrail("horizon"));
    }



}
