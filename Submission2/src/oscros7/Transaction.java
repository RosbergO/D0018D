package oscros7;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String dateTime;
    private int accountID;
    private double amount;
    private double balance;

    public Transaction(int accountID, double amount, double balance) {
        this.accountID = accountID;
        this.amount = amount;
        this.balance = balance;
        dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getAccountID() {
        return accountID;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

}
