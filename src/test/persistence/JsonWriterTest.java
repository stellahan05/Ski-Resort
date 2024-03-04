package persistence;

import model.Review;
import model.Trail;
import model.Trails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test class for JsonWriter
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Trails trails = new Trails();
            JsonWriter writer = new JsonWriter("./data/my/0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTrails() {
        try {
            Trails trails = new Trails();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTrails.json");
            writer.open();
            writer.write(trails);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTrails.json");
            trails = reader.read();
            assertEquals(0, trails.numOfTrails());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTrails() {
        try {
            Trails trails = new Trails();
            Trail collins = new Trail("Collins", "Easy");
            Trail horizon = new Trail("Horizon", "Intermediate");
            Review review1 = new Review(1, "horrible", "Stella");
            Review review5 = new Review(5, "amazing", "Han");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTrails.json");
            writer.open();
            writer.write(trails);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTrails.json");
            trails = reader.read();
            trails.addTrail(collins);
            trails.addTrail(horizon);
            collins.addReview(review1);
            horizon.addReview(review5);
            assertEquals(2, trails.getTrails().size());
            List<Review> collinsReviews = collins.getReviews();
            List<Review> horizonReviews = horizon.getReviews();
            assertEquals(1, collinsReviews.size());
            assertEquals(1, horizonReviews.size());
            checkTrail("Collins", "Easy", true, collinsReviews,
                    trails.getTrails().get(0));
            checkTrail("Horizon", "Intermediate", true, horizonReviews,
                    trails.getTrails().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}