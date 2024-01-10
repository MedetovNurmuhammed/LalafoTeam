package services;

import daos.UserDao;
import exceptions.Notfound;
import generics.GenericChecks;
import interFaces.AnnouncementInterface;
import models.Announcement;
import models.User;

import java.util.*;

public class AnnouncementImpl implements AnnouncementInterface {
    public static Long id = 0L;
    final UserDao userDao;

    public AnnouncementImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String addAnnouncement(User user) {
        Announcement announcement = new Announcement();
        while (true) {
            System.out.println("Write title: ");
            String name = new Scanner(System.in).nextLine();
            if (GenericChecks.checkForAll(name)) {
                announcement.setOwner(user);
                announcement.setId(++id);
                announcement.setName(name);
                break;
            }
        }
        while (true) {
            System.out.println("Write description: ");
            String des = new Scanner(System.in).nextLine();
            if (GenericChecks.checkForAll(des)) {
                announcement.setDescription(des);
                break;
            }
        }
        while (true) {
            try {
                System.out.println("Write price: ");
                int price = new Scanner(System.in).nextInt();
                if (price > 0) {
                    announcement.setPrice(price);
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Write correct price");
            }
        }
        user.setAnnouncement(announcement);
        return "Success";
    }

    @Override
    public List<Announcement> getAllAnnouncementsInUser(User user) {
        return user.getAnnouncement();
    }

    @Override
    public String deleteAnnouncement(User user) {
        try {
            System.out.println("Write announcement id");
            Long id = new Scanner(System.in).nextLong();
            for (int i = 0; i < user.getAnnouncement().size(); i++) {
                if (user.getAnnouncement().get(i).getId().equals(id)) {
                    user.getAnnouncement().remove(i);
                    return "Success";
                }
            }
        } catch (InputMismatchException exception) {
            System.out.println("Write id");
        }
        throw new Notfound("Not found");
    }

    @Override
    public String updateAnnouncement(User user) {
        System.out.println("Write id: ");
        Long id = new Scanner(System.in).nextLong();
        for (int i = 0; i < user.getAnnouncement().size(); i++) {
            if (id.equals(user.getAnnouncement().get(i).getId())) {
                System.out.println("""
                        Choose command for update
                        1.Title
                        2.Description
                        3.Price
                        """);
                try {
                    int action = new Scanner(System.in).nextInt();
                    switch (action) {
                        case 1 -> {
                            while (true) {
                                System.out.println("Write title: ");
                                String title = new Scanner(System.in).nextLine();
                                if (GenericChecks.checkForAll(title)) {
                                    user.getAnnouncement().get(i).setName(title);
                                    return "Success";
                                }
                            }

                        }
                        case 2 -> {
                            while (true) {
                                System.out.println("Write description: ");
                                String des = new Scanner(System.in).nextLine();
                                if (GenericChecks.checkForAll(des)) {
                                    user.getAnnouncement().get(i).setDescription(des);
                                    return "Success";
                                }
                            }
                        }
                        case 3 -> {
                            while (true) {
                                System.out.println("Write price: ");
                                int price = new Scanner(System.in).nextInt();
                                if (price > 0) {
                                    user.getAnnouncement().get(i).setPrice(price);
                                    return "Success";
                                }
                            }
                        }
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("Write number");

                }
            }
        }
        throw new Notfound("Not found");
    }

    @Override
    public Announcement searchAnnouncement(String name, User user) {
        for (int i = 0; i < user.getAnnouncement().size(); i++) {
            if (name.equalsIgnoreCase(user.getAnnouncement().get(i).getName())) {
                return user.getAnnouncement().get(i);
            }
        }
        throw new Notfound("Not found");
    }

    @Override
    public Announcement searchAnnouncement(String name) {
        List<Announcement> announcements = new ArrayList<>();
        for (int i = 0; i < userDao.getAllUsers().size(); i++) {
            announcements.addAll(userDao.getAllUsers().get(i).getAnnouncement());
        }
        for (int i = 0; i < announcements.size(); i++) {
            if (name.equalsIgnoreCase(announcements.get(i).getName())) {
                return announcements.get(i);
            }
        }
        throw new Notfound("Not found");
    }

    @Override
    public void myfavorites(User user) {

          boolean isTrue = true;
     do {System.out.println("Write id Announcement:");
         Long id = new Scanner(System.in).nextLong();


         isTrue = false;

         for (int i = 0; i < user.getAnnouncement().size(); i++) {
             if (id.equals(user.getAnnouncement().get(i).getId())) {
                 System.out.println("Name Announcement: "+user.getAnnouncement().get(i).getName());
                 System.out.println("Count likes: " + user.getAnnouncement().get(i).getFavorite().getUserList().size());
                 System.out.println("Likes people:" + user.getAnnouncement().get(i).getFavorite().getUserList());
                 isTrue = true;
                 break;
             }
         }

         if (!isTrue) {
             System.err.println("Not found! ");
             break;
         }

     } while (!isTrue);
    }

    @Override
    public void myProfile(User user) {
        System.out.println("UserName: "+user.getUserName());
        System.out.println("Count Announcement:"+user.getAnnouncement().size());
        for (Announcement announcement : user.getAnnouncement()) {
        System.out.println("Count Likes :"+announcement.getFavorite().getUserList().size());
    }


}

    @Override
    public List<Announcement> analysis(User user) {
        Comparator <Announcement> sort  = new Comparator<Announcement>() {
            @Override
            public int compare(Announcement o1, Announcement o2) {
                return o2.getFavorite().getUserList().size() - o1.getFavorite().getUserList().size();
            }
        };
        List<Announcement> list = getAllAnnouncementsInUser(user);
        list.sort(sort);
        return list;
    }
}


