/**
 * Holds all the info about a customer. Also contains all the accounts of a customer in an ArrayList.
 * @author  Oscar Rosberg, oscros-7
 */

package oscros7;

import java.util.ArrayList;

public class Customer {
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private String firstName;
    private String lastName;
    private final String ssn;

    /**
     * Constructor to create a new instance of a customer object
     * @param firstName first name of the customer
     * @param lastName last name of the customer
     * @param ssn social security number of a customer
     */
    public Customer(String firstName, String lastName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    /**
     *
     * @return the first name of the instantiated customer as a string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Used to set the first name of the instantiated customer
     * @param firstName the first name of the customer
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return a string of the last name of a customer
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Used to set the last name of the instantiated customer
     * @param lastName the last name of the instantiated customer
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return a string of the ssn of the instantiated customer
     */

    public String getSsn() {
        return ssn;
    }

    /**
     *
     * @return a string of the ssn, first name and last name of the instantiated customer
     */
    public String getCustomerInfo() {
        return ssn + " " + firstName + " " + lastName;
    }

    /**
     *
     * @return ArrayList of the accounts of instantiated customer
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * Adds account to the list of accounts of the instantiated customer
     * @param account
     */

    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     * Deletes a specified account of the instantiated customer
     * @param accountId the identification of the account
     * @return true if successful, else false
     */
    public boolean deleteAccount(int accountId) {
        for(Account account : accounts) {
            if(account.getAccountNumber() == accountId) {
                accounts.remove(account);
                return true;
            }
        }
        return false;
    }

    public void deleteAllAccounts() {
        accounts.clear();
    }
}