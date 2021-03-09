/**
 * Class which holds all the info for an account which a customer can use.
 * @author  Oscar Rosberg, oscros-7
 */

package oscros7;

public class Account {

    private double balance;
    private int accountNumber;
    private static int lastAssignedAccountNumber = 1000;
    private int interestRate;
    private AccountType accountType;

    /**
     * Constructor to create an instance of the Account class
     * @param balance starting balance of the account
     * @param interestRate interest rate of the account
     * @param accountType type of account (only "Sparkonto" available at the moment)
     */
    public Account(double balance, int interestRate, AccountType accountType) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        accountNumber = ++lastAssignedAccountNumber;
    }

    /**
     *
     * @return the balance of the account
     */
    public double getBalance() {
        return balance;
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

    public int getInterestRate() {
        return interestRate;
    }

    /**
     *
     * @return the account type
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     *
     * @return the account number, balance, account type and interest rate (%) as a string
     */
    public String getAccountInfo() {
        return accountNumber + " " + balance + " " + accountType + " " + interestRate + "%";
    }

    /**
     *
     * @return account number, balance, type of account and the interest as a string
     */
    public String getClosingAccountInfo() {
        return accountNumber + " " + balance + " " + accountType + " " + calculateInterest();
    }

    /**
     * Deposits an amount in to the selected account
     * @param amount the amount of money to be deposited, has to be > 0.
     */
    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        }
    }

    /**
     * Function to withdraw an amount from an account
     * @param amount the amount to withdraw from the account
     * @return true if successfull, else returns false
     */
    public boolean withdraw(double amount) {
        if(balance >= amount && amount > 0) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Calculates the interest of the account
     * @return the interest
     */
    public double calculateInterest() {
        return balance * interestRate / 100;
    }

}

/**
 * Specifies the different type of accounts.
 */
enum AccountType {
    sparkonto;
}