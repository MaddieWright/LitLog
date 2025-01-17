package persistance;

import model.Book;
import model.Library;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonWriter extends TestJson {

    @Test
    void testWriterInvalidFile() {
        try {
            Library lib = new Library();
            JsonWriter writer = new JsonWriter("./lib/data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            Library lib = new Library();
            JsonWriter writer = new JsonWriter("./lib/data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./lib/data/testWriterEmptyLibrary.json");
            lib = reader.read();
            assertEquals(0, lib.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLibrary() {
        try {
            Library lib = new Library();
            Book book1 = new Book("Harry", "Rowling", "Horror");
            Book book2 = new Book("Lies", "Mitten", "Mystery");
            lib.addBook(book1);
            lib.addBook(book2);
            JsonWriter writer = new JsonWriter("./lib/data/testWriterGeneralLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./lib/data/testWriterGeneralLibrary.json");
            lib = reader.read();
            // assertEquals("My library", lib.getName());
            List<Book> books = lib.getBooks();
            assertEquals(2, books.size());
            checkBook("Harry", "Rowling", "Horror",books.get(0));
            checkBook("Lies", "Mitten", "Mystery", books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
