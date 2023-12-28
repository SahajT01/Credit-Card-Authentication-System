import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

public class TestJSONFileParser {

    @Test
    public void testParseValidJSONFile() throws Exception {
        JSONFileParser parser = new JSONFileParser();
        List<String[]> data = parser.parseFile("inputFiles/input_file_1.json");
        assertNotNull(data);
        assertFalse(data.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testParseNonExistentFile() throws Exception {
        JSONFileParser parser = new JSONFileParser();
        parser.parseFile("nonexistent.json");
    }

    @Test(expected = IOException.class)
    public void testParseEmptyJSONFile() throws Exception{
        JSONFileParser parser = new JSONFileParser();
        List<String[]> data = parser.parseFile("inputFiles/test_file_empty.json");
        assertNotNull(data);
        assertTrue(data.isEmpty());
    }

    @Test
    public void testParseJSONWithInvalidData() throws Exception {
        JSONFileParser parser = new JSONFileParser();
        List<String[]> data = parser.parseFile("inputFiles/test_file_incorrect.json");
        assertNotNull(data);
    }
}
