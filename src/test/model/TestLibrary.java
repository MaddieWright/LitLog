package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLibrary {
    private Library testLibrary;
    private Book testBook;
    private Book testBook2;


    @BeforeEach
    void runBefore() {
        testLibrary = new Library();
        testBook = new Book("Harry Potter", "J.K. Rowling", "Fantasy");
        testBook2 = new Book("To Kill a Mockingbird", "Harper Lee", "Fiction");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testLibrary.getSize());
        assertEquals(0, testLibrary.getBooks().size());
    }

    @Test
    void testAddBook() {
        testLibrary.addBook(testBook);
        assertEquals(1, testLibrary.getBooks().size());
        assertTrue(testLibrary.getBooks().contains(testBook));

        testLibrary.addBook(testBook2);
        assertEquals(2, testLibrary.getBooks().size());
        assertTrue(testLibrary.getBooks().contains(testBook2));
    }

    @Test
    void testRemoveBook() {
        testLibrary.addBook(testBook);
        testLibrary.addBook(testBook2);

        testLibrary.removeBook(testBook);
        assertEquals(1, testLibrary.getBooks().size());
        assertFalse(testLibrary.getBooks().contains(testBook));
        assertTrue(testLibrary.getBooks().contains(testBook2));
    }

    @Test
    void testFindBookByGenre() {
        testLibrary.addBook(testBook);
        testLibrary.addBook(testBook2);

        List<Book> fictionBooks = testLibrary.findBookByGenre("Fantasy");
        assertTrue(fictionBooks.contains(testBook));
        assertFalse(fictionBooks.contains(testBook2));
    }

    @Test
    void testFindBookByAuthor() {
        testLibrary.addBook(testBook);
        testLibrary.addBook(testBook2);

        List<Book> rowlingBooks = testLibrary.findBookByAuthor("J.K. Rowling");
        assertTrue(rowlingBooks.contains(testBook));
        assertFalse(rowlingBooks.contains(testBook2));
    }
}
