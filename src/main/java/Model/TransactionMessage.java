package Model;

public final class TransactionMessage {

    private String accountNumber;
    private Currency currency;
    private float amount;
    private float exchangeRate;

    public TransactionMessage(String accountNumber, Currency currency, float amount, float exchangeRate){
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(int exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString(){
        return "Pénznem: "+getCurrency()+"\nÖsszeg: "+getAmount()+"\nValutaárfolyam: "+getExchangeRate();
    }
}
