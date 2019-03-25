package osu.smartPlanner;

import org.json.JSONObject;

public interface AsyncResponse {
    void processFinish(JSONObject output);
}