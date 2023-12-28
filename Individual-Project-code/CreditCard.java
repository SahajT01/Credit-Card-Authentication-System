public abstract class CreditCard {
    protected String cardNumber;
    protected String expirationDate;
    protected String holderName;

    // Constructor
     public CreditCard(String cardNumber, String expirationDate, String holderName) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.holderName = holderName;
    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getHolderName() {
        return holderName;
    }
}

