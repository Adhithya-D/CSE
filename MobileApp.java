package basic;

class Account {
    private String accountNumber;
    private double accountBalance;
    private String accountHolder;

    public Account(String accountNumber, double accountBalance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                ", accountHolder='" + accountHolder + '\'' +
                '}';
    }
}

class DebitCard {
    private String cardNumber;
    private String cardPin;

    public DebitCard(String cardNumber, String cardPin) {
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    public String withdraw(double amount, String enteredPin, Account account) {
        if (!enteredPin.equals(this.cardPin)) {
            return "Invalid PIN";
        }
        if (amount > account.getAccountBalance()) {
            return "Insufficient funds";
        }
        account.setAccountBalance(account.getAccountBalance() - amount);
        return "Withdrawal successful. New balance: " + account.getAccountBalance();
    }
}

class GPay {
    private String upiPin;
    private String username;

    public GPay(String upiPin, String username) {
        this.upiPin = upiPin;
        this.username = username;
    }

    public String payBills(String billerName, double billedAmount, String billerType, String enteredUpiPin, Account account) {
        if (!enteredUpiPin.equals(this.upiPin)) {
            return "Invalid UPI PIN";
        }
        if (billedAmount > account.getAccountBalance()) {
            return "Insufficient funds";
        }
        account.setAccountBalance(account.getAccountBalance() - billedAmount);
        return "Bill paid to " + billerName + " (" + billerType + ") for " + billedAmount + ". New balance: " + account.getAccountBalance();
    }
}

public class MobileApp {
    public static void main(String[] args) {
        Account account = new Account("123456", 5000, "John Doe");
        DebitCard debitCard = new DebitCard("1111-2222-3333-4444", "1234");
        GPay gpay = new GPay("5678", "john_doe");


        System.out.println(debitCard.withdraw(1000, "1234", account));
        System.out.println(debitCard.withdraw(1000, "0000", account));
        System.out.println(debitCard.withdraw(6000, "1234", account));


        System.out.println(gpay.payBills("Electric Company", 500, "Electricity", "5678", account));  // Correct UPI PIN
        System.out.println(gpay.payBills("Internet Provider", 1000, "Internet", "0000", account));  // Incorrect UPI PIN
        System.out.println(gpay.payBills("Water Company", 6000, "Water", "5678", account));  // Insufficient funds
    }
}

