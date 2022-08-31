import java.io.*;
import java.util.ArrayList;

public class BankMain {

    public static void main(String[] args) {

        User user = ReadingWritingUserDB.loadUser("Jovan Jovaniovic Zmaj");

        user.printAllTransactions();
        System.out.println(user.getBalance());

        user.deposit(199D);
        ReadingWritingUserDB.storeUser(user);
    }
}
