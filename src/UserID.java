public class UserID {
    static final String generateUserUniqueID(String userName) {
        StringBuilder ID = new StringBuilder("");

        for (int i = 0; i < userName.length(); i++)
            if (i % 2 == 0)
                ID.append((int) userName.charAt(i));
            else if (userName.charAt(i) == ' ')
                ID.append("");
            else
                ID.append(userName.charAt(i));

        return ID.toString();
    }


}
