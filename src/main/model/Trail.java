package model;

// Represents a trail having a name, difficulty level, and status (open/closed).
public class Trail {
    private String name;
    private String difficulty;
    private boolean isOpen;

    // REQUIRES: trailName has a non-zero length; trail difficulty is one of: Easy, Intermediate, Advanced
    // EFFECTS: name of trail is set to trailName; difficulty is set to trailDifficulty;
    // status isOpen is set to true by default
    public Trail(String trailName, String trailDifficulty) {
        name = trailName;
        difficulty = trailDifficulty;
        this.isOpen = true;
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

    // MODIFIES: this
    // EFFECTS: sets the status of the trail to isOpen
    public void setStatus(boolean isOpen) {
        this.isOpen = isOpen;
    }

    // EFFECTS: returns a string representation of the details of the trail
    public String toString() {
        String status = isOpen ? "Open" : "Closed";
        return ("Trail Name: " + name + ", Difficulty: " + difficulty
                + ", Status: " + status);
    }
}
