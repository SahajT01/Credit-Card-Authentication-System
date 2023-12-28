import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestDiscoverCCHandler {
    private DiscoverCCHandler handler;
    private CreditCardFactory factory;

    @Before
    public void setUp() {
        factory = new DiscoverCCFactory();
        handler = new DiscoverCCHandler(factory);
    }

    @Test
    public void handleValidDiscoverCard() {
        String[] cardData = {"6011111111111117", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNotNull("Valid Discover card should be handled correctly", card);
        assertTrue("Card should be instance of DiscoverCC", card instanceof DiscoverCC);
    }

    @Test
    public void handleInvalidCardNumber() {
        String[] cardData = {"1234567890123456", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Invalid card number should not be handled by DiscoverCCHandler", card);
    }

    @Test
    public void handleNullCardNumber() {
        String[] cardData = {null, "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Null card number should not be handled by DiscoverCCHandler", card);
    }

    @Test
    public void handleEmptyCardNumber() {
        String[] cardData = {"", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNull("Empty card number should not be handled by DiscoverCCHandler", card);
    }
}
