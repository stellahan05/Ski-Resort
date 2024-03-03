package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a trail having a name, difficulty level, and status (open/closed).
public class Trail implements Writable {
    private String name;
    private String difficulty;
    private boolean isOpen;
    private List<Review> reviews;

    // REQUIRES: trailName has a non-zero length; trail difficulty is one of: Easy, Intermediate, Advanced
    // EFFECTS: name of trail is set to trailName; difficulty is set to trailDifficulty;
    // status isOpen is set to true by default; reviews is initialized as an empty list
    public Trail(String trailName, String trailDifficulty) {
        name = trailName;
        difficulty = trailDifficulty;
        this.isOpen = true;
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setStatus(boolean isOpen) {
        this.isOpen = isOpen;
    }

    // MODIFIES: this
    // EFFECTS: adds a review to reviews
    public void addReview(Review review) {
        reviews.add(review);
    }

    // EFFECTS: calculates the average rating of the trail
    public double calculateAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return (double) sum / reviews.size();
    }

    // EFFECTS: returns a string representation of the details of the trail
    public String toString() {
        String status = isOpen ? "Open" : "Closed";
        return ("Trail Name: " + name + ", Difficulty: " + difficulty
                + ", Status: " + status + ", Avg Rating: " + calculateAverageRating());
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("difficulty", difficulty);
        json.put("isOpen", isOpen);
        json.put("reviews", reviewsToJson());
        return json;
    }

    // EFFECTS: returns reviews of this trail as a JSON array
    private JSONArray reviewsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Review r : reviews) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
