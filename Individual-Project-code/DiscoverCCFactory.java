public class DiscoverCCFactory implements CreditCardFactory {
    @Override
    public CreditCard createCreditCard(String[] cardData) {
        return new DiscoverCC(cardData[0], cardData[1], cardData[2]);
    }
}