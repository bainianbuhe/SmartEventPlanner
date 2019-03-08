package osu.smartPlanner;

public class Event {
    private String userName;
    private String title;
    private String description;
    private String time;
    private String location;
    private String priority;
    private String contacts;

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContacts() {
        return contacts;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getPriority() {
        return priority;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return userName;
    }
}
