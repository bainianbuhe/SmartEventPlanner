package osu.smartPlanner;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.CardView;

public class EventListActivity extends SingleFragmentActivity {
    private String userName;
    private CardView confirmNewEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        userName=getIntent().getStringExtra("USERNAME");
        super.onCreate(savedInstanceState);
        confirmNewEvent = findViewById(R.id.newEventButton);
        confirmNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventListActivity.this, EventActivity.class);
                Log.e("TAG1", userName);
                intent.putExtra("USERNAME", userName);
                startActivity(intent);
            }
        });
    }
    @Override
    protected Fragment createFragment(){
        EventListFragment e = new EventListFragment();
        e.setUserName(userName);
        return e;
    }
}
