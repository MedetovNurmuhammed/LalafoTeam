package models;

public class Chat {
    private User user1;
    private User user2;
    private String chat;

    public Chat() {
    }

    public Chat(User user1, User user2, String messege) {
        this.user1 = user1;
        this.user2 = user2;
        this.chat = messege;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getMessege() {
        return chat;
    }

    public void setMessege(String messege) {
        this.chat = messege;
    }

    @Override
    public String toString() {
        return "ChatPole{" +
                " You   " +
                user2.getUserName() +
                ", chat='" + chat + '\'' +
                '}';
    }

    public void seechat(User currentUser, User user2) {
        int currentUserChatLength = currentUser.getChatPolesketken().size();
        int user2ChatLength = user2.getChatPolesketken().size();

        int maxLength = Math.max(currentUserChatLength, user2ChatLength);

        for (int i = 0; i < maxLength; i++) {
            String currentUserMessage = (i < currentUserChatLength) ? currentUser.getChatPolesketken().get(i).getMessege() : "";
            String user2Message = (i < user2ChatLength) ? user2.getChatPolesketken().get(i).getMessege() : "";
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("| " + currentUser.getUserName() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + user2.getUserName() + " |");
            System.out.println("| " + currentUserMessage + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t| " + user2Message + " |");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
        }
    }

}
