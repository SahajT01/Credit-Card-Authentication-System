import static org.junit.Assert.*;
import org.junit.Test;

public class TestVisaCC {

    @Test
    public void createVisaCard() {
        String cardNumber = "4123456789123";
        String expirationDate = "12/25";
        String holderName = "Alice Smith";

        VisaCC VisaCard = new VisaCC(cardNumber, expirationDate, holderName);

        assertNotNull("Visa card should not be null", VisaCard);
        assertEquals("Card number should match", cardNumber, VisaCard.getCardNumber());
        assertEquals("Expiration date should match", expirationDate, VisaCard.getExpirationDate());
        assertEquals("Holder name should match", holderName, VisaCard.getHolderName());
    }

    // Additional tests can be written for boundary cases or specific business rules
}
