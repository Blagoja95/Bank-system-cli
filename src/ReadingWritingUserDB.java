import java.io.*;
import java.util.ArrayList;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadingWritingUserDB {

    private static String dbDirectory = "db/";

    public static String getDbDirectory() {
        return dbDirectory;
    }

    public static String getDbDirectoryName () {
        return getDbDirectory().split("/")[0];
    }

    public static void setDbDirectory(String dbDirectory) {
        if (dbDirectory.length() == 0) {
            System.err.println("Path name can't be empty!");
            return;
        }
        ReadingWritingUserDB.dbDirectory = dbDirectory;
    }

    public static String getFilePathName(User user) {
        return getDbDirectory() + UserID.generateUserUniqueID(user.getFullName()) + ".db";
    }

    public static String getFilePathName(String name) {
        return getDbDirectory() + UserID.generateUserUniqueID(name) + ".db";
    }

    public static void storeUser(User newUser) {
        File userFile = new File(getFilePathName(newUser));

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
        File inFile = new File(getFilePathName(user));

        if (!inFile.exists()) {
            System.err.println("User is not in database!");
            return null;
        }

        try (ObjectInputStream inObjectFile = new ObjectInputStream(new FileInputStream(inFile))) {

            return (User) inObjectFile.readObject();

        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return null;
    }

    public static boolean deleteUser(User user) {
        File userFile = new File(getFilePathName(user));

        if (userFile.exists()) {
            userFile.delete();
            return true;
        }
        else
            return false;
    }

    public static Map<String, User> getAllUsers () {
        Map<String, User> users= new LinkedHashMap<>();
        File dir = new File(getDbDirectoryName());

        for (File file: dir.listFiles()){
            try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file))) {
                User user = (User) oin.readObject();
                users.put(user.getFullName(), user);

            } catch (EOFException ex){
                System.out.println("All users!");
            } catch (IOException | ClassNotFoundException ex){
                ex.printStackTrace();
            }
        }

        return users;
    }
}
