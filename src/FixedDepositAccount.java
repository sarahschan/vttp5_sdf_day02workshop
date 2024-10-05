package src;

public class FixedDepositAccount extends BankAccount {      // Inherits BankAccount class

    private float interest;                                 // New member - interest
    private boolean defaultInterest;                        // Is this the default interest amount?
    private int durationInMonths;                           // New member - duration in months
    private boolean defaultDuration;                        // Is this the default duration?
    
    
    // Create constructors
    //      1. Two parameters - accountHolderName, accountBalance
    public FixedDepositAccount(String accountHolderName, float accountBalance) {
        super(accountHolderName, accountBalance);           // Inherits constructor from BankAccount
        this.interest = 3;                                  // Specify default interest
        this.defaultInterest = true;                        // Set if this is the default interest
        this.durationInMonths = 6;                          // Specify default duration
        this.defaultDuration = true;                        // Set if this is the default duration
    }

    //      2. Three parameters - accountName, accountBalance, interest
    public FixedDepositAccount(String accountHolderName, float accountBalance, float interest) {
        super(accountHolderName, accountBalance);
        this.interest = interest;                           // Takes in parameter when instantiated
        if (this.interest == 3) {                           // Check if an idiot decided to "change" to the default value
            this.defaultDuration = true;                    //      if they did, leave the boolean as true;
        } else {                                            // For all other values, this is the first change to the interest amount
            this.defaultInterest = false;                   //      change the boolean to false;
        }
        this.durationInMonths = 6;                          // Set defaul duration
        this.defaultDuration = true;                        // Set if this is the default duration
    }

    //      3. Four parameters - accountName, accountBalance, interest, duration
    public FixedDepositAccount(String accountHolderName, float accountBalance, float interest, int durationInMonths) {
        super(accountHolderName, accountBalance);
        this.interest = interest;                           // Takes in parameter when instantiated
        if (this.interest == 3) {                           // Same check as above
            this.defaultDuration = true;
        } else {
            this.defaultInterest = false;
        }
        this.durationInMonths = durationInMonths;           // Take in parameter when instantiated
        if (this.durationInMonths == 6) {                   // Same idiot check as for interest
            this.defaultDuration = true;
        } else {
            this.defaultDuration = false;
        }
    }


    // Create getters for FixedDepositAccount's fields 
    //      (getters for fields in BankAccount will inherit - no need to recreate)
    public float getInterest() {
        return this.interest;
    }

    public boolean isDefaultInterest() {
        return this.defaultInterest;
    }

    public int getDurationInMonths() {
        return this.durationInMonths;
    }

    public boolean isDefaultDuration() {
        return this.defaultDuration;
    }


    // Create setters for appropriate fields
    public void setInterest(float interest) {
        if (interest == this.interest){                     // If idiot tried to 'change' interest rate to what it already is
            throw new IllegalArgumentException("Your interest rate is already " + interest + "%");
        } else if (!defaultInterest) {                      // If defaultInterest is false
            throw new IllegalArgumentException("Interest has been changed previously, you cannot change it again");
        } else {                                            // If everything is okay
            this.interest = interest;
            this.defaultInterest = false;
        }
    }

    public void setDurationInMonths(int durationInMonths) {
        if (durationInMonths == this.durationInMonths){     // If idiot tried to 'change' duration to what it already is
            throw new IllegalArgumentException("Your duration is already " + durationInMonths + " months");
        } else if (!defaultDuration) {                      // If defaultDuration is false
            throw new IllegalArgumentException("Duration has been changed previously, you cannot change it again");
        } else {                                            // If everything is okay
            this.durationInMonths = durationInMonths;
            this.defaultDuration = false;
        }
    }


    // Override methods
    //      1. Override deposit to a NOP
    @Override
    public void deposit(float depositAmount) {

    }

    //      2. Override withdraw to a NOP
    @Override
    public void withdraw(float withdrawAmount) {

    }

    //      3. Override toString to include additional information
    @Override
    public String toString() {
        String fixedDepositAccountToString = "Account Holder's Name: " + getAccountHolderName() + "\n" +
                                             "Account Number: " + getAccountNumber() + "\n" +
        /* everything is now a getter  */    "Account Balance: " + getAccountBalance() + "\n" +
        /* because of private fields   */    "Account Transactions: " + getTransactions() + "\n" +
        /* in parent class BankAccount */    "Account Open: " + (!isClosed()) + "\n" +
        /* have to use getters to      */    "Account Creation date: " + getAccountCreationDate().format(customDateTimeFormat) + "\n" +    // Account creation date
        /* access                      */    "Account Closing date: " + (getAccountClosingDate() != null ? getAccountClosingDate().format(customDateTimeFormat) : "null") + "\n" +
                                             "Interest rate: " + this.interest + "%\n" +
                                             "Default interest rate: " + this.defaultInterest + "\n" +
                                             "Duration in months: " + this.durationInMonths + "\n" +
                                             "Default duration: " + this.defaultDuration + "\n";
        return fixedDepositAccountToString;
    }

    //      4. Override getBalance to return balance plus interest
    @Override
    public float getAccountBalance() {
        float fdaAccountBalance = super.getAccountBalance() + this.interest;    // Inherited getter super.getAccountBalance()
        return fdaAccountBalance;
    }


}

