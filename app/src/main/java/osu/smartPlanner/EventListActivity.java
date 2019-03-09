package osu.smartPlanner;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class EventListActivity extends SingleFragmentActivity {
    private String userName;
    private static final String EXTRA_USERNAME="userName";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        userName=getIntent().getStringExtra(EXTRA_USERNAME);
    }
    @Override
    protected Fragment createFragment(){
        EventListFragment e=new EventListFragment();
        e.setUserName("test");
        return e;
    }
}
