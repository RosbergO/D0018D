package oscros7;

import java.io.*;
import java.util.ArrayList;

//import oscros-.BankLogic;

/** 
 * 
 * D0018D, Lab 2: Testar Account-klasserna, Customer and BankLogic  
 * Notera att klassen kan uppdateras under kursens gång
 * 
 * Denna klass placeras i default package
 * Ändra paketets namn <qwerty1> i importen ovan, så den matchar 
 * ditt pakets namn <användarid>
 * 
 * Kontrollera utskriften i konsol, så ustkifterna matchar
 * 
 * Programmet skriver ut fel i filen err.txt
 * 		err.txt kommer vara helt tom om allt stämmer
 * 		err.txt hittar du i projekt-mappen
 * 		Markera projektet och tryck F5 för att uppdatera projektet 
 * 		om filen inte syns
 * 
 * 
 * Last changes:  2020-08-31
 * @author Susanne Fahlman, susanne.fahlman@ltu.se        
 */
public class TestBank2
{
	private BankLogic bank = new BankLogic();
	
	//-----------------------------------------------------------------------------------
	// Runs the tests
	//-----------------------------------------------------------------------------------
	public void test() throws FileNotFoundException, InterruptedException
	{
		String 	customerName;
		String  customerSurname;
		String 	personalNumber;
		int 	accountNumber;
		double 	amount;

		// Sends error messages in an text file called err.txt instead of to the console
		// err.txt should be empty if everything is implemented correctly
		// The file err.txt is saved in your project folder, 
		// Select your project foiler and press F5 to refresh the folder if you can't see the file err.txt
		File file = new File("err.txt");
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
		System.setErr(ps);
		
		System.out.println("Testing begins, check the printout below but also check that the file err.txt");
		
		// Gets the empty list
		testingGetAllCustomers();
		System.out.println("# []"); 
		System.out.println();
		
		// Create customers
		customerName = "Olle";
		customerSurname = "Ohlsson";
		personalNumber = "0005221898";
		if(!testingCreateCustomer(customerName, customerSurname, personalNumber))
			System.err.println("Error: createCustomer(" + customerName +","+ customerSurname+","+personalNumber +")");
		
		customerName = "Karl";
		customerSurname = "Karlsson";
		personalNumber = "8505221898";
		if(!testingCreateCustomer(customerName, customerSurname, personalNumber))
			System.err.println("Error: createCustomer(" + customerName +","+ customerSurname+","+personalNumber +")");
		
		customerName = "Donald";
		customerSurname = "Duck";
		personalNumber = "8505221898";
		if(testingCreateCustomer(customerName, customerSurname, personalNumber)) // Should not work, personal number should be unique
			System.err.println("Error: createCustomer(" + customerName +","+ customerSurname+","+personalNumber +")");

		customerName = "Pelle";
		customerSurname = "Persson";
		personalNumber = "6911258876";
		if(!testingCreateCustomer(customerName, customerSurname, personalNumber))
			System.err.println("Error: createCustomer(" + customerName +","+ customerSurname+","+personalNumber +")");
			
		customerName = "Lotta";
		customerSurname = "Larsson";
		personalNumber = "7505121231";
		if(!testingCreateCustomer(customerName, customerSurname, personalNumber))
			System.err.println("Error: createCustomer(" + customerName +","+ customerSurname+","+personalNumber +")");
		
		customerName = "Ronald";
		customerSurname = "Duck";
		personalNumber = "7505121231";
		if(testingCreateCustomer(customerName, customerSurname, personalNumber))
			System.err.println("Error: createCustomer(" + customerName +","+ customerSurname+","+personalNumber +")");
		
		System.out.println();
		testingDeleteCustomer("0005221898");
		System.out.print("# [0005221898 Olle Ohlsson]");
		System.out.println();
		
		
		
		// Get all customers in the list and prints it
		System.out.println();
		testingGetAllCustomers();
		// Prints an example how the output should look, try to get your output as close to this as possible
		// Don't print the '#' however that is just to make it clear what is the expected output
		System.out.print("# [8505221898 Karl Karlsson,");
		System.out.print(  " 6911258876 Pelle Persson,");	
		System.out.println(" 7505121231 Lotta Larsson]");
		System.out.println();
		
		
		// Change a customers name
		customerName = "Kalle";
		customerSurname = "Karlsson";
		personalNumber = "8505221898";
		if(!testingChangeName(customerName, customerSurname, personalNumber))
			System.err.println("Error: changeCustomerName(" + customerName +","+ customerSurname + "," + personalNumber +")");

		// Creates accounts
		personalNumber = "8505221898";
		if(1001 != testingCreateCreditAccount(personalNumber))
			System.err.println("Error: createCreditAccount(" + personalNumber +")");
		personalNumber = "9905225166";
		if(-1 != testingCreateCreditAccount(personalNumber))
			System.err.println("Error: createCreditAccount(" + personalNumber +")");
		personalNumber = "6911258876";
		if(1002 != testingCreateCreditAccount(personalNumber))
			System.err.println("Error: createCreditAccount(" + personalNumber +")");
		personalNumber = "8505221898";
		if(1003 != testingCreateSavingsAccount(personalNumber))
			System.err.println("Error: createSavingsAccount(" + personalNumber +")");
		personalNumber = "7505121231";
		if(1004 != testingCreateSavingsAccount(personalNumber))
			System.err.println("Error: createSavingsAccount(" + personalNumber +")");
		
		testingGetTransactions("8505221898", 1002);
		System.out.println("# null");	
		
		// Get information about the customer including accounts
		System.out.println();
		testingGetCustomer("8505221898");
		System.out.print("# [8505221898 Kalle Karlsson, ");
		System.out.print("1001 0.0 kr Kreditkonto 0.5 %, ");
		System.out.println("1003 0.0 kr Sparkonto 1.0 %]");
		System.out.println();

		// Get information about the customer including accounts
		testingGetCustomer("6911258876");
		System.out.print("# [6911258876 Pelle Persson, ");
		System.out.println("1002 0.0 kr Kreditkonto 0.5 %]");
		System.out.println();

		// Get information about the customer including accounts
		testingGetCustomer("7505121231");
		System.out.print("# [7505121231 Lotta Larsson, ");
		System.out.println("1004 0.0 kr Sparkonto 1.0 %]");
		System.out.println();
		
		personalNumber 	= "8505221898";
		accountNumber  	= 1001;
		amount 			= 500;		
		if(!testingWithdraw(personalNumber, accountNumber, amount))	
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");
		
		System.out.println("Please wait a moment... the test vill continue soon");		
		// Pause the program, so we can see that the transaction time is different
		// If not, remember that you need to create a new Date-object each time
		// a transaction is made. Otherwise the transaction time is wrong.
		Thread.sleep(2000);
		
		personalNumber 	= "8505221898";
		accountNumber  	= 1001;
		amount 			= 4000;
		if(!testingWithdraw(personalNumber, accountNumber, amount))
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");	
		
		personalNumber 	= "8505221898";
		accountNumber  	= 1001;
		amount 			= 501;
		if(testingWithdraw(personalNumber, accountNumber, amount))	// Should not work, not enough money
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");

		personalNumber 	= "8505221898";
		accountNumber  	= 1003;
		amount 			= 500;
		if(testingWithdraw(personalNumber, accountNumber, amount)) // Should not work, not enough money
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");

		personalNumber 	= "8505221898";
		accountNumber  	= 1003;
		amount 			= 500;
		if(!testingDeposit(personalNumber, accountNumber, amount))	
			System.err.println("Error: deposit("+personalNumber + "," + accountNumber + "," + amount+")");
				
		// Get information about the customer including accounts
		System.out.println();
		testingGetCustomer("8505221898");
		System.out.print("# [8505221898 Kalle Karlsson, ");
		System.out.print("1001 -4500.0 kr Kreditkonto 7.0 %, ");
		System.out.println("1003 500.0 kr Sparkonto 1.0 %]");
		System.out.println();
		
		testingGetTransactions("8505221898", 1001);
		System.out.println("# [YYYY-MM-DD hh:mm:ss -500.0 kr Saldo: -500.0 kr, YYYY-MM-DD hh:mm:ss -4000.0 kr Saldo: -4500.0 kr]");
		System.out.println();

		testingGetTransactions("8505221898", 1003);
		System.out.println("# [YYYY-MM-DD hh:mm:ss 500.0 kr Saldo: 500.0 kr]");
		System.out.println();
		
		personalNumber 	= "6911258876";
		accountNumber  	= 1002;
		amount 			= 500;
		if(!testingDeposit(personalNumber, accountNumber, amount))	
			System.err.println("Error: deposit("+personalNumber + "," + accountNumber + "," + amount+")");

		personalNumber 	= "6911258876";
		accountNumber  	= 1002;
		amount 			= 1000;
		if(!testingDeposit(personalNumber, accountNumber, amount))	
			System.err.println("Error: deposit("+personalNumber + "," + accountNumber + "," + amount+")");

		personalNumber 	= "6911258876";
		accountNumber  	= 1002;
		amount 			= 500;
		if(!testingWithdraw(personalNumber, accountNumber, amount))	
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");
		
		// Get information about the customer including accounts
		System.out.println();
		testingGetCustomer("6911258876");
		System.out.print("# [6911258876 Pelle Persson, ");
		System.out.println("1002 1000.0 kr Kreditkonto 0.5 %]");
		System.out.println();
		
		testingGetTransactions("6911258876", 1002);
		System.out.println("# [YYYY-MM-DD hh:mm:ss 500.0 kr Saldo: 500.0 kr, YYYY-MM-DD hh:mm:ss 1000.0 kr Saldo: 1500.0 kr, YYYY-MM-DD hh:mm:ss -500.0 kr Saldo: 1000.0 kr]");
		System.out.println();
		
		personalNumber 	= "7505121231";
		accountNumber  	= 1004;
		amount 			= 500;
		if(testingWithdraw(personalNumber, accountNumber, amount))	 // Should not work, not enough money
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");	
		
		personalNumber 	= "7505121231";
		accountNumber  	= 1004;
		amount 			= 1000;
		if(!testingDeposit(personalNumber, accountNumber, amount))	
			System.err.println("Error: deposit("+personalNumber + "," + accountNumber + "," + amount+")");	
		
		personalNumber 	= "7505121231";
		accountNumber  	= 1004;
		amount 			= 100;
		if(!testingWithdraw(personalNumber, accountNumber, amount))	 
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");	
		
		personalNumber 	= "7505121231";
		accountNumber  	= 1004;
		amount 			= 890;
		if(testingWithdraw(personalNumber, accountNumber, amount))	 // Should not work, not enough money
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");	
		
		personalNumber 	= "7505121231";
		accountNumber  	= 1004;
		amount 			= 100;
		if(!testingWithdraw(personalNumber, accountNumber, amount))	 
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");	

		// Get information about the customer including accounts
		System.out.println();
		testingGetCustomer("7505121231");
		System.out.print("# [7505121231 Lotta Larsson, ");
		System.out.println("1004 798.0 kr Sparkonto 1.0 %]");
		System.out.println();
		
		testingGetTransactions("7505121231", 1004);
		System.out.println("# [YYYY-MM-DD hh:mm:ss 1000.0 kr Saldo: 1000.0 kr, YYYY-MM-DD hh:mm:ss -100.0 kr Saldo: 900.0 kr, YYYY-MM-DD hh:mm:ss -102.0 kr Saldo: 798.0 kr]");
		System.out.println();
		
		// Closes the account
		testingCloseAccount("8505221898", 1001);	
		System.out.println("# 1001 -4500.0 kr Kreditkonto -315.00000000000006 kr");
		System.out.println();
		
		// Creates account
		personalNumber = "8505221898";
		if(1005 != testingCreateCreditAccount(personalNumber))
			System.err.println("Error: createCreditAccount(" + personalNumber +")");
		
		personalNumber 	= "8505221898";
		accountNumber  	= 1005;
		amount 			= 1000;
		if(!testingWithdraw(personalNumber, accountNumber, amount))
			System.err.println("Error: withdraw("+personalNumber + "," + accountNumber + "," + amount+")");	
				
		// Get information about the account
		testingGetAccount("8505221898", 1003); 
		System.out.println("# 1003 500.0 kr Sparkonto 1.0 %");
		System.out.println();
		
		// Deletes the customer including accounts
		System.out.println();
		testingDeleteCustomer("8505221898");
		System.out.print("# [8505221898 Kalle Karlsson, ");
		System.out.print("1003 500.0 kr Sparkonto 5.0 kr, ");
		System.out.println("1005 -1000.0 kr Kreditkonto -70.0 kr]");
		System.out.println();
		
		// Get information about the account
		testingGetAccount("7505121231", 1004); 
		System.out.println("# 1004 798.0 kr Sparkonto 1.0 %");
		System.out.println();
		
		// Closes the account
		testingCloseAccount("7505121231", 1004);	
		System.out.println("# 1004 798.0 kr Sparkonto 7.98 kr");
		System.out.println();
		
		// Get information about the account
		testingGetAccount("7505121231", 1004); // Should not work
		System.out.println("# null");
		System.out.println();

		// Get information about the customer including accounts
		testingGetCustomer("7505121231");
		System.out.println("# [7505121231 Lotta Larsson]");
		System.out.println();

		// Deletes the customer
		testingDeleteCustomer("6911258876");
		System.out.print("# [6911258876 Pelle Persson, ");	
		System.out.println("1002 1000.0 kr Kreditkonto 5.0 kr]");	
		System.out.println();
		
		// Deletes the customer
		testingDeleteCustomer("7505121231");
		System.out.print("# [7505121231 Lotta Larsson]");
		
		// Get all customers in the list and prints it
		System.out.println();
		testingGetAllCustomers();
		System.out.println("# []");
		System.out.println();
		ps.close();
	}

	
	/**
	 * Prints the customer list
	 */
	private void testingGetAllCustomers()
	{
		System.out.println("getAllCustomers()");
		ArrayList<String> result = bank.getAllCustomers();
		System.out.println("  " + result);
	}
	
