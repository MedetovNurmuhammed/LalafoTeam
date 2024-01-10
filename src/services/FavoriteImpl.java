package services;

import daos.DataBase;
import daos.UserDao;
import interFaces.FavoriteInterface;
import models.Announcement;
import models.Favorite;
import models.User;

import java.util.List;
import java.util.Scanner;

public class FavoriteImpl implements FavoriteInterface {
    final UserDao userDao;
    public static long id = 0L;

    public FavoriteImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String addFavoriteToAnnouncement(User user, Announcement announcement,Favorite favorite) {
        favorite.setId(++id);
        favorite.setUserList(user);
        announcement.setFavorite(favorite);
        return "Success";
    }

    @Override
    public String deleteFavoriteInAnnouncement(User user, Announcement announcement) {
        if (announcement.getFavorite().getUserList().remove(user)){
            return "Success";
        }return "Error";

    }


    @Override
    public List<User> getAllFavorites(Announcement announcement) {
        return announcement.getFavorite().getUserList();
    }
}
