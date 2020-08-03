import Model.Account;
import Model.Currency;
import Model.TransactionMessage;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionHandler {

    public static void main(String[] args) {
        boolean newTransaction = true;
        String accountNumber;
        Currency currency;
        float amount;
        String exchangeRateStr;
        float exchangeRate;
        Account account;
        int transactionCounter = 0;
        Logger LOGGER = Logger.getLogger("TrxLogger");
        HashMap<String, Account> accounts = new HashMap<>();

        //Two account hardcoded
        accounts.put("11111111-22222222", new Account("11111111-22222222", Currency.HUF, 150000));
        accounts.put("22222222-33333333", new Account("22222222-33333333", Currency.USD, 1230));

        //Read transaction messages from stdin
        //handle some Exception and incorrect usecases (but not all of them)
        System.out.println("----------Tranzakciók megadása----------");
        try (Scanner sc = new Scanner(System.in)) {
            while (newTransaction) {
                System.out.print("\nSzámlaszám: ");
                accountNumber = sc.next();
                account = accounts.get(accountNumber);
                if (account == null) {
                    LOGGER.log(Level.WARNING, "\nNincs ilyen számlaszám");
                    continue;
                } else {
                    transactionCounter++;
                    System.out.print("Pénznem: ");
                    currency = Currency.valueOf(sc.next().toUpperCase());
                    System.out.print("Összeg: ");
                    amount = sc.nextFloat();

                    System.out.print("Valutaárfolyam:");
                    exchangeRateStr = sc.next();
                    if (exchangeRateStr.compareTo("null") == 0 || exchangeRateStr.compareTo("0") == 0) {
                        exchangeRate = 1.0f;
                    } else exchangeRate = Float.parseFloat(exchangeRateStr);

                    if (Math.abs(amount*exchangeRate) > account.getBalance() && amount<0) {
                        LOGGER.log(Level.WARNING, "\nNincs ekkora összeg a számlán");
                        continue;
                    }
                    TransactionMessage trMsg = new TransactionMessage(accountNumber, currency, amount, exchangeRate);
                    account.getTransactionMessages().add(trMsg);

                    //account currency and transaction currency are different
                    if (currency.compareTo(accounts.get(accountNumber).getCurrency()) != 0) {
                        amount *= exchangeRate;
                    }
                    //update account balance
                    account.setBalance(account.getBalance() + amount);
                }
                //Make a riport after every 10th transaction
                if ((transactionCounter % 10) == 0) {
                    for (Map.Entry<String, Account> entry : accounts.entrySet()) {
                        System.out.println("\nTranzakciók(" + entry.getKey() + ")");
                        for (TransactionMessage trm : entry.getValue().getTransactionMessages()) {
                            System.out.println("\n" + trm.toString());
                        }
                    }
                }
                System.out.print("\nÚj tranzakciós üzenet?(I/N)");
                if (sc.next().toLowerCase().compareTo("n") == 0) {
                    newTransaction = false;
                }
            }
        } catch (InputMismatchException inEx) {
            System.err.println("Nem megfelelő formátumban adtad meg az adatot!");
        } catch (NumberFormatException numEx) {
            System.err.println("Kérlek szám formátumot használj!");
        } catch (IllegalArgumentException iEx) {
            System.err.println("Nincs ilyen pénznem!");
        }

        //Check the balance after transactions
        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            System.out.println("Számla: " + entry.getKey() + " Egyenleg: " + entry.getValue().getBalance());
        }
    }
}
