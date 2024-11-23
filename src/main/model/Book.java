package model;

import org.json.JSONObject;
import persistance.Writable;

// Represents a Book having a title, author, genre, reading status, rating, and review
public class Book implements Writable {

    String title; // Book title
    String author; // Book author
    String genre; // Book genre
    String readingStatus; // Book reading status ("not started", "started", "completed")
    int rating; // Book user rating (scale 1 to 5)
    String review; // Book user review

    // Constructs a book
    // EFFECTS: creates a book with given name, author, genre.
    // Initializes the reading status as "not started", and sets rating and review
    // to default values.
    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.readingStatus = "not started"; // defualt status
        this.rating = -1; // default for rating value
        this.review = ""; // default empty review
    }

    // Returns title of book
    public String getTitle() {
        return this.title;
    }

    // Returns genre of book
    public String getGenre() {
        return this.genre;
    }

    // Returns author of book
    public String getAuthor() {
        return this.author;
    }

    // Returns reading status of book
    public String getReadingStatus() {
        return this.readingStatus;
    }

    // Returns rating of book
    public int getRating() {
        return this.rating;
    }

    // Returns review of book
    public String getReview() {
        return this.review;
    }

    // MODIFIES: this
    // EFFECTS: changes the title of book
    public void setTitle(String title) {
        this.title = title;
        EventLog.getInstance().logEvent(new Event("Changed title to book."));
    }

    // MODIFIES: this
    // EFFECTS: changes the genre of book
    public void setGenre(String genre) {
        this.genre = genre;
        EventLog.getInstance().logEvent(new Event("Changed genre to book."));
    }

    // MODIFIES: this
    // EFFECTS: changes the author of book
    public void setAuthor(String author) {
        this.author = author;
        EventLog.getInstance().logEvent(new Event("Changed author to book."));
    }

    // REQUIRES: status to be one of: "not started", "started", or "completed"
    // MODIFIES: this
    // EFFECTS: changes the reading status of book
    public void setReadingStatus(String readingStatus) {
        this.readingStatus = readingStatus;
        if (readingStatus.equals("completed")) {
            EventLog.getInstance().logEvent(new Event("Completed book."));
        } else {
            EventLog.getInstance().logEvent(new Event("Started book."));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the review of book
    public void setReview(String review) {
        this.review = review;
        EventLog.getInstance().logEvent(new Event("Changed review to book."));
    }

    // REQUIRES: rating of book to be between 1 and 5
    // MODIFIES: this
    // EFFECTS: sets the rating of book
    public void setRating(int rating) {
        this.rating = rating;
        EventLog.getInstance().logEvent(new Event("Changed rating to book."));
    }

    // MODIFIES: this
    // EFFECTS: edits book given by user
    public void editBook(String newTitle, String newAuthor,
            String newGenre, int newRating, String newReview) {
        this.setTitle(newTitle);
        this.setAuthor(newAuthor);
        this.setGenre(newGenre);
        this.setRating(newRating);
        this.setReview(newReview);
        EventLog.getInstance().logEvent(new Event("Editing book."));
    }

    // Override toString() for displaying book details
    @Override
    public String toString() {
        String status = this.getReadingStatus();
        String review = this.getReview();
        int rating = this.getRating();
        return "Title: " + title + "\nAuthor: " + author + "\nGenre: " + genre
                + "\nStatus: " + status + "\nRating: " + rating + "\nReview: " + review;
    }

    // Override toJson() for book details
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("genre", genre);
        json.put("status", readingStatus);
        json.put("rating", rating);
        json.put("review", review);
        return json;
    }
}
