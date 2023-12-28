public class MasterCCFactory implements CreditCardFactory {
    @Override
    public CreditCard createCreditCard(String[] cardData) {
        return new MasterCC(cardData[0], cardData[1], cardData[2]);
    }
}