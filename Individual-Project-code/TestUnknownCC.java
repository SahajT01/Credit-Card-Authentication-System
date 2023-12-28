import static org.junit.Assert.*;
import org.junit.Test;

public class TestUnknownCC {

    @Test
    public void createUnknownCard() {
        String cardNumber = "5567894523129089";
        String expirationDate = "12/25";
        String holderName = "Alice Smith";

        UnknownCC UnknownCard = new UnknownCC(cardNumber, expirationDate, holderName);

        assertNotNull("Unknown card should not be null", UnknownCard);
        assertEquals("Card number should match", cardNumber, UnknownCard.getCardNumber());
        assertEquals("Expiration date should match", expirationDate, UnknownCard.getExpirationDate());
        assertEquals("Holder name should match", holderName, UnknownCard.getHolderName());
    }

    // Additional tests can be written for boundary cases or specific business rules
}
