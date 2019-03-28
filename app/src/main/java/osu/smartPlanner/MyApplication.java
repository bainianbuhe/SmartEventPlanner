package osu.smartPlanner;

import android.app.Application;

public class MyApplication extends Application {
    private String sortType = "";

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
