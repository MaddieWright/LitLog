package persistance;

import model.Book;
import model.Library;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader extends TestJson{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library lib = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Library lib = reader.read();
            // assertEquals("My Library", lib.getName());
            assertEquals(0, lib.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Library lib = reader.read();
            List<Book> books = lib.getBooks();
            assertEquals(2, books.size());
            checkBook("Harry", "Rowling", "Horror",  books.get(0));
            checkBook("Lies", "Mitten", "Mystery", books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}