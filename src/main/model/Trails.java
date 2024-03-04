package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

// Represents a list of Trails from which you can add and remove trails.
public class Trails implements Writable {
    private List<Trail> trails;

    // EFFECTS: a new list of trails currently containing no trails is created
    public Trails() {
        trails = new ArrayList<>();
    }

    // EFFECTS: returns the list of all trails
    public List<Trail> getTrails() {
        return trails;
    }

    // REQUIRES: !(trails.contains(trail)), ignoring case
    // MODIFIES: this
    // EFFECTS: adds given trail to the list
    public void addTrail(Trail trail) {
        trails.add(trail);
    }

    // MODIFIES: this
    // EFFECTS: removes given trail from the list
    public void removeTrail(Trail trail) {
        trails.remove(trail);
    }

    // EFFECTS: returns a list of all trail names
    public List<String> getAllTrailNames() {
        List<String> trailNames = new ArrayList<>();
        for (Trail trail : trails) {
            trailNames.add(trail.getName());
        }
        return trailNames;
    }

    // EFFECTS: returns the number of trails in the list
    public int numOfTrails() {
        return trails.size();
    }

    // EFFECTS: searches for a trail with the given name, ignoring case, in the list of trails
    // returns the trail if found, otherwise returns null
    public Trail findTrail(String name) {
        for (Trail trail : trails) {
            if (trail.getName().equalsIgnoreCase(name)) {
                return trail;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("trails", trailsToJson());
        return json;
    }

    // EFFECTS: returns trails in this list of trails as a JSON array
    protected JSONArray trailsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Trail trail : trails) {
            jsonArray.put(trail.toJson());
        }

        return jsonArray;
    }
}