	/**
	 * Prints the customer
	 * @param pNr - Personal number of customer that will be printed
	 */
	private void testingGetCustomer(String pNr)
	{
		System.out.println("getCustomer(" + pNr + ")");
		System.out.println("  " + bank.getCustomer(pNr));
	}

	/**
	 * Prints the account
	 * @param pNr - Personal number of customer that owns the account
	 * @param accountId - Id of the account that will be printed
	 */
	private void testingGetAccount(String pNr, int accountId)
	{
		System.out.println("getAccount(" + pNr + "," + accountId + ")");
		System.out.println("  " + bank.getAccount(pNr, accountId));
	}

	/**
	 * Creates a cusomer if no customer exists wit hte same personal number
	 * @param name - Name of the customer
	 * @param pNr - Personal number of customer
	 * @return true if customer is created otherwise false
	 */
	private boolean testingCreateCustomer(String name, String surname, String pNr)
	{
		System.out.println("createCustomer(" + name + "," + surname + "," + pNr + ")");
		return bank.createCustomer(name, surname, pNr); 
	}

	/**
	 * Changes the name of the customer
	 * @param name - The new name
	 * @param pNr - Personal number of customer that is getting a new name
	 * @return true if customer name is changed otherwise false
	 */
	private boolean testingChangeName(String name, String surname, String pNr)
	{
		System.out.println("changeCustomerName(" + name + "," + surname + "," + pNr + ")");
		return bank.changeCustomerName(name, surname, pNr);
	}

