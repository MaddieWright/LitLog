package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistance.Writable;

//A representation of a library containing a collection of books
public class Library implements Writable {
    private List<Book> bookList;

    // Constructs an empty collection of books
    public Library() {
        bookList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new book to the collection of books
    public void addBook(Book book) {
        EventLog.getInstance().logEvent(new Event("Added book to Library."));
        bookList.add(book);
    }

    // MODIFIES: this
    // EFFECTS: removes book from the collection of books
    public void removeBook(Book book) {
        EventLog.getInstance().logEvent(new Event("Removed book to Library."));
        bookList.remove(book);
    }

    // Returns a list of all books in library
    public List<Book> getBooks() {
        EventLog.getInstance().logEvent(new Event("Display books in Library."));
        return bookList;
    }

    // Returns a list of books in the given genre
    public List<Book> findBookByGenre(String genre) {
        List<Book> genreList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getGenre().equals(genre)) {
                genreList.add(book);
            }
        }
        EventLog.getInstance().logEvent(new Event("Search books in Library for " + genre + " genre."));

        return genreList;
    }

    // Returns a list of books by the given auhtor
    public List<Book> findBookByAuthor(String author) {
        List<Book> authorList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                authorList.add(book);
            }
        }
        EventLog.getInstance().logEvent(new Event("Search books in Library for " + author + " author."));

        return authorList;
    }

    // Returns list of books in library size
    public int getSize() {
        return bookList.size();
    }

    // Override toJson() for library details
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns books in this library as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : bookList) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}
