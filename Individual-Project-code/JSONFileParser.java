import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileParser implements FileParser {
    @Override
    public List<String[]> parseFile(String filePath) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);

        // Check if the file is empty
        if (fis.available() == 0) {
            throw new IOException("File is empty: " + filePath);
        }

        JSONTokener tokener = new JSONTokener(fis);
        JSONObject root = new JSONObject(tokener);
        JSONArray cardsArray = root.getJSONArray("cards");
        List<String[]> records = new ArrayList<>();

        for (int i = 0; i < cardsArray.length(); i++) {
            JSONObject cardObject = cardsArray.getJSONObject(i);

            String cardNumber = cardObject.has("cardNumber") ? cardObject.getString("cardNumber") : "Unknown";
            String expirationDate = cardObject.has("expirationDate") ? cardObject.getString("expirationDate") : "Unknown";
            String cardHolderName = cardObject.has("cardHolderName") ? cardObject.getString("cardHolderName") : "Unknown";

            records.add(new String[]{cardNumber, expirationDate, cardHolderName});
        }

        fis.close();
        return records;
    }
}
