import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestAmexCCFactory {

    private AmexCCFactory factory;

    @Before
    public void setUp() {
        factory = new AmexCCFactory();
    }
    @Test
    public void testCreateCreditCard() {
        String cardNumber = "377856341908126";
        String expirationDate = "12/24";
        String holderName = "John Doe";

        CreditCard card = factory.createCreditCard(new String[]{cardNumber, expirationDate, holderName});

        assertNotNull("The card should not be null", card);
        assertTrue("The card should be an instance of AmexCC", card instanceof AmexCC);
        assertEquals("Card number should match", cardNumber, card.getCardNumber());
        assertEquals("Expiration date should match", expirationDate, card.getExpirationDate());
        assertEquals("Holder's name should match", holderName, card.getHolderName());
    }
}
