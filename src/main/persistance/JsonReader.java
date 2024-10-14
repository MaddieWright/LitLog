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

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        // stub
    }

     // EFFECTS: reads library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Library read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses library from JSON object and returns it
    private Library parseLibrary(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: lib
    // EFFECTS: parses books from JSON object and adds them to library
    private void addBooks(Library lib, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: lib
    // EFFECTS: parses book from JSON object and adds it to library
    private void addBook(Library lib, JSONObject jsonObject) {
        // stub
    }
}
