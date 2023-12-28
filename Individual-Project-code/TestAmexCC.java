import static org.junit.Assert.*;
import org.junit.Test;

public class TestAmexCC {

    @Test
    public void testConstruction() {
        String cardNumber = "378282246310005";
        String expirationDate = "12/24";
        String holderName = "John Doe";

        AmexCC amexCC = new AmexCC(cardNumber, expirationDate, holderName);

        assertNotNull(amexCC);
        assertEquals(cardNumber, amexCC.getCardNumber());
        assertEquals(expirationDate, amexCC.getExpirationDate());
        assertEquals(holderName, amexCC.getHolderName());
    }
}
