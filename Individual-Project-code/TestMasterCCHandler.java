import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestMasterCCHandler {
    private MasterCCHandler handler;
    private CreditCardFactory factory;

    @Before
    public void setUp() {
        factory = new MasterCCFactory();
        handler = new MasterCCHandler(factory);
    }

    @Test
    public void handleValidMasterCard() {
        String[] cardData = {"5555555555554444", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNotNull("Valid MasterCard should be handled correctly", card);
        assertTrue("Card should be instance of MasterCC", card instanceof MasterCC);
    }

    @Test
    public void handleInvalidCardNumber() {
        String[] cardData = {"123456789012345", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Invalid card number should not be handled by MasterCCHandler", card);
    }

    @Test
    public void handleNullCardNumber() {
        String[] cardData = {null, "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Null card number should not be handled by MasterCCHandler", card);
    }

    @Test
    public void handleEmptyCardNumber() {
        String[] cardData = {"", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Empty card number should not be handled by MasterCCHandler", card);
    }

    @Test
    public void handleNonMasterCardNumber() {
        String[] cardData = {"4111111111111111", "12/24", "John Doe"}; // Visa card number
        CreditCard card = handler.handle(cardData);
        assertNull("Non-MasterCard number should not be handled by MasterCCHandler", card);
    }
}
