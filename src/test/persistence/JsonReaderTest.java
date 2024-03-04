package persistence;

import model.Review;
import model.Trail;
import model.Trails;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Trails trails = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTrails() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTrails.json");
        try {
            Trails trails = reader.read();
            assertEquals(0, trails.numOfTrails());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTrails() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTrails.json");
        try {
            Trails trails = reader.read();
            assertEquals(2, trails.numOfTrails());
            Trail collins = trails.getTrails().get(0);
            assertEquals("Collins", collins.getName());
            assertEquals("Easy", collins.getDifficulty());
            assertTrue(collins.isOpen());
            Trail horizon = trails.getTrails().get(1);
            assertEquals("Horizon", horizon.getName());
            assertEquals("Intermediate", horizon.getDifficulty());
            assertTrue(horizon.isOpen());
            List<Review> collinsReviews = trails.getTrails().get(0).getReviews();
            List<Review> horizonReviews = trails.getTrails().get(1).getReviews();
            checkTrail("Collins", "Easy", true, collinsReviews, trails.getTrails().get(0));
            checkTrail("Horizon", "Intermediate", true, horizonReviews, trails.getTrails().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
