public abstract class CreditCardHandler {
    protected CreditCardHandler successor;

    public void setSuccessor(CreditCardHandler successor) {
        this.successor = successor;
    }

    public abstract CreditCard handle(String[] cardData);

    public CreditCardHandler getSuccessor() {
        return successor;
    }

    public abstract boolean canHandle(String cardNumber);
}