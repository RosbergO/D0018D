package oscros7;

public class SavingsAccount extends Account {
    private int withdrawalsThisYear = 0;

    /**
     * Constructor to create an instance of the Account class
     *
     */
    public SavingsAccount() {
        super(0, 1);
    }

    @Override
    public String getAccountInfo() {
        return getAccountNumber() + " " + getBalance() + " " + SavingsAccount.class.getSimpleName() + " " + getInterestRate() + "%";

    }

    @Override
    public String getClosingAccountInfo() {
        return getAccountInfo() + " " + getBalance() + " " + SavingsAccount.class.getSimpleName() + " " + calculateInterest();
    }

    @Override
    public boolean withdraw(double amount) {
        if(withdrawalsThisYear < 1) {
            if (getBalance() - amount >= 0 && amount > 0) {
                setBalance(getBalance() - amount);
                getTransactions().add(new Transaction(getAccountNumber(), amount, getBalance()));
                withdrawalsThisYear++;
                return true;
            }
        }
        else if (getBalance() - (amount * 1.02) >= 0 && amount > 0) {
            setBalance(getBalance() - (amount * 1.02));
            withdrawalsThisYear++;
            getTransactions().add(new Transaction(getAccountNumber(), amount, getBalance()));
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest() {
        return getBalance() * getInterestRate() / 100;
    }


}
