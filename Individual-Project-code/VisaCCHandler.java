public class VisaCCHandler extends CreditCardHandler {
    private CreditCardFactory factory;

    public VisaCCHandler(CreditCardFactory factory) {
        this.factory = factory;
    }

    @Override
    public CreditCard handle(String[] cardData) {
        if (isValidVisa(cardData[0])) {
            return factory.createCreditCard(cardData);
        } else if (successor != null) {
            return successor.handle(cardData);
        }
        return null;
    }

    private boolean isValidVisa(String cardNumber) {
        // Check if the card number contains only digits
        if (cardNumber==null || !cardNumber.matches("^\\d+$")) {
            return false;
        }
        return cardNumber.startsWith("4") && (cardNumber.length() == 13 || cardNumber.length() == 16);
    }

    @Override
    public boolean canHandle(String cardNumber) {
        return isValidVisa(cardNumber);
    }
}
