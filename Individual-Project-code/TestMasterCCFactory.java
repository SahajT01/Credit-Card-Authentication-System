import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestMasterCCFactory {
    private MasterCCFactory factory;

    @Before
    public void setUp() {
        factory = new MasterCCFactory();
    }

    @Test
    public void createValidMasterCreditCard() {
        String[] cardData = {"5567894523129089", "12/24", "John Doe"};
        CreditCard card = factory.createCreditCard(cardData);
        assertNotNull("Credit card should be created for valid data", card);
        assertTrue("Created card should be instance of MasterCC", card instanceof MasterCC);
        assertEquals("Card number should match", "5567894523129089", card.getCardNumber());
        assertEquals("Expiration date should match", "12/24", card.getExpirationDate());
        assertEquals("Holder name should match", "John Doe", card.getHolderName());
    }
}
