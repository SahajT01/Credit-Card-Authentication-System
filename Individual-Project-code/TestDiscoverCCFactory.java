import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestDiscoverCCFactory {
    private DiscoverCCFactory factory;

    @Before
    public void setUp() {
        factory = new DiscoverCCFactory();
    }

    @Test
    public void createValidDiscoverCreditCard() {
        String[] cardData = {"6011111111111117", "12/24", "John Doe"};
        CreditCard card = factory.createCreditCard(cardData);
        assertNotNull("Credit card should be created for valid data", card);
        assertTrue("Created card should be instance of DiscoverCC", card instanceof DiscoverCC);
        assertEquals("Card number should match", "6011111111111117", card.getCardNumber());
        assertEquals("Expiration date should match", "12/24", card.getExpirationDate());
        assertEquals("Holder name should match", "John Doe", card.getHolderName());
    }
}
