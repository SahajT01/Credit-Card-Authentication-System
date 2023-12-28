import static org.junit.Assert.*;
import org.junit.Test;

public class TestMasterCC {

    @Test
    public void createMasterCard() {
        String cardNumber = "5567894523129089";
        String expirationDate = "12/25";
        String holderName = "Alice Smith";

        MasterCC MasterCard = new MasterCC(cardNumber, expirationDate, holderName);

        assertNotNull("Master card should not be null", MasterCard);
        assertEquals("Card number should match", cardNumber, MasterCard.getCardNumber());
        assertEquals("Expiration date should match", expirationDate, MasterCard.getExpirationDate());
        assertEquals("Holder name should match", holderName, MasterCard.getHolderName());
    }

    // Additional tests can be written for boundary cases or specific business rules
}
