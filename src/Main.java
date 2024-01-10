import daos.DataBase;
import daos.UserDao;
import models.Announcement;
import models.Favorite;
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
        FavoriteImpl favorite = new FavoriteImpl(userDao);
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
                                                    7.Chat
                                                    8.Update my profile
                                                    9.Delete my profile
                                                    10.My Profile
                                                    0.Exit
                                                                                       
                                                                                       
                                                    """);
                                            try {
                                                int set = new Scanner(System.in).nextInt();
                                                switch (set) {
                                                    case 1 -> {
                                                        System.out.println(announcement.addAnnouncement(currentUser));
                                                    }
                                                    case 2 -> {
                                                        try {
                                                            System.out.println(announcement.updateAnnouncement(currentUser));
                                                        } catch (Exception e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    case 3 -> {
                                                        System.out.println(announcement.deleteAnnouncement(currentUser));

                                                    }
                                                    case 4 -> {
                                                        System.out.println(announcement.getAllAnnouncementsInUser(currentUser));
                                                    }
                                                    case 5 -> {announcement.myfavorites(currentUser);

                                                    }
                                                    case 6 -> {
                                                        System.out.println(announcement.analysis(currentUser));
                                                    }

                                                    case 7 -> {
                                                    }
                                                    case 8 -> {
                                                        System.out.println(currentUser = user.updateProfile(currentUser));

                                                    }
                                                    case 9 -> {
                                                        try {
                                                            System.out.println(user.deleteProfile(currentUser));
                                                            break LOOP2;
                                                        } catch (Exception e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    case 10->{
                                                        announcement.myProfile(currentUser);
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
                                                        System.out.println(favorite.getMyLikeAnnouncements(currentUser));
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
                                                            System.out.println("Write title announcement: ");
                                                            Announcement delete = announcement.searchAnnouncement(new Scanner(System.in).nextLine());
                                                            System.out.println("ID: "+ delete.getId());
                                                            System.out.println("Name: "+ delete.getName());
                                                            System.out.println("Description: "+ delete.getDescription());
                                                            System.out.println("price: "+ delete.getPrice());
                                                            System.out.println("Owner: "+ delete.getOwner().getUserName());
                                                            System.out.println("Likes count: " + delete.getFavorite().getUserList().size());
                                                            System.out.println("Likes people: " + delete.getFavorite().getUserList());

                                                            System.out.println("Хотите поставить лайк? (55) ||  отменить лайк! (0)");
                                                            try {
                                                                int like = new Scanner(System.in).nextInt();
                                                                switch (like){
                                                                    case 55->{
                                                                        System.out.println(favorite.addFavoriteToAnnouncement(currentUser, delete,new Favorite()));
                                                                        System.out.println();
                                                                    }
                                                                    case 0->{
                                                                        System.out.println(favorite.deleteFavoriteInAnnouncement(currentUser, delete));

                                                                    }
                                                                }
                                                            }catch (InputMismatchException e){
                                                                System.err.println("Write number!");
                                                            }

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