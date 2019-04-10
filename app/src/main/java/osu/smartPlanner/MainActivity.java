package osu.smartPlanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private CardView confirmNewEvent;
    private CardView confirmSortEvent;
    private String username;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username = null;
            } else {
                username = extras.getString("USERNAME");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("USERNAME");
        }

        confirmNewEvent = findViewById(R.id.newEventButton);

        confirmNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, EventActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            }
        });

    }
}
