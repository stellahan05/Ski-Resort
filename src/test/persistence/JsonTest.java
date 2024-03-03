package persistence;

import model.Review;
import model.Trail;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkTrail(String name, String difficulty, boolean isOpen, List<Review> reviews, Trail trail) {
        assertEquals(name, trail.getName());
        assertEquals(difficulty, trail.getDifficulty());
        assertEquals(isOpen, trail.isOpen());
        assertEquals(reviews, trail.getReviews());
    }

    protected void checkReview(String description, String author, int rating, Review review) {
        assertEquals(description, review.getDescription());
        assertEquals(author, review.getAuthor());
        assertEquals(rating, review.getRating());
    }

}
