package Model;

import java.util.ArrayList;

public class Account {
    private final String accountNumber;
    private Currency currency;
    private float balance;
    private ArrayList<TransactionMessage> transactionMessages;

    public Account(String accountNumber, Currency currency, float balance){
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
        this.transactionMessages = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public ArrayList<TransactionMessage> getTransactionMessages() {
        return transactionMessages;
    }
}
