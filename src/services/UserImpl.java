package services;

import daos.UserDao;
import exceptions.Notfound;
import generics.GenericChecks;
import interFaces.UserInterface;
import models.Announcement;
import models.Role;
import models.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserImpl implements UserInterface {
    public static Long id = 0L;
    final UserDao userDao;

    public UserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String sign(User user) {

        while (true) {
            System.out.println("Choose role: (Vendor)||(user)");
            String role = new Scanner(System.in).nextLine();
            if (role.equalsIgnoreCase(Role.USER.toString())) {
                user.setId(++id);
                user.setRole(Role.USER);
                break;
            } else if (role.equalsIgnoreCase(Role.VENDOR.toString())) {
                user.setId(++id);
                user.setRole(Role.VENDOR);
                break;
            }
        }
        while (true) {
            System.out.println("Write user Name: ");
            String userName = new Scanner(System.in).nextLine();
            if (GenericChecks.unickalUserName(userName, userDao.getAllUsers())) {
                user.setUserName(userName);
                break;
            }
        }
        while (true) {
            System.out.println("Write email ");
            String email = new Scanner(System.in).nextLine();
            if (GenericChecks.unickalEmail(email, userDao.getAllUsers())) {
                user.setEmail(email);
                break;
            }
        }
        while (true) {
            System.out.println("Write password");
            String password = new Scanner(System.in).nextLine();
            if (password.length() > 3) {
                user.setPassword(password);
                break;
            }
        }

        userDao.add(user);

        return "Success";

    }

    @Override
    public User signUp(User user) {
        User admin = new User(1L, "Zaripbek", "zaripbek@gmail.com", "zaripbek1234", Role.ADMIN);
        userDao.add(admin);
        while (true) {
            System.out.println("Write email:");
            String email = new Scanner(System.in).nextLine();
            if (email.length() > 2) {
                user.setEmail(email);
                break;
            }
        }
        while (true) {
            System.out.println("Write password:");
            String password = new Scanner(System.in).nextLine();
            if (password.length() > 3) {
                user.setPassword(password);
                break;
            }
        }
        for (User user1 : userDao.getAllUsers()) {
            if (user1.getEmail().equalsIgnoreCase(user.getEmail()) && user1.getPassword().equalsIgnoreCase(user.getPassword())) {
                return user1;
            }
        }
        return null;
    }

    @Override
    public User updateProfile(User user) {
        LOOP:
        for (int i = 0; i < userDao.getAllUsers().size(); i++) {
            if (userDao.getAllUsers().get(i).getId().equals(user.getId())) {
                System.out.println("""
                        Choose command for update:
                        1.User name
                        2.Email
                        3.Password
                        0.Exit
                        """);
                try {
                    int action = new Scanner(System.in).nextInt();
                    switch (action) {
                        case 1 -> {
                            while (true) {
                                System.out.println("Write user name: ");
                                String userNAme = new Scanner(System.in).nextLine();
                                if (GenericChecks.unickalUserName(userNAme, userDao.getAllUsers())) {
                                    userDao.getAllUsers().get(i).setUserName(userNAme);
                                    return userDao.getAllUsers().get(i);
                                }
                            }
                        }
                        case 2 -> {
                            while (true) {
                                System.out.println("Write email: ");
                                String em = new Scanner(System.in).nextLine();
                                if (GenericChecks.unickalEmail(em, userDao.getAllUsers())) {
                                    userDao.getAllUsers().get(i).setEmail(em);
                                    return userDao.getAllUsers().get(i);
                                }
                            }
                        }
                        case 3 -> {
                            while (true) {
                                System.out.println("Write password: ");
                                String password = new Scanner(System.in).nextLine();
                                if (password.length() > 3) {
                                    userDao.getAllUsers().get(i).setPassword(password);
                                    return userDao.getAllUsers().get(i);
                                }
                            }
                        }
                        case 0 -> {
                            break LOOP;
                        }
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("Write number please!");
                }
            }
        }
        return user;
    }

    @Override
    public String deleteProfile(User user) {
        System.out.println("Write password for Delete");
        String password = new Scanner(System.in).nextLine();
        for (User i : userDao.getAllUsers()) {
            if (i.getPassword().equalsIgnoreCase(password) && user.getPassword().equalsIgnoreCase(password)) {
                userDao.deleteUser(user);
                return "Success";
            }
        }
        throw new Notfound("Password incorrect");
    }





    public String delete(Long id) {
        for (User allUser : userDao.getAllUsers()) {
            if (allUser.getId().equals(id)) {
                userDao.deleteUser(allUser);
                userDao.set(allUser);
                return "Success";
            }
        }
        throw new Notfound("Not found");
    }
}
