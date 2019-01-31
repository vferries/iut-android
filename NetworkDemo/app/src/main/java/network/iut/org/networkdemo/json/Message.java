package network.iut.org.networkdemo.json;

import java.util.List;

public class Message {
    private long id;
    private String text;
    private User user;
    private List<Double> geo;


    public Message(long id, String text, User user, List<Double> geo) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.geo = geo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Double> getGeo() {
        return geo;
    }

    public void setGeo(List<Double> geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return id + "\n" + text + "\nFrom " + user.getUsername() + " with " + user.getFollowersCount() + " followers\n" + ((geo != null) ? "coords [" + geo.get(0) + " " + geo.get(1) + "]" : "") + "\n\n";
    }
}
