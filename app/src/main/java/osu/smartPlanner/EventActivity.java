package osu.smartPlanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class EventActivity extends AppCompatActivity {
    private EditText titleField;
    private EditText descriptionField;
    private EditText timeField;
    private EditText locationField;
    private EditText contactsField;
    private EditText priorityField;
    private String username;
    private String title;
    private String description;
    private String time;
    private String location;
    private String contacts;
    private String priority;
    private CardView confirmAddEvent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

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

        titleField = findViewById(R.id.eventTittle);
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        descriptionField = findViewById(R.id.eventDescription);
        descriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        timeField = findViewById(R.id.eventTime);
        timeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                time = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        locationField = findViewById(R.id.eventLocation);
        locationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                location = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        contactsField = findViewById(R.id.eventContacts);
        contactsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contacts = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        priorityField = findViewById(R.id.eventPriority);
        priorityField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                priority = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmAddEvent = findViewById(R.id.addEventButton);
        confirmAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event();
                event.setUserName(username);
                event.setTitle(title);
                event.setDescription(description);
                event.setTime(time);
                event.setLocation(location);
                event.setContacts(contacts);
                event.setPriority(priority);
                UserDAO.addEvent(event,EventActivity.this);
                Intent intent = new Intent(EventActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}





