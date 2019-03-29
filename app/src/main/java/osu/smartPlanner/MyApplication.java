package osu.smartPlanner;

import android.app.Application;

public class MyApplication extends Application {
    private String sortType = "";
    private String location= "";
    private String username="";
    private double distance = 0;

    public String getSortType() {
        return sortType;
    }
    public String getLocation() {
        return location;
    }
    public String getUsername() {
        return username;
    }
    public double getDistance() { return distance; }



    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setDistance(double distance) { this.distance = distance; }

}
