/**
 * Class which holds all the info for an account which a customer can use.
 * @author  Oscar Rosberg, oscros-7
 */

package oscros7;

import java.util.ArrayList;

public abstract class Account {

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private double balance;
    private int accountNumber;
    private static int lastAssignedAccountNumber = 1000;
    private double interestRate;

    /**
     * Constructor to create an instance of the Account class
     * @param balance starting balance of the account
     * @param interestRate interest rate of the account
     */
    public Account(double balance, double interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
        accountNumber = ++lastAssignedAccountNumber;
    }

    /**
     *
     * @return the balance of the account
     */
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *
     * @return the account number of the account
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Static method which returns the static variable of the last assigned account number
     * @return the last assigned account number
     */
    public static int getLastAssignedAccountNumber() {
        return lastAssignedAccountNumber;
    }

    /**
     *
     * @return the interest rate of the account
     */

    public double getInterestRate() {
        return interestRate;
    }

    /**
     *
     * @return the account number, balance, account type and interest rate (%) as a string
     */
    public abstract String getAccountInfo();

    /**
     *
     * @return account number, balance, type of account and the interest as a string
     */
    public abstract String getClosingAccountInfo();

    /**
     * Deposits an amount in to the selected account
     * @param amount the amount of money to be deposited, has to be > 0.
     */
    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            transactions.add(new Transaction(accountNumber, amount, balance));
        }
    }

    /**
     * Function to withdraw an amount from an account
     * @param amount the amount to withdraw from the account
     * @return true if successfull, else returns false
     */
    public abstract boolean withdraw(double amount);

    /**
     * Calculates the interest of the account
     * @return the interest
     */
    public abstract double calculateInterest();


    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList<String> getTransactionString() {
        ArrayList<String> trans = new ArrayList<>();
        for(Transaction transaction : transactions) {
            String info = "";
            info += transaction.getDateTime() + " ";
            info += transaction.getAmount() + " ";
            info += "Saldo: " + transaction.getBalance();
            trans.add(info);
        }
        return trans;
    }



}