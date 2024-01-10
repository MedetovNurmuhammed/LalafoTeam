package interFaces;

import models.Announcement;
import models.User;

import java.util.List;

public interface AnnouncementInterface {
    String addAnnouncement(User user);

    List<Announcement> getAllAnnouncementsInUser(User user);

    String deleteAnnouncement(User user);

    String updateAnnouncement(User user);

    Announcement searchAnnouncement(String name, User user);

    Announcement searchAnnouncement(String name);

    void myfavorites(User user);

    void myProfile(User user);

   List <Announcement> analysis(User user);

}
