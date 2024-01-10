package interFaces;

import models.Announcement;
import models.Favorite;
import models.User;

import java.util.List;

public interface FavoriteInterface {
    String addFavoriteToAnnouncement(User user);
    String deleteFavoriteInAnnouncement(User user);
    List<Favorite> getAllFavorites(Announcement announcement);
}
