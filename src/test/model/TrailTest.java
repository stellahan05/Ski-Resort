package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Trail class
public class TrailTest {
    private Trail collins;
    private Trail horizon;
    private Trail blaster;

    private List<Review> collinsReviews;
    private List<Review> horizonReviews;
    private List<Review> blasterReviews;

    private Review r1;
    private Review r2;
    private Review r3;

    @BeforeEach
    void runBefore() {
        collins = new Trail("Collins", "Easy");
        horizon = new Trail("Horizon", "Intermediate");
        blaster = new Trail("Blaster", "Advanced");

        collinsReviews = new ArrayList<>();
        horizonReviews = new ArrayList<>();
        blasterReviews = new ArrayList<>();

        r1 = new Review(1, "Super icy conditions", "Stella");
        r2 = new Review(3, "Decent ride.", "Han");
        r3 = new Review(5, "My favourite trail.", "d");
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
    void testGetReviews() {
        assertTrue(collins.getReviews().isEmpty());
        collins.addReview(r1);
        collinsReviews.add(r1);
        assertEquals(collinsReviews, collins.getReviews());
        assertTrue(horizon.getReviews().isEmpty());
        assertTrue(blaster.getReviews().isEmpty());
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
    void testAddReview() {
        assertTrue(collins.getReviews().isEmpty());
        collins.addReview(r1);
        collinsReviews.add(r1);
        assertEquals(collinsReviews, collins.getReviews());
        assertEquals(1, collinsReviews.size());
        assertTrue(collinsReviews.contains(r1));
        collins.addReview(r2);
        collinsReviews.add(r2);
        assertEquals(collinsReviews, collins.getReviews());
        assertEquals(2, collinsReviews.size());
        assertTrue(collinsReviews.contains(r1));
        assertTrue(collinsReviews.contains(r2));
        horizon.addReview(r2);
        horizonReviews.add(r2);
        assertEquals(horizonReviews, horizon.getReviews());
        blaster.addReview(r3);
        blasterReviews.add(r3);
        assertEquals(blasterReviews, blaster.getReviews());
    }

    @Test
    void testCalculateAverageRating() {
        assertEquals(0.0, collins.calculateAverageRating());
        collins.addReview(r1);
        assertEquals(1.0, collins.calculateAverageRating());
        collins.addReview(r2);
        assertEquals(2.0, collins.calculateAverageRating());
        collins.addReview(r3);
        assertEquals(3.0, collins.calculateAverageRating());
    }

    @Test
    void testToString() {
        assertEquals("Trail Name: Collins, Difficulty: Easy, Status: Open, Avg Rating: 0.0",
                collins.toString());
        collins.setStatus(false);
        collins.addReview(r3);
        assertEquals("Trail Name: Collins, Difficulty: Easy, Status: Closed, Avg Rating: 5.0",
                collins.toString());
        assertEquals("Trail Name: Horizon, Difficulty: Intermediate, Status: Open, Avg Rating: 0.0",
                horizon.toString());
        assertEquals("Trail Name: Blaster, Difficulty: Advanced, Status: Open, Avg Rating: 0.0",
                blaster.toString());
    }

    @Test
    void testToJson() {
        collins.addReview(r1);
        collins.addReview(r2);
        collins.addReview(r3);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Collins");
        jsonObject.put("difficulty", "Easy");
        jsonObject.put("isOpen", true);

        JSONArray reviewsJsonArray = new JSONArray();
        reviewsJsonArray.put(r1.toJson());
        reviewsJsonArray.put(r2.toJson());
        reviewsJsonArray.put(r3.toJson());

        jsonObject.put("reviews", reviewsJsonArray);

        assertEquals(jsonObject.toString(), collins.toJson().toString());
    }

    @Test
    void testReviewsToJson() {
        collins.addReview(r1);
        collins.addReview(r2);

        JSONArray expectedArray = new JSONArray();
        expectedArray.put(r1.toJson());
        expectedArray.put(r2.toJson());

        JSONArray actualArray = collins.reviewsToJson();

        assertEquals(expectedArray.toString(), actualArray.toString());
    }


}