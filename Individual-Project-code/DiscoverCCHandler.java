public class DiscoverCCHandler extends CreditCardHandler {
    private CreditCardFactory factory;

    public DiscoverCCHandler(CreditCardFactory factory) {
        this.factory = factory;
    }

    @Override
    public CreditCard handle(String[] cardData) {
        if (isValidDiscover(cardData[0])) {
            return factory.createCreditCard(cardData);
        } else if (successor != null) {
            return successor.handle(cardData);
        }
        return null;
    }

    private boolean isValidDiscover(String cardNumber) {
        // Check if the card number contains only digits
        if (cardNumber == null || !cardNumber.matches("^\\d+$")) {
            return false;
        }
        return cardNumber.startsWith("6011") && cardNumber.length() == 16;
    }

    @Override
    public boolean canHandle(String cardNumber) {
        return isValidDiscover(cardNumber);
    }
}
