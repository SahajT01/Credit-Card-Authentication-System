import static org.junit.Assert.*;
import org.junit.Test;

public class TestDiscoverCC {

    @Test
    public void createDiscoverCard() {
        String cardNumber = "6011111111111117";
        String expirationDate = "12/25";
        String holderName = "Alice Smith";

        DiscoverCC discoverCard = new DiscoverCC(cardNumber, expirationDate, holderName);

        assertNotNull("Discover card should not be null", discoverCard);
        assertEquals("Card number should match", cardNumber, discoverCard.getCardNumber());
        assertEquals("Expiration date should match", expirationDate, discoverCard.getExpirationDate());
        assertEquals("Holder name should match", holderName, discoverCard.getHolderName());
    }

    // Additional tests can be written for boundary cases or specific business rules
}
