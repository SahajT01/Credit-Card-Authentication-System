import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLFileParser implements FileParser {
    @Override
    public List<String[]> parseFile(String filePath) throws Exception {
        File xmlFile = new File(filePath);

        // Check if the file is empty
        if (xmlFile.length() == 0) {
            throw new IOException("File is empty: " + filePath);
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("CARD"); // Assuming "CARD" is the tag name
        List<String[]> records = new ArrayList<>();

        if (nList.getLength() == 0) {
            throw new IOException("No CARD elements found in the file: " + filePath);
        }

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String cardNumber = element.getElementsByTagName("CARD_NUMBER").item(0).getTextContent();
                String expirationDate = element.getElementsByTagName("EXPIRATION_DATE").item(0).getTextContent();
                String cardHolderName = element.getElementsByTagName("CARD_HOLDER_NAME").item(0).getTextContent();
                records.add(new String[]{cardNumber, expirationDate, cardHolderName});
            }
        }

        return records;
    }
}
