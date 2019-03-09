package osu.smartPlanner;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class EventListActivity extends SingleFragmentActivity {
    private String userName;
    private static final String EXTRA_USERNAME="userName";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        userName=getIntent().getStringExtra(EXTRA_USERNAME);
        Log.e("TAGBOSS","eventlistactivity username is"+userName);
    }
    @Override
    protected Fragment createFragment(){
        Log.e("TAGBOSS","eventlistactivity in createfragment username is"+userName);
        EventListFragment e=new EventListFragment();
        e.setUserName(userName);
        return e;
    }
}
