package model;

public class Review {

    private String description;
    private String author;
    private int rating;

    public Review(int rating, String description, String author) {
        this.description = description;
        this.author = author;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    // EFFECTS: returns a string representation of the details of the review
    public String toString() {
        return ("Rating: " + rating + ", Description: " + description + ", Author: " + author);
    }
}
