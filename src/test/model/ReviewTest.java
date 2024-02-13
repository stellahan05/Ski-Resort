package model;

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
}
