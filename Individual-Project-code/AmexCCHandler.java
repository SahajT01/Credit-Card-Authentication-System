public class AmexCCHandler extends CreditCardHandler {
    private CreditCardFactory factory;

    public AmexCCHandler(CreditCardFactory factory) {
        this.factory = factory;
    }

    @Override
    public CreditCard handle(String[] cardData) {
        if (isValidAmex(cardData[0])) {
            return factory.createCreditCard(cardData);
        } else if (successor != null) {
            return successor.handle(cardData);
        }
        return null;
    }

    private boolean isValidAmex(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 15) {
            return false;
        }
        // Check if the card number contains only digits
        if (!cardNumber.matches("^\\d+$")) {
            return false;
        }
        char firstDigit = cardNumber.charAt(0);
        char secondDigit = cardNumber.charAt(1);
    
        return firstDigit == '3' && (secondDigit == '4' || secondDigit == '7');
    }

    @Override
    public boolean canHandle(String cardNumber) {
        return isValidAmex(cardNumber);
    }
}
