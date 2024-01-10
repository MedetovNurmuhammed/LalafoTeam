package interFaces;

import models.Announcement;
import models.User;

public interface UserInterface {
    String sign(User user);
    User signUp(User user);
    User updateProfile(User user);
    String deleteProfile(User user);



}
