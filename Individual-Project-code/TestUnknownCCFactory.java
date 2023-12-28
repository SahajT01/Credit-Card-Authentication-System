import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestUnknownCCFactory {
    private UnknownCCFactory factory;

    @Before
    public void setUp() {
        factory = new UnknownCCFactory();
    }

    @Test
    public void createValidUnknownCreditCard() {
        String[] cardData = {"4", "12/24", "John Doe"};
        CreditCard card = factory.createCreditCard(cardData);
        assertNotNull("Credit card should be created for valid data", card);
        assertTrue("Created card should be instance of UnknownCC", card instanceof UnknownCC);
        assertEquals("Card number should match", "4", card.getCardNumber());
        assertEquals("Expiration date should match", "12/24", card.getExpirationDate());
        assertEquals("Holder name should match", "John Doe", card.getHolderName());
    }
}
