import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class BankMain {
    public static void printMainMenu() {
        System.out.println("Welcome\n\n1. See All Users in Database" +
                "\n2. Create a User\n3. Find user\nType 'quit' or 'q' to Quit");
    }

    public static void main(String[] args) {
        BankMain bank = new BankMain();

        printMainMenu();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            boolean work = true;
            while (work) {
                String userInput = input.readLine();

                switch (userInput) {
                    case "1":
                        for (Map.Entry<String, User> userSet : ReadingWritingUserDB.getAllUsers().entrySet())
                            System.out.println("# " + userSet.getValue().getFullName() + "\n");
                        printMainMenu();
                        break;

                    case "2":
                        System.out.print("What is a full name of the user: ");

                        do {
                            userInput = input.readLine().trim();

                            if (userInput.length() == 0)
                                System.out.println("User's full name can't be empty!");
                        } while (userInput.length() < 5);


                        ReadingWritingUserDB.storeUser(new User(userInput));
                        System.out.println("User created!\n");
                        printMainMenu();
                        break;

                    case "3":
                        Map<String, User> users = ReadingWritingUserDB.getAllUsers();

                        System.out.println("Search by full name: ");
                        do {
                            userInput = input.readLine().trim();

                            if (userInput.length() == 0)
                                System.out.println("User's full name can't be empty!");
                        } while (userInput.length() < 5);

                        if (users.containsKey(userInput)) {
                            System.out.println("TODO user acc manipulations");
                            users.get(userInput).printAccInformation();
                        } else {
                            System.out.println("There is no user with a name '" + userInput + "'\n");
                        }

                        printMainMenu();
                        break;

                    case "q":
                        System.out.println("Good buy");
                        work = false;
                        break;

                    case "quit":
                        System.out.println("Good buy");
                        work = false;
                        break;

                    default:
                        System.out.println("Wrong command!\n");
                        printMainMenu();
                        break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
