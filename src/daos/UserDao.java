package daos;

import models.User;

import java.util.List;

public class UserDao {
    final DataBase dataBase;

    public UserDao(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void add(User user) {
        dataBase.userList.add(user);
    }

    public List<User> getAllUsers() {
        return dataBase.userList;
    }

    public void deleteUser(User user) {
        dataBase.userList.remove(user);
    }

    public void set(User user) {
        dataBase.removeUsers.add(user);
    }
}
