package persistance;

import model.Book;
import model.Library;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads library from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Library read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses library from JSON object and returns it
    private Library parseLibrary(JSONObject jsonObject) {
        Library lib = new Library();
        addBooks(lib, jsonObject);
        return lib;
    }

    // MODIFIES: lib
    // EFFECTS: parses books from JSON object and adds them to library
    private void addBooks(Library lib, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(lib, nextBook);
        }
    }

    // MODIFIES: lib
    // EFFECTS: parses book from JSON object and adds it to library
    private void addBook(Library lib, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        String genre = jsonObject.getString("genre");
        String status = jsonObject.getString("status");
        int rating = jsonObject.getInt("rating");
        String review = jsonObject.getString("review");

        Book book = new Book(title, author, genre);
        book.setReadingStatus(status);
        book.setRating(rating);
        book.setReview(review);
        lib.addBook(book);
    }
}
