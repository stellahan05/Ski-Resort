package persistence;

import model.Review;
import model.Trail;
import model.Trails;
import org.json.JSONArray;
import org.json.JSONObject;
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
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
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
            List<Trail> allTrails = trails.getTrails();
            assertEquals(2, allTrails.size());
            List<Review> collinsReviews = allTrails.get(0).getReviews();
            List<Review> horizonReviews = allTrails.get(1).getReviews();
            checkTrail("Collins", "Easy", true, collinsReviews, allTrails.get(0));
            checkTrail("Horizon", "Intermediate", true, horizonReviews, allTrails.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
