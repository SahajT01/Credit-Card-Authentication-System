public class UnknownCCFactory implements CreditCardFactory{
    @Override
    public CreditCard createCreditCard(String[] cardData) {
        return new UnknownCC(cardData[0], cardData[1], cardData[2]);
    }
}
