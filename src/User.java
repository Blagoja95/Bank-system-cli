import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

public class User implements Serializable {
    private String fullName;
    private String userUniqueId;

    private LinkedList<Double> transactions = new LinkedList<>();

    private double balance;

    User(String fullName) {
        if (fullName.length() == 0) {
            System.out.println("Error, empty full name ");
            return;
        }

        this.fullName = fullName;
        balance = 0;
        userUniqueId = UserID.generateUserUniqueID(fullName);
    }

    User(String fullName, Double balance) {
        if (fullName.length() == 0) {
            System.out.println("Error, empty full name ");
            return;
        }

        if (balance < 0) {
            System.out.println("Error, balance can't be negative number");
            return;
        }

        this.fullName = fullName;
        this.balance = balance;
        userUniqueId = UserID.generateUserUniqueID(fullName);
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserId() {
        return userUniqueId;
    }

    public double getBalance() {
        return balance;
    }

    public void printAccInformation() {
        System.out.println("Full name: " + getFullName() +
                "\nID: " + userUniqueId + "\nBalance: " + String.format("%.2f", getBalance()));
    }

    @Override
    public String toString() {
        return "Full name: " + getFullName() +
                "\nID: " + userUniqueId + "\nBalance: " + String.format("%.2f", getBalance()) + "\n Transactions: " + transactions.toString();
    }


    public void deposit(Double amount) {
        if (amount <= 0) {
            System.err.println("Amount of money need to be greater than zero!");
            return;
        }

        addBalance(amount);
    }

    public void withdraw(Double amount) {
        if (balance <= 0) {
            System.err.println("Balance is zero! Add funds first! ");
            return;
        }

        if (amount <= 0) System.err.println("Amount of money need to be greater than zero!");

        addBalance(-amount); //negative value
    }

    private void addBalance(Double amount) {
        this.balance += amount;
        transactions.add(amount);
    }

    public void printAllTransactions() {
        System.out.println("Transactions for user: " + getFullName());
        System.out.println();

        for (Double i : transactions)
            System.out.println(": " + (i >= 0 ? " " : "") + i);

        System.out.println("\nEND OF TRANSACTIONS\n");
    }
}
