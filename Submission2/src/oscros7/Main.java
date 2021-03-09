package oscros7;
/**
 * @// TODO: 2021-02-04 Create abstract methods in Account and add credit and savings accounts which extends Account 
 */
public class Main {
    public static void main(String[] args) {
        //Account account = new Account(2300, 0, AccountType.sparkonto);
        //Account account1 = new Account(2300, 0, AccountType.sparkonto);
        BankLogic bankLogic = new BankLogic();
        bankLogic.createCustomer("Oscar", "Rosberg", "9704222356");
        bankLogic.createCustomer("Linkan", "Linksson", "9712121313");
        bankLogic.createSavingsAccount("9704222356");
        bankLogic.deposit("9704222356", 1001, 1000);
        bankLogic.withdraw("9704222356", 1001, 1000);
        System.out.println(bankLogic.getCustomer("9704222356"));

        System.out.println(bankLogic.getAllCustomers());
    }
}