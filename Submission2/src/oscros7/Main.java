package oscros7;
/**
 * @// TODO: 2021-02-04 Create abstract methods in Account and add credit and savings accounts which extends Account 
 */
public class Main {
    public static void main(String[] args) {

        BankLogic bankLogic = new BankLogic();
        bankLogic.createCustomer("Oscar", "Rosberg", "1");
        bankLogic.createCustomer("Oscar", "Rosberg", "2");
        bankLogic.createSavingsAccount("1");
        bankLogic.createCreditAccount("1");
        bankLogic.deposit("1", 1001, 1000);
        bankLogic.withdraw("1", 1002, 5001);
        System.out.println(bankLogic.changeCustomerName("", "", "1"));
       // System.out.println(bankLogic.getTransactions("1", 1001));
        System.out.println(bankLogic.getCustomer("1"));


        //System.out.println(bankLogic.getAccount("1", 1001));

    }
}