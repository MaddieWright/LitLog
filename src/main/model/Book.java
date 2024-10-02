package model;

// Represents a Book having a title, author, genre, reading status, rating, and review
public class Book {
    
    String title;           // Book title
    String author;          // Book author
    String genre;           // Book genre
    String readingStatus;   // Book reading status ("not started", "started", "completed")
    int rating;             // Book user rating (scale 1 to 5)
    String review;          // Book user review

    // Constructs a book
    // EFFECTS: creates a book with given name, author, genre.
    // initializes the reading status as "not started", and sets rating and review to default values.
    public Book(String title, String author, String genre) {
       // stub
    }

    //Returns title of book
    public String getTitle() {
        return null; // stub
    }

    // Returns genre of book
    public String getGenre() {
        return null; //stub
    }

    // Returns author of book
    public String getAuthor() {
        return null; //stub
    }

    // Returns reading status of book
    public String getReadingStatus() {
        return null; //stub
    }

    // Returns rating of book
    public int getRating() {
        return 0;
    }

    // Returns review of book
    public String getReview() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: changes the title of book
    public void setTitle(String genre) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: changes the genre of book
    public void setGenre(String genre) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: changes the author of book
    public void setAuthor(String auhtor) {
        // stub
    }

    // REQUIRES: status to be one of: "not started", "started", or "completed"
    // MODIFIES: this
    // EFFECTS: changes the reading status of book
    public void setReadingStatus(String readingStatus) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: sets the review of book
    public void setReview(String review) {
        // stub
    }

    // REQUIRES: rating of book to be between 1 and 5
    // MODIFIES: this
    // EFFECTS: sets the rating of book
    public void setRating(int rating) {
        // stub
    }
}
