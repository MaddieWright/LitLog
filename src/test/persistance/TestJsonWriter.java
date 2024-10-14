package persistance;

import model.Book;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonWriter extends TestJson{

    @Test
    void testWriterInvalidFile() {
        try {
            Library lib = new Library();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Library lib = new Library();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            lib = reader.read();
            // assertEquals("My library", lib.getName());
            assertEquals(0, lib.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
