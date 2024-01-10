package generics;


import models.User;

import java.util.List;

public class GenericChecks {
    public static boolean checkForAll(String name) {
        return name.length() > 2;
    }

    public static boolean unickalEmail(String em, List<User> users) {
        User user = users.stream().filter(email -> email.getEmail().equalsIgnoreCase(em)).findFirst().orElse(new User());
        if (user.getPassword() != null){
            return false;

        }return em.endsWith("@gmail.com") && checkForAll(em);

    }

    public static boolean unickalUserName(String userName, List<User> users) {
        User user = users.stream().filter(email -> email.getUserName().equalsIgnoreCase(userName)).findFirst().orElse(new User());
        if (user.getPassword() != null) {
            return false;
        }return true;
    }
}
