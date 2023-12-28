import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFileParser implements FileParser {
    @Override
    public List<String[]> parseFile(String filePath) throws Exception {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    records.add(data);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: An exception occurred - " + e.getMessage());
            throw e;
        }
        return records;
    }
}
