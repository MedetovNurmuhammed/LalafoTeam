package services;

import daos.UserDao;
import models.Chat;
import models.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChatIml {
    final UserDao userdao;

    public ChatIml(UserDao userdao) {
        this.userdao = userdao;
    }

    public User addnewChat(User userPole, List<Chat> chatPoles) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write login for sent messsege");
        String name = scanner.nextLine();
        for (int i = 0; i < userdao.getAllUsers().size(); i++) {
            Chat chatPole = new Chat();
            if (name.equalsIgnoreCase(userdao.getAllUsers().get(i).getUserName())) {
                chatPole.setUser1(userPole);
                chatPole.setUser2(userdao.getAllUsers().get(i));
                System.out.println("Write messege : ");
                String messege = scanner.nextLine();
                chatPole.setMessege(messege);
                chatPoles.add(chatPole);
                userPole.setChatPolesketken(chatPoles);
                chatPole.seechat(userPole, userdao.getAllUsers().get(i));
                System.out.println("Success");
                return userPole;
            }
        }
        return null;
    }

}