import daos.DataBase;
import daos.UserDao;
import models.Announcement;
import models.Role;
import models.User;
import services.AnnouncementImpl;
import services.FavoriteImpl;
import services.UserImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        UserDao userDao = new UserDao(dataBase);
        AnnouncementImpl announcement = new AnnouncementImpl(userDao);
        FavoriteImpl favorite = new FavoriteImpl();
        UserImpl user = new UserImpl(userDao);
        LOOP1:
        while (true) {
            User currentUser = new User();
            System.out.println("""
                    1.Sign
                    2.SignUp
                    0.Exit
                    """);
            try {
                int action = new Scanner(System.in).nextInt();
                switch (action) {
                    case 1 -> {
                        System.out.println(user.sign(new User()));
                    }
                    case 2 -> {
                        try {
                            LOOP2:
                            while (true) {
                                User deleteuser = user.signUp(new User());
                                if (deleteuser == null) {
                                    System.out.println("Invalid info");
                                    break LOOP2;
                                } else {
                                    currentUser = deleteuser;
                                    System.out.println("Welcome " + currentUser.getUserName());
                                    if (currentUser.getRole().name().equalsIgnoreCase(Role.VENDOR.name())) {
                                        LOOP3:
                                        while (true) {
                                            System.out.println("""
                                                    Choose command:
                                                    1.Add new announcement
                                                    2.Update announcement
                                                    3.Delete announcement
                                                    4.Get all my announcements
                                                    5.My favorites
                                                    6.Analysis
                                                    7.All Income
                                                    8.Chat
                                                    9.Update my profile
                                                    10.Delete my profile
                                                    0.Exit
                                                                                       
                                                                                       
                                                    """);
                                            try {
                                                int set = new Scanner(System.in).nextInt();
                                                switch (set) {
                                                    case 1 -> {
                                                        System.out.println(announcement.addAnnouncement(currentUser));
                                                    }
                                                    case 2 -> {
                                                    }
                                                    case 3 -> {
                                                    }
                                                    case 4 -> {
                                                    }
                                                    case 5 -> {
                                                    }
                                                    case 6 -> {
                                                    }
                                                    case 7 -> {
                                                    }
                                                    case 8 -> {
                                                    }
                                                    case 9 -> {
                                                        System.out.println(currentUser = user.updateProfile(currentUser));

                                                    }
                                                    case 10 -> {
                                                        try {
                                                            System.out.println(user.deleteProfile(currentUser));
                                                            break LOOP2;
                                                        } catch (Exception e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    case 0 -> {
                                                        break LOOP3;
                                                    }

                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Write number");
                                            }
                                        }
                                    } else if (currentUser.getRole().toString().equalsIgnoreCase(Role.ADMIN.name())) {
                                        LOOP4:
                                        while (true) {
                                            System.out.println("""
                                                    Choose command:
                                                    1.Complaints
                                                    2.Delete announcement
                                                    3.Delete User && Vendor
                                                    5.Analysis
                                                    6.All Income
                                                    7.Update my profile
                                                    0.Exit
                                                                                       
                                                                                       
                                                    """);
                                            try {
                                                int set = new Scanner(System.in).nextInt();
                                                switch (set) {
                                                    case 3 -> {
                                                        while (true) {
                                                            System.out.println("Write id");
                                                            try {
                                                                long id = new Scanner(System.in).nextLong();
                                                                if (id > 0) {
                                                                    System.out.println(user.delete(id));
                                                                    break;
                                                                }
                                                            } catch (InputMismatchException exception) {
                                                                System.out.println("Write number > 0");
                                                            }
                                                        }

                                                    }
                                                    case 0 -> {
                                                        break LOOP4;
                                                    }
                                                }
                                            } catch (InputMismatchException exception) {
                                                System.out.println("Write number!");

                                            }
                                        }
                                    } else if (currentUser.getRole().toString().equalsIgnoreCase(Role.USER.name())) {
                                        LOOP5:
                                        while (true) {
                                            System.out.println("""
                                                    Choose command:
                                                    1.My favorites
                                                    2.Chat
                                                    3.Update my profile
                                                    4.Delete my profile
                                                    5.Search announcement by name
                                                    0.Exit
                                                                                                        
                                                                                       
                                                                                      
                                                    """);
                                            try {
                                                int set = new Scanner(System.in).nextInt();
                                                switch (set) {
                                                    case 1 -> {
                                                    }
                                                    case 2 -> {
                                                    }
                                                    case 3 -> {
                                                        System.out.println(currentUser = user.updateProfile(currentUser));
                                                    }
                                                    case 4 -> {
                                                        try {
                                                            System.out.println(user.deleteProfile(currentUser));
                                                            break LOOP2;
                                                        } catch (Exception e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    case 5 -> {
                                                        try {
                                                            System.out.println(announcement.searchAnnouncement(new Scanner(System.in).nextLine()));

                                                        } catch (Exception e) {
                                                            System.out.println(e.getMessage());
                                                        }

                                                    }
                                                    case 0 -> {
                                                        break LOOP5;
                                                    }

                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Write number");
                                            }
                                        }

                                    }
                                }


                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 0 -> {
                        break LOOP1;
                    }
                }
            } catch (InputMismatchException exception) {
                System.out.println("Write number");
            }
        }
    }
}