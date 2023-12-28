public class MasterCCHandler extends CreditCardHandler {
    private CreditCardFactory factory;

    public MasterCCHandler(CreditCardFactory factory) {
        this.factory = factory;
    }

    @Override
    public CreditCard handle(String[] cardData) {
        if (isValidMaster(cardData[0])) {
            return factory.createCreditCard(cardData);
        } else if (successor != null) {
            return successor.handle(cardData);
        }
        return null;
    }

    private boolean isValidMaster(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16) {
            return false;
        }

        // Check if the card number contains only digits
        if (!cardNumber.matches("^\\d+$")) {
            return false;
        }
    
        char firstDigit = cardNumber.charAt(0);
        char secondDigit = cardNumber.charAt(1);
    
        return firstDigit == '5' && secondDigit >= '1' && secondDigit <= '5';
    }

    @Override
    public boolean canHandle(String cardNumber) {
        return isValidMaster(cardNumber);
    }
}
