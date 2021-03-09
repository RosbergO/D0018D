/**
 * Driver class.
 * @author  Oscar Rosberg, oscros-7
 */

package oscros7;

public class Main {
    public static void main(String[] args) {
        //Account account = new Account(2300, 0, AccountType.sparkonto);
        //Account account1 = new Account(2300, 0, AccountType.sparkonto);
        BankLogic bankLogic = new BankLogic();
        bankLogic.createCustomer("Oscar", "Rosberg", "970422");
        bankLogic.createCustomer("Linkan", "Linksson", "971212");
    }
}