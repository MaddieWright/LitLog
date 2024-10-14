package persistance;

import model.Book;

import static org.junit.jupiter.api.Assertions.*;

public class TestJson {
    protected void checkBook(String title, String genre, String author,
            int rating, String review, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(genre, book.getGenre());
        assertEquals(author, book.getAuthor());
        assertEquals(rating, book.getRating());
        assertEquals(review, book.getReview());
    }
}
