package model;

import java.util.*;

//A representation of a library containing a collection of books
public class Library {
    private  List<Book> bookList;

    // Constructs an empty collection of books
    public Library() {
        bookList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new book to the collection of books
    public void addBook(Book book) {
        bookList.add(book);
    }

    // MODIFIES: this
    // EFFECTS: removes book from the collection of books
    public void removeBook(Book book) {
        bookList.remove(book);
    }

    // Returns a list of all books in library
    public List<Book> getBooks() {
        return bookList;
    }

    // Returns a list of books in the given genre
    public List<Book> findBookByGenre(String genre) {
        List<Book> genreList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getGenre().equals(genre))
            genreList.add(book);
        }

        return genreList;
    }

    // Returns a list of books by the given auhtor
    public List<Book> findBookByAuthor(String author) {
        List<Book> authorList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getAuthor().equals(author))
            authorList.add(book);
        }

        return authorList;
    }
}
