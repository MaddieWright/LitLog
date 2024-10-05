package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBook {
    private Book testBook;
    
    @BeforeEach
    void runBefore() {
        testBook = new Book("Harry Potter", "J.K. Rowling", "Fantasy");
    }

    @Test
    void testConstructor() {
        assertEquals("Harry Potter", testBook.getTitle());
        assertEquals("J.K. Rowling", testBook.getAuthor());
        assertEquals("Fantasy", testBook.getGenre());
        assertEquals("not started", testBook.getReadingStatus());
        assertEquals(-1, testBook.getRating());
        assertEquals("", testBook.getReview());
    }

    // Test Setters/Getters

    @Test
    void testSetTitle() {
        testBook.setTitle("The Silent Patient");
        assertEquals("The Silent Patient", testBook.getTitle());
    }

    @Test
    void testSetGenre() {
        testBook.setGenre("Mystery");
        assertEquals("Mystery", testBook.getGenre());   
    }

    @Test
    void testSetAuthor() {
        testBook.setAuthor("Alex Michaelides");
        assertEquals("Alex Michaelides", testBook.getAuthor());
    }

    @Test
    void testSetReadingStatus() {
        testBook.setReadingStatus("in progress");
        assertEquals("in progress", testBook.getReadingStatus());

        testBook.setReadingStatus("completed");
        assertEquals("completed", testBook.getReadingStatus());    
    }

    @Test
    void testSetRating() {
        testBook.setRating(1);
        assertEquals(1, testBook.getRating()); 

        testBook.setRating(4);
        assertEquals(4, testBook.getRating());     
    }

    @Test
    void testSetReview() {
        testBook.setReview("This book is really good");
        assertEquals("This book is really good", testBook.getReview()); 
    }
}
