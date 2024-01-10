package models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Chat> chatPoleskelgen = new ArrayList<>();
    private List<Chat> chatPolesketken = new ArrayList<>();
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Role role;
    private List<Announcement> announcement = new ArrayList<>();


    public User() {
    }

    public User(Long id, String userName, String email, String password, Role role, List<Announcement> announcement) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.announcement = announcement;
    }

    public User(Long i, String zaripbek, String mail, String zaripbek1234, Role role) {
        this.id = i;
        this.userName = zaripbek;
        this.email = mail;
        this.password = zaripbek1234;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Announcement> getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement.add(announcement);
    }


    public List<Chat> getChatPoleskelgen() {
        return chatPoleskelgen;
    }

    public void setChatPoleskelgen(List<Chat> chatPoleskelgen) {
        this.chatPoleskelgen = chatPoleskelgen;
    }

    public List<Chat> getChatPolesketken() {
        return chatPolesketken;
    }

    public void setChatPolesketken(List<Chat> chatPolesketken) {
        this.chatPolesketken = chatPolesketken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", announcement=" + announcement +
                '}';
    }
}
