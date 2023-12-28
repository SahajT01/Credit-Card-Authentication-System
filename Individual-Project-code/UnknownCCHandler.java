public class UnknownCCHandler extends CreditCardHandler {
    private CreditCardFactory factory;

    public UnknownCCHandler(CreditCardFactory factory) {
        this.factory = factory;
    }

    @Override
    public CreditCard handle(String[] cardData) {
        return factory.createCreditCard(cardData);
    }

    @Override
    public boolean canHandle(String cardNumber) {
        return true;
    }
}