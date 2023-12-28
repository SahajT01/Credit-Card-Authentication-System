import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestUnknownCCHandler {
    private UnknownCCHandler handler;
    private CreditCardFactory factory;

    @Before
    public void setUp() {
        factory = new UnknownCCFactory();
        handler = new UnknownCCHandler(factory);
    }

    @Test
    public void handleUnknownCard() {
        String[] cardData = {"0000000000000000", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNotNull("Unknown card should be handled", card);
        assertTrue("Card should be instance of UnknownCC", card instanceof UnknownCC);
    }

    @Test
    public void handleNullCardNumber() {
        String[] cardData = {null, "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNotNull("Null card number should be handled as UnknownCC", card);
        assertTrue("Card should be instance of UnknownCC", card instanceof UnknownCC);
    }

    @Test
    public void handleEmptyCardNumber() {
        String[] cardData = {"", "12/24", "John Doe"};
        CreditCard card = handler.handle(cardData);
        assertNotNull("Empty card number should be handled as UnknownCC", card);
        assertTrue("Card should be instance of UnknownCC", card instanceof UnknownCC);
    }
}
