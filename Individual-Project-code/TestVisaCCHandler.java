import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestVisaCCHandler {
    private VisaCCHandler handler;
    private CreditCardFactory factory;

    @Before
    public void setUp() {
        factory = new VisaCCFactory();
        handler = new VisaCCHandler(factory);
    }

    @Test
    public void handleValidVisaCard() {
        String[] cardData = {"4111111111111111", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNotNull("Valid Visa card should be handled correctly", card);
        assertTrue("Card should be instance of VisaCC", card instanceof VisaCC);
    }

    @Test
    public void handleInvalidCardNumber() {
        String[] cardData = {"5555555555554444", "12/24", "John Doe"}; // MasterCard number
        CreditCard card = handler.handle(cardData);
        assertNull("Invalid card number should not be handled by VisaCCHandler", card);
    }

    @Test
    public void handleNullCardNumber() {
        String[] cardData = {null, "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Null card number should not be handled by VisaCCHandler", card);
    }

    @Test
    public void handleEmptyCardNumber() {
        String[] cardData = {"", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Empty card number should not be handled by VisaCCHandler", card);
    }

    @Test
    public void handleInvalidLengthCardNumber() {
        String[] cardData = {"4111111", "12/24", "John Doe"}; // Short number
        CreditCard card = handler.handle(cardData);
        assertNull("Invalid length card number should not be handled by VisaCCHandler", card);
    }
}
