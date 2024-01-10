package interFaces;

import models.Announcement;
import models.Favorite;
import models.User;

import java.util.List;

public interface FavoriteInterface {
    String addFavoriteToAnnouncement(User user,Announcement announcement,Favorite favorite);
    String deleteFavoriteInAnnouncement(User user,Announcement announcement);
    List<User> getAllFavorites(Announcement announcement);
}
