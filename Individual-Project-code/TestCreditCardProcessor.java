import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class TestCreditCardProcessor {

    // createOutputFileName()
    @Test
    public void testCreateOutputFileNameWithExtension() {
        assertEquals("input_output.csv", CreditCardProcessor.createOutputFileName("input.csv"));
    }

    @Test
    public void testCreateOutputFileNameWithoutExtension() {
        assertEquals("input_output", CreditCardProcessor.createOutputFileName("input"));
    }

    @Test
    public void testCreateOutputFileNameEmpty() {
        assertEquals("_output", CreditCardProcessor.createOutputFileName(""));
    }

    // getFileExtension()
    @Test
    public void testGetFileExtensionWithExtension() {
        assertEquals(".csv", CreditCardProcessor.getFileExtension("input.csv"));
    }

    @Test
    public void testGetFileExtensionWithoutExtension() {
        assertEquals("", CreditCardProcessor.getFileExtension("input"));
    }

    @Test
    public void testGetFileExtensionEmpty() {
        assertEquals("", CreditCardProcessor.getFileExtension(""));
    }

    // createChainOfHandlers()
    @Test
    public void testHandlerOrder() {
        CreditCardHandler chain = CreditCardProcessor.createChainOfHandlers();

        assertTrue(chain instanceof VisaCCHandler);
        CreditCardHandler next = chain.getSuccessor();

        assertTrue(next instanceof MasterCCHandler);
        next = next.getSuccessor();

        assertTrue(next instanceof AmexCCHandler);
        next = next.getSuccessor();

        assertTrue(next instanceof DiscoverCCHandler);
        next = next.getSuccessor();

        assertTrue(next instanceof UnknownCCHandler);
        assertNull(next.getSuccessor());
    }


    @Test
    public void testHandlerDelegation() {
        CreditCardHandler chain = CreditCardProcessor.createChainOfHandlers();

        // Assuming we have methods in handlers to test their validation logic
        assertTrue(chain.canHandle("4123456789123999"));// discover
        assertFalse(chain.canHandle("5167894523129089"));// mastercard
        assertTrue(chain.getSuccessor().canHandle("5167894523129089"));
        assertFalse(chain.canHandle("377856341908126"));// amex
        assertTrue(chain.getSuccessor().getSuccessor().canHandle("377856341908126"));
        assertFalse(chain.canHandle("6011111100007756"));// discover
        assertTrue(chain.getSuccessor().getSuccessor().getSuccessor().canHandle("6011111100007756"));
        assertFalse(chain.canHandle(""));// unknown
        assertTrue(chain.getSuccessor().getSuccessor().getSuccessor().getSuccessor().canHandle(""));
    }

    // getCardType()
    @Test
    public void testGetCardTypeVisa() {
        CreditCard visaCard = new VisaCC("4123456789123456", "01/23", "John Doe");
        assertEquals("Visa", CreditCardProcessor.getCardType(visaCard));
    }

    @Test
    public void testGetCardTypeMasterCard() {
        CreditCard masterCard = new MasterCC("5123456789123456", "02/24", "Jane Doe");
        assertEquals("MasterCard", CreditCardProcessor.getCardType(masterCard));
    }

    @Test
    public void testGetCardTypeAmex() {
        CreditCard amexCard = new AmexCC("341234567891234", "03/25", "Alice Doe");
        assertEquals("AmericanExpress", CreditCardProcessor.getCardType(amexCard));
    }

    @Test
    public void testGetCardTypeDiscover() {
        CreditCard discoverCard = new DiscoverCC("6011123456789123", "04/26", "Bob Doe");
        assertEquals("Discover", CreditCardProcessor.getCardType(discoverCard));
    }

    @Test
    public void testGetCardTypeInvalidNonNumeric() {
        CreditCard invalidCard = new UnknownCC("ABCDE", "05/27", "Charlie Doe");
        assertEquals("Invalid: non-numeric characters", CreditCardProcessor.getCardType(invalidCard));
    }

    @Test
    public void testGetCardTypeInvalidNull() {
        assertEquals("Invalid: empty/null card number", CreditCardProcessor.getCardType(null));
    }

    @Test
    public void testGetCardTypeInvalidEmpty() {
        CreditCard emptyCard = new UnknownCC("", "06/28", "Diana Doe");
        assertEquals("Invalid: empty/null card number", CreditCardProcessor.getCardType(emptyCard));
    }

    @Test
    public void testGetCardTypeInvalidNumber() {
        CreditCard unknownCard = new UnknownCC("1234567890123456", "07/29", "Ethan Doe");
        assertEquals("Invalid: not a possible card number", CreditCardProcessor.getCardType(unknownCard));
    }

    //processCreditCards()
    @Test
    public void testProcessCreditCardsWithBlankFile() {
        String testBlankCSV = "inputFiles/test_file_empty.csv";
        List<CreditCard> creditCards = CreditCardProcessor.processCreditCards(testBlankCSV);
        assertTrue(creditCards.isEmpty());
    }

    @Test
    public void testProcessCreditCardsWithInvalidData() {
        String testInvalidCSV = "inputFiles/test_file_incorrect.csv";
        List<CreditCard> creditCards = CreditCardProcessor.processCreditCards(testInvalidCSV);
        assertFalse(creditCards.isEmpty());
        for (CreditCard card : creditCards) {
            assertTrue(card instanceof UnknownCC);
        }
    }
}
