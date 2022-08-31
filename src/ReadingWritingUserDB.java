import java.io.*;
import java.util.ArrayList;
import java.io.File;

public class ReadingWritingUserDB {

    private static String dbDirectory = "./db/";

    public static String getDbDirectory() {
        return dbDirectory;
    }

    public static void setDbDirectory(String dbDirectory) {
        if (dbDirectory.length() == 0) {
            System.out.println("path name can't be empty!");
            return;
        }
        ReadingWritingUserDB.dbDirectory = dbDirectory;
    }

    public static void storeUser(User newUser) {
        File userFile = new File(getDbDirectory() + newUser.getUserId() + ".db");

        if (!userFile.exists())
            try {
                userFile.createNewFile();
            } catch (IOException ob) {
                ob.printStackTrace();
            }

        try (ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(userFile))) {

            obOut.writeObject(newUser);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static User loadUser(String user) {
        File inFile = new File(getDbDirectory() + UserID.generateUserUniqueID(user) + ".db");

        if (!inFile.exists()) {
            System.out.println("User is not in db!");
            return null;
        }

        try (ObjectInputStream inObjectFile = new ObjectInputStream(new FileInputStream(inFile))) {

            return (User) inObjectFile.readObject();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return null;
    }
}
