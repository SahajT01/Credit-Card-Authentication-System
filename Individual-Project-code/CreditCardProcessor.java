import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class CreditCardProcessor {
    public static void main(String[] args) {
        //String inputFile = "inputFiles/test_file_empty.xml";
        String inputFile = "Individual-Project-code/inputFiles/demo2_inp.csv";
        //String inputFile = "inputFiles/input_file_1.json";
        //String inputFile = "inputFiles/input_file_1.xml";

        try {
            List<CreditCard> creditCards = processCreditCards(inputFile);
            String outputFile = createOutputFileName(inputFile);
            writeOutputToFile(creditCards, outputFile, getFileExtension(inputFile));
        } catch (Exception e) {
            System.out.println("Error: An exception occurred - " + e.getMessage());
        }
    }

    static String createOutputFileName(String inputFile) {
        try {
            if (inputFile.contains(".")) {
                String baseName = inputFile.substring(0, inputFile.lastIndexOf('.'));
                return baseName + "_output" + getFileExtension(inputFile);
            }

        } catch (Exception e) {
            System.out.println("Error: An exception occurred - " + e.getMessage());
        }
        return inputFile + "_output";
    }

    static String getFileExtension(String fileName) {
        if (fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf('.'));
        }
        return "";
    }

    static void writeOutputToFile(List<CreditCard> creditCards, String outputFile, String fileExtension) {
        try {
            switch (fileExtension) {
                case ".json":
                    writeJSONOutput(creditCards, outputFile);
                    break;
                case ".xml":
                    writeXMLOutput(creditCards, outputFile);
                    break;
                case ".csv":
                default:
                    writeCSVOutput(creditCards, outputFile);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: An exception occurred - " + e.getMessage());
        }
    }

    static void writeJSONOutput(List<CreditCard> creditCards, String outputFile) {
        JSONArray cardArray = new JSONArray();
        for (CreditCard card : creditCards) {
            JSONObject cardObject = new JSONObject();
            // Use an empty string for card number if the card is null
            String cardNumber = (card != null) ? card.getCardNumber() : "";
            String cardType = getCardType(card);  // This method handles null cases

            cardObject.put("cardType", cardType);
            cardObject.put("cardNumber", cardNumber);
            cardArray.put(cardObject);
        }

        JSONObject outputObject = new JSONObject();
        outputObject.put("cards", cardArray);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(outputObject.toString(4)); // Indentation for readability
        } catch (Exception e) {
            System.out.println("Error: An exception occurred - " + e.getMessage());
        }
    }

    static void writeCSVOutput(List<CreditCard> creditCards, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("cardNumber,cardType\n");
            for (CreditCard card : creditCards) {
                String cardNumber = (card != null) ? card.getCardNumber() : "";
                writer.write(cardNumber + "," + getCardType(card));
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error: An exception occurred - " + e.getMessage());
        }
    }

    static void writeXMLOutput(List<CreditCard> creditCards, String outputFile) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<CARDS>\n");

        for (CreditCard card : creditCards) {
            String cardNumber = (card != null) ? card.getCardNumber() : "";
            xmlBuilder.append("    <CARD>\n");
            xmlBuilder.append("        <CARD_NUMBER>").append(cardNumber).append("</CARD_NUMBER>\n");
            xmlBuilder.append("        <CARD_TYPE>").append(getCardType(card)).append("</CARD_TYPE>\n");
            xmlBuilder.append("    </CARD>\n");
        }

        xmlBuilder.append("</CARDS>");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(xmlBuilder.toString());
        } catch (Exception e) {
            System.out.println("Error: An exception occurred - " + e.getMessage());
        }
    }


    public static List<CreditCard> processCreditCards(String filePath) {
        List<CreditCard> creditCards = new ArrayList<>();
        CreditCardHandler chain = createChainOfHandlers();

        FileParser parser = FileParserFactory.createFileParser(filePath);
            try {
                List<String[]> parsedData = parser.parseFile(filePath);
                for (String[] cardData : parsedData) {
                    if (cardData.length == 3) {
                        CreditCard card = chain.handle(cardData);
                        creditCards.add(card);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: An exception occurred - " + e.getMessage());
            }

        return creditCards;
    }

    static CreditCardHandler createChainOfHandlers() {
        CreditCardHandler visaHandler = new VisaCCHandler(new VisaCCFactory());
        CreditCardHandler masterHandler = new MasterCCHandler(new MasterCCFactory());
        CreditCardHandler amexHandler = new AmexCCHandler(new AmexCCFactory());
        CreditCardHandler discoverHandler = new DiscoverCCHandler(new DiscoverCCFactory());
        CreditCardHandler unknownHandler = new UnknownCCHandler(new UnknownCCFactory());
        visaHandler.setSuccessor(masterHandler);
        masterHandler.setSuccessor(amexHandler);
        amexHandler.setSuccessor(discoverHandler);
        discoverHandler.setSuccessor(unknownHandler);
        return visaHandler; // Return the first handler in the chain
    }

    static String getCardType(CreditCard card) {
        if (card == null || card.getCardNumber() == null || card.getCardNumber().isEmpty() || card.getCardNumber().equals("Unknown")) {
            return "Invalid: empty/null card number";
        }
        if (!card.getCardNumber().matches("\\d+")) {
            return "Invalid: non-numeric characters";
        }
        if (card instanceof VisaCC) {
            return "Visa";
        } else if (card instanceof MasterCC) {
            return "MasterCard";
        } else if (card instanceof AmexCC) {
            return "AmericanExpress";
        } else if (card instanceof DiscoverCC) {
            return "Discover";
        } else {
            return "Invalid: not a possible card number";
        }
    }
}
