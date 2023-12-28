public class VisaCCFactory implements CreditCardFactory {
    @Override
    public CreditCard createCreditCard(String[] cardData) {
        return new VisaCC(cardData[0], cardData[1], cardData[2]);
    }
}