	/**
	 * Creates a account
	 * @param pNr - Personal number of customer that is getting a new account
	 * @return true if account is created otherwise false
	 */
	private int testingCreateSavingsAccount(String pNr)
	{
		System.out.println("createSavingsAccount(" + pNr + ")");
		return bank.createSavingsAccount(pNr);
	}
	
	/**
	 * Creates a account
	 * @param pNr - Personal number of customer that is getting a new account
	 * @return true if account is created otherwise false
	 */
	private int testingCreateCreditAccount(String pNr)
	{
		System.out.println("createCreditAccount(" + pNr + ")");
		return bank.createCreditAccount(pNr);
	}

	/**
	 * Deposit the amount
	 * @param pNr - The personal number of the customer that owns the account
	 * @param accountId -  The id of the account
	 * @param amount - The amount
	 * @return true if amount is deposited otherwise false
	 */
	private boolean testingDeposit(String pNr, int accountId, double amount)
	{
		System.out.println("deposit(" + pNr + "," + accountId + "," + amount + ")");
		return bank.deposit(pNr, accountId, amount);						
	}
	
	/**
	 * Withdraws the amount
	 * @param pNr - The personal number of the customer that owns the account
	 * @param accountId -  The id of the account
	 * @param amount - The amount
	 * @return true if amount is withdrawn otherwise false
	 */
	private boolean testingWithdraw(String pNr, int accountId, double amount)
	{
		System.out.println("withdraw(" + pNr + "," + accountId + "," + amount + ")");
		return bank.withdraw(pNr, accountId, amount);						
	}
	
	/**
	 * The account is deleted and result is printed
	 * @param pNr - The personal number of the customer that is to be deleted
	 * @param accountId - The id of the account that is closed
	 */
	private void testingCloseAccount(String pNr, int accountId)
	{
		System.out.println("closeAccount(" + pNr + "," + accountId + ")");
		System.out.println("  " + bank.closeAccount(pNr, accountId));							
	}
		
	/**
	 * The customer is deleted and result is printed
	 * @param pNr - The personal number of the customer that is to be deleted
	 */
	private void testingDeleteCustomer(String pNr)
	{
		System.out.println("deleteCustomer(" + pNr + ")");
		System.out.println("  " + bank.deleteCustomer(pNr));				
	}
	
	private void testingGetTransactions(String pNr, int accountId)
	{
		System.out.println("getTransactions(" + pNr + ", " + accountId + ")");
		System.out.println("  " + bank.getTransactions(pNr, accountId));							
	}

	public static void main(String[] args) throws FileNotFoundException, InterruptedException
	{		
		TestBank2 bankMenu = new TestBank2();
		bankMenu.test();	
	}
}
