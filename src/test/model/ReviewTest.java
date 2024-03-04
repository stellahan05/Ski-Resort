package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Review
public class ReviewTest {
    private Review r1;
    private Review r2;
    private Review r3;

    @BeforeEach
    void runBefore() {
        r1 = new Review(1, "Super icy conditions", "Stella");
        r2 = new Review(3, "Decent ride.", "Han");
        r3 = new Review(5, "My favourite trail.", "d");
    }

    @Test
    void testGetRating() {
        assertEquals(1, r1.getRating());
        assertEquals(3, r2.getRating());
        assertEquals(5, r3.getRating());
    }

    @Test
    void testGetDescription() {
        assertEquals("Super icy conditions", r1.getDescription());
        assertEquals("Decent ride.", r2.getDescription());
        assertEquals("My favourite trail.", r3.getDescription());
    }

    @Test
    void testGetAuthor() {
        assertEquals("Stella", r1.getAuthor());
        assertEquals("Han", r2.getAuthor());
        assertEquals("d", r3.getAuthor());
    }

    @Test
    void testToString() {
        assertEquals("Rating: 1, Description: Super icy conditions, Author: Stella", r1.toString());
    }

    @Test
    void testToJson() {
        JSONObject object1 = new JSONObject();
        object1.put("rating", 1);
        object1.put("description", "Super icy conditions");
        object1.put("author", "Stella");

        JSONObject object2 = new JSONObject();
        object2.put("rating", 3);
        object2.put("description", "Decent ride.");
        object2.put("author", "Han");

        JSONObject object3 = new JSONObject();
        object3.put("rating", 5);
        object3.put("description", "My favourite trail.");
        object3.put("author", "d");

        assertEquals(object1.toString(), r1.toJson().toString());
        assertEquals(object2.toString(), r2.toJson().toString());
        assertEquals(object3.toString(), r3.toJson().toString());
    }
}
