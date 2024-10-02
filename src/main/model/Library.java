package model;

import java.util.*;

//A representation of a library containing a collection of books
public class Library {
    private List<Book> bookList;

    // Constructs an empty collection of books
    public Library() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a new book to the collection of books
    public void addBook(Book book) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes book from the collection of books
    public void removeBook(Book book) {
        // stub
    }

    // Returns a list of all books in library
    public List<Book> getBooks() {
        return null; // stub
    }

    // Returns a list of books in the given genre
    public List<Book> findBookByGenre(String genre) {
        return null; // stub
    }

    // Returns a list of books by the given auhtor
    public List<Book> findBookByAuthor(String author) {
        return null; // stub
    }
}
