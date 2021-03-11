package oscros7;

public class CreditAccount extends Account {

    private final static int creditLimit = 5000;
    private final static int debtInterestPercent = 7;
    /**
     * Constructor to create an instance of the Account class
     *
     */
    public CreditAccount() {
        super(0, 0.5);
    }

    @Override
    public String getAccountInfo() {
        return getAccountNumber() + " " + getBalance() + " " + CreditAccount.class.getSimpleName() + " " + getInterestRate() + "%";

    }

    @Override
    public String getClosingAccountInfo() {
        return getAccountInfo() + " " + getBalance() + " " + CreditAccount.class.getSimpleName() + " " + calculateInterest();
    }

    @Override
    public boolean withdraw(double amount) {
        if(getBalance() - amount >= - creditLimit && amount > 0) {
            setBalance(getBalance() - amount);
            getTransactions().add(new Transaction(getAccountNumber(), amount, getBalance()));
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest() {
        if(getBalance() < 0) {
            return getBalance() * debtInterestPercent / 100;
        }
        else {
            return getBalance() * getInterestRate() / 100;
        }
    }


}
