package persistence;

import model.Review;
import model.Trail;
import model.Trails;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads trails from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads trails from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Trails read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrails(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses trails from JSON object and returns it
    private Trails parseTrails(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Trails trails = new Trails();
        addTrails(trails, jsonObject);
        return trails;
    }

    // MODIFIES: trails
    // EFFECTS: parses trails from JSON object and adds them to trails
    private void addTrails(Trails trails, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("trails");
        for (Object json : jsonArray) {
            JSONObject nextTrail = (JSONObject) json;
            addTrail(trails, nextTrail);
        }
    }

    // MODIFIES: trails
    // EFFECTS: parses trail from JSON object and adds it to trails
    private void addTrail(Trails trails, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String difficulty = jsonObject.getString("difficulty");
        boolean isOpen = jsonObject.getBoolean("isOpen");
        Trail trail = new Trail(name, difficulty);
        addReviews(trail, jsonObject);
        trails.addTrail(trail);
    }

    // MODIFIES: trail
    // EFFECTS: parses reviews from JSON object and adds them to trail
    private void addReviews(Trail trail, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("reviews");
        for (Object json : jsonArray) {
            JSONObject nextReview = (JSONObject) json;
            addReview(trail, nextReview);
        }
    }

    // MODIFIES: trail
    // EFFECTS: parses review from JSON object and adds it to trail
    private void addReview(Trail trail, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        String author = jsonObject.getString("author");
        int rating = jsonObject.getInt("rating");
    }
}
