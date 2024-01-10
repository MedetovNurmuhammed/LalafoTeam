package models;

public class Announcement {
    private Long id;
    private String name;
    private String description;
    private int price;
    private User owner;
    private Favorite favorite = new Favorite();

    public Announcement(Long id, String name, String description, int price, User owner, Favorite favorite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.owner = owner;
        this.favorite = favorite;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    public Announcement() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Announcement{" +"\n"+
                "id=" + id +"\n"+
                "title='" + name + "\n"+
                "description='" + description + "\n"+
                "price=" + price +
                "owner=" + owner.getUserName() +"\n"+
                "favorite=" + favorite +"\n"+
                '}';
    }
}
