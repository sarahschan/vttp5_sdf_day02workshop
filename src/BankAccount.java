package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankAccount {

    // Create members
    private final String accountHolderName;         // Create read only
    private final String accountNumber;             // Create read only
    private float accountBalance;
    private List<String> transactions;
    private boolean isClosed;
    private LocalDateTime accountCreationDate;               // LocalDateTime default 2024-10-05T14:28:52.075179
    private LocalDateTime accountClosingDate;                //                       yyyy-MM-dd HH:mm:ss
    

    // Use DateTimeFormatter to change default print of date and time
    DateTimeFormatter customDateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    // Create getters for all fields
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public LocalDateTime getAccountClosingDate() {
        return accountClosingDate;
    }


    // Create setters for the appropriate field
    //      The only appropriate field to have a setter would be isClosed
    //      All other fields are final or should be manipulated through methods
    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
        this.accountClosingDate = LocalDateTime.now();
    }


    // Create constructors
    //      1. Single parameter - accountHolderName, initialize balance to 0
    public BankAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;             // Take in parameter when instantiated
        this.accountNumber = generateAccountNumber();           // Uses method to generate random account number
        this.accountBalance = 0.0f;                             // Initialize balance to 0
        this.transactions = new ArrayList<>();                  // Creates List<String> transactions = new ArrayList<>();
        this.isClosed = false;                                  // Sets account to open, when instantiated
        this.accountCreationDate = LocalDateTime.now();         // Sets creation date and time
    }

    //      2. Two parameters - accountHolderName, initial account balance
    public BankAccount(String accountHolderName, float accountBalance) {
        this.accountHolderName = accountHolderName;             // Take in parameter when instantiated
        this.accountNumber = generateAccountNumber();           // Uses method to generate random account number
        this.accountBalance = accountBalance;                   // Take in parameter when instantiated
        this.transactions = new ArrayList<>();                  // Creates List<String> transactions = new ArrayList<>();
        this.isClosed = false;                                  // Sets account to open, when instantiated
        this.accountCreationDate = LocalDateTime.now();         // Sets creation date and time
    }


    // Create special method to generate random account number
    public String generateAccountNumber() {
        Random random = new Random();                           // Instantiate random class to generate random digits
        String accountNumber = "";                              // Start off accountNumber with an empty String
        for ( int i = 0; i < 10; i++ ) {                        // Use a loop to generate a series of 10 numbers
            int generatedDigit = random.nextInt(10);      // Generate a digit
            accountNumber += generatedDigit;                    // Concat (because it's a String) to add generated digit to the accountNumber
        }
        return accountNumber;                                   // Return final account number
    }


    // Create methods
    //      1. deposit method
    public void deposit(float depositAmount) {                                      // deposit method takes in parameter of depositAmount
        if (depositAmount > 0 && !isClosed) {                                       // if deposit amount is valid AND account is !(NOT)closed
            this.accountBalance += depositAmount;                                   //      add deposit amount to accountBalance
            transactions.add("Deposit of $" + depositAmount + " made at " +         // add transaction to transactions ArrayList
                (LocalDateTime.now().format(customDateTimeFormat)) );               //      remember to add customDateTimeFormat
        } else {                                                                    // else
            if (isClosed) {                                                         // if account is closed
                throw new IllegalArgumentException("Account is closed");          // throw new IllegalArgumentException - Account closed 
            } else if (depositAmount <= 0) {                                        // if deposit amount is negative
                throw new IllegalArgumentException("Deposit amount not valid");   // throw new IllegalArgumentException - Invalid amount
            }
        }
    }


    //      2. withdraw method
    public void withdraw(float withdrawAmount) {
        if (isClosed) {                                                             // Check if account is open
            throw new IllegalArgumentException("Account is closed");
        } else if (withdrawAmount <= 0) {
            throw new IllegalArgumentException("Deposit amount not valid");
        } else if (accountBalance - withdrawAmount < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        } else {                                                                    // If all checks pass, everything is okay
            this.accountBalance -= withdrawAmount;                                  // Subtract withdrawalAmount from accountBalance
            transactions.add("Withdrawal of $" + withdrawAmount + " made at " +     // add transaction to transactions ArrayList
                (LocalDateTime.now().format(customDateTimeFormat)) );  
        }
    }


    //      3. toString method to print out account information
    public String toString() {
        String accountDetails =                                         // Format for printing
            "Account Holder's Name: " + accountHolderName + "\n" +      // Account holder's name
            "Account Number: " + accountNumber + "\n" +                 // Account number
            "Account Balance: " + accountBalance + "\n" +               // Account balance
            "Account Transactions: " + transactions + "\n" +            // Account transactions
            "Account Open: " + (!isClosed) + "\n" +                     // Account open? Prints opposite of isCLosed
            "Account Creation date: " + accountCreationDate.format(customDateTimeFormat) + "\n" +    // Account creation date
            "Account Closing date: " + (accountClosingDate != null ? accountClosingDate.format(customDateTimeFormat) : "null");
                                     // accountClosingDate ISNOT null, print accountClosingDate.format(customerTimeFormat), else print "null"
        return accountDetails;
    }

}

