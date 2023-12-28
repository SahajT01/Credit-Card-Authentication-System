import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

public class TestXMLFileParser {

    @Test
    public void testParseValidXMLFile() throws Exception {
        XMLFileParser parser = new XMLFileParser();
        List<String[]> data = parser.parseFile("inputFiles/input_file_1.xml");
        assertNotNull(data);
        assertFalse(data.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testParseNonExistentFile() throws Exception {
        XMLFileParser parser = new XMLFileParser();
        parser.parseFile("nonexistent.xml");
    }

    @Test(expected = IOException.class)
    public void testParseEmptyXMLFile() throws Exception {
        XMLFileParser parser = new XMLFileParser();
        List<String[]> data = parser.parseFile("inputFiles/test_file_empty.xml");
        assertNotNull(data);
        assertTrue(data.isEmpty());
    }

    @Test
    public void testParseXMLWithInvalidData() throws Exception {
        XMLFileParser parser = new XMLFileParser();
        List<String[]> data = parser.parseFile("inputFiles/test_file_incorrect.xml");
        assertNotNull(data);
    }
}
