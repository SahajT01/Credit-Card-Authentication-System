public class AmexCCFactory implements CreditCardFactory {
    @Override
    public CreditCard createCreditCard(String[] cardData) {
        return new AmexCC(cardData[0], cardData[1], cardData[2]);
    }
}