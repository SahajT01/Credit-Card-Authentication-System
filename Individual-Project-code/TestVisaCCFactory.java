import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestVisaCCFactory {
    private VisaCCFactory factory;

    @Before
    public void setUp() {
        factory = new VisaCCFactory();
    }

    @Test
    public void createValidVisaCreditCard() {
        String[] cardData = {"4123456789123", "12/24", "John Doe"};
        CreditCard card = factory.createCreditCard(cardData);
        assertNotNull("Credit card should be created for valid data", card);
        assertTrue("Created card should be instance of VisaCC", card instanceof VisaCC);
        assertEquals("Card number should match", "4123456789123", card.getCardNumber());
        assertEquals("Expiration date should match", "12/24", card.getExpirationDate());
        assertEquals("Holder name should match", "John Doe", card.getHolderName());
    }
}
