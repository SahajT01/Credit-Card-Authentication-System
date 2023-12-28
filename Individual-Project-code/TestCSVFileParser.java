import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

public class TestCSVFileParser {

    @Test
    public void testParseValidCSVFile() throws Exception {
        CSVFileParser parser = new CSVFileParser();
        List<String[]> data = parser.parseFile("inputFiles/input_file_1.csv");
        assertNotNull(data);
        assertFalse(data.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testParseNonExistentFile() throws Exception {
        CSVFileParser parser = new CSVFileParser();
        parser.parseFile("nonexistent.csv");
    }

    @Test
    public void testParseEmptyCSVFile() throws Exception {
        CSVFileParser parser = new CSVFileParser();
        List<String[]> data = parser.parseFile("inputFiles/test_file_empty.csv");
        assertNotNull(data);
        assertTrue(data.isEmpty());
    }

    @Test
    public void testParseCSVWithInvalidData() throws Exception {
        CSVFileParser parser = new CSVFileParser();
        List<String[]> data = parser.parseFile("inputFiles/test_file_incorrect.csv");
        assertNotNull(data);
    }
}
