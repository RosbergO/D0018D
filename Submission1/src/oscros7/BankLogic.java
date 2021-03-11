/**
 *
 * @author  Oscar Rosberg, oscros-7
 */

package oscros7;

import java.util.ArrayList;

public class BankLogic {
    private ArrayList<Customer> customers = new ArrayList<>();

    /**
     * Returns a list of strings of all customers with ssn, first name and last name
     * @return the ArrayList of strings
     */
    public ArrayList<String> getAllCustomers() {
        ArrayList<String> allCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            allCustomers.add(customer.getCustomerInfo());
        }
        return allCustomers;
    }

    /**
     * Creates a new customer and adds it to the customer ArrayList
     * @param firstName first name of the customer
     * @param lastName last name of the customer
     * @param ssn social security number of the customer
     * @return true if successful, else false
     */
    public boolean createCustomer(String firstName, String lastName, String ssn) {
        for(Customer customer : customers) {
            if(customer.getSsn().equals(ssn)) {
                return false;
            }
        }
        Customer customer = new Customer(firstName, lastName, ssn);
        customers.add(customer);
        return true;
    }

    /**
     * Returns an ArrayList of strings where index 0 is customer first name, last name, ssn and the following is the account info
     * @param ssn social security number of the requested customer
     * @return the ArrayList
     */
    public ArrayList<String> getCustomer(String ssn) {
        ArrayList<String> specificCustomer = new ArrayList<>();
        Customer customer = getSpecificCustomer(ssn);
        if(customer != null) {
            specificCustomer.add(customer.getCustomerInfo());
            for(Account account : customer.getAccounts()) {
                specificCustomer.add(account.getAccountInfo());
            }
            return specificCustomer;
        }
        return null;
    }

    /**
     * Change the first name, last name or both of a customer
     * @param firstName first name of the customer
     * @param lastName last name of the customer
     * @param ssn social security number of the customer
     * @return true if name changed was successful, else false
     */
    public boolean changeCustomerName(String firstName, String lastName, String ssn) {
        Customer customer = getSpecificCustomer(ssn);
        if(customer != null) {
            if(firstName.isBlank() && lastName.isBlank()) {
                return false;
            }
            if(!firstName.isBlank()) {
                customer.setFirstName(firstName);
            }
            if(!lastName.isBlank()) {
                customer.setLastName(lastName);
            }
            return true;
        }
        return false;
    }

    /**
     * Creates a savings account for a customer
     * @param ssn social security number of the customer
     * @return the new account number if successful, else -1
     */
    public int createSavingsAccount(String ssn) {
        Customer customer = getSpecificCustomer(ssn);
        if(customer != null) {
            Account account = new Account(0, 1, AccountType.sparkonto);
            customer.addAccount(account);
            return account.getAccountNumber();
        }
        return -1;
    }

    /**
     *
     * @param ssn the social security number of the customer
     * @param accountId the account id
     * @return
     * the account number, balance, account type and interest rate (%) as a string
     */
    public String getAccount(String ssn, int accountId) {
        Account account = getAccountInfo(ssn, accountId);
        if(account != null) {
            return account.getAccountInfo();
        }
        return null;
    }

    public Customer getSpecificCustomer(String ssn) {
        for(Customer customer : customers) {
            if(customer.getSsn().equals(ssn)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Used to get the account object of a customer
     * @param ssn social security number of the customer
     * @param accountId the requested accounts id
     * @return the account object or null
     */
    public Account getAccountInfo(String ssn, int accountId) {
        Customer customer = getSpecificCustomer(ssn);
        if(customer != null) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber() == accountId) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Deposits a sum in to a customers account
     * @param ssn social security number of the customer
     * @param accountId account id
     * @param amount the amount to deposit
     * @return true if successfull else false
     */
    public boolean deposit(String ssn, int accountId, double amount) {
        if(amount > 0) {
            Account account = getAccountInfo(ssn, accountId);
            if(account != null) {
                account.deposit(amount);
                return true;
            }
        }
        return false;
    }

    /**
     * Withdraws a sum from a customers account
     * @param ssn social security number of a customer
     * @param accountId account id of the account
     * @param amount the amount to withdraw from the account
     * @return true if the deposit was successful, else false
     */
    public boolean withdraw(String ssn, int accountId, double amount) {
        Account account = getAccountInfo(ssn, accountId);
        if(account != null) {
            return account.withdraw(amount);
        }
        return false;
    }

    /**
     *
     * @param ssn social security number of the customer
     * @param accountId account id of the account
     * @return account number, balance, type of account and the interest as a string if successful, else null
     */
    public String closeAccount(String ssn, int accountId) {
        Account account = getAccountInfo(ssn, accountId);
        Customer customer = getSpecificCustomer(ssn);
        if(customer != null && account != null) {
            String accountInfo = account.getClosingAccountInfo();
            customer.deleteAccount(accountId);
            return accountInfo;
        }
        return null;
    }

    /**
     * Deletes a selected customer
     * @param ssn social security number of the customer
     * @return ArrayList of customer and account info
     */
    public ArrayList<String> deleteCustomer(String ssn) {
        ArrayList<String> customerInfo = new ArrayList<>();
        Customer customer = getSpecificCustomer(ssn);
        if(customer != null) {
            customerInfo.add(customer.getCustomerInfo());
            if(customer.getAccounts() != null) {
                for(Account account : customer.getAccounts()) {
                    customerInfo.add(account.getClosingAccountInfo());
                }
            }
            customer.deleteAllAccounts();
            customers.remove(customer);
            return customerInfo;
        }
        return null;
    }
}