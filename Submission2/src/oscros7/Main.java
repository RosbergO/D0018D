package oscros7;
/**
 * @// TODO: 2021-02-04 Create abstract methods in Account and add credit and savings accounts which extends Account 
 */
public class Main {
    public static void main(String[] args) {

        BankLogic bankLogic = new BankLogic();
        bankLogic.createCustomer("Oscar", "Rosberg", "1");

    }
}