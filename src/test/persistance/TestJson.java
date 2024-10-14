package persistance;

import model.Book;

import static org.junit.jupiter.api.Assertions.*;

public class TestJson {
    protected void checkBook(String title, String author, String genre, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(genre, book.getGenre());
        assertEquals(author, book.getAuthor());
        assertEquals("not started", book.getReadingStatus());
        assertEquals(-1, book.getRating());
        assertEquals("", book.getReview());
    }
}
