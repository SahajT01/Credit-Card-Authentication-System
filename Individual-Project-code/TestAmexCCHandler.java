import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestAmexCCHandler {
    private AmexCCHandler handler;
    private CreditCardFactory factory;

    @Before
    public void setUp() {
        factory = new AmexCCFactory();
        handler = new AmexCCHandler(factory);
    }

    @Test
    public void testHandleValidAmexCard() {
        String[] cardData = {"378282246310005", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNotNull("Valid Amex card should be handled correctly", card);
        assertTrue("Card should be instance of AmexCC", card instanceof AmexCC);
    }

    @Test
    public void testHandleInvalidCardNumber() {
        String[] cardData = {"123456789012345", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Invalid card number should not be handled by AmexCCHandler", card);
    }

    @Test
    public void testHandleNullCardNumber() {
        String[] cardData = {null, "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Null card number should not be handled by AmexCCHandler", card);
    }

    @Test
    public void testHandleEmptyCardNumber() {
        String[] cardData = {"", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Empty card number should not be handled by AmexCCHandler", card);
    }
}
