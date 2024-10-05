package src;

import src.BankAccount;
import src.FixedDepositAccount;

public class App {
    
    public static void main(String[] args) {
        
        BankAccount testAccount = new BankAccount("testHolderName", 1000);
        
        System.out.println(testAccount);
        System.out.println("");
        

        testAccount.deposit(500);
        System.out.println("Account balance: " + testAccount.getAccountBalance());
        System.out.println("");
        

        testAccount.withdraw(500);
        System.out.println("Account balance: " + testAccount.getAccountBalance());
        System.out.println("");
        // CONSIDER USING A TRY CATCH STATEMENT HERE INSTEAD
        //      especially if your program is more robust and/or calling methods from libraries over the internet
        //      - Try catch statements will still allow the program to flow after throwing an exception
        //          meaning that after this block is executed, it will still move on to code below
        //
        // try {
        //     testAccount.withdraw(-20000);                            // Attempt to withdraw an invalid amount
        // } catch (Exception error) {                                  // Catch exceptions
        //     System.out.println("Error: " + error.getMessage());      // Print error message of exception, prints 'null' if found error is not specified
        // }


        System.out.println("Acount transactions: " + testAccount.getTransactions());
        System.out.println("-----------------------");




        FixedDepositAccount testFixedDepositAccount = new FixedDepositAccount("testFDAName", 100);
        
        // System.out.println(testFixedDepositAccount.getDurationInMonths());
        // testFixedDepositAccount.setDurationInMonths(10);

        // System.out.println(testFixedDepositAccount);
        // testFixedDepositAccount.setClosed(true);
        System.out.println(testFixedDepositAccount);

    }
    
}
