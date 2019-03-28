package osu.smartPlanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    private EditText titleField;
    private EditText descriptionField;
    private EditText timeField;
    private EditText contactsField;
    private Spinner priorityField;
//    private EditText priorityField;
    private EditText locationField;
    private TextView addButtonName;
    private TextView deleteButtonName;
    private boolean update;
    private String username;
    private String originalTitle;
    private String title;
    private String description;
    private String time;
    private String location;
    private String contacts;
    private String priority;
    private CardView confirmAddEvent;
    private CardView confirmDeleteEvent;
    private CardView mSelectLocation;
    public static boolean isLocationComplete=false;
    private static final String TAG="eventactivity";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        addItemsOnPriority();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username = null;
                title = null;
                description = null;
                time = null;
                location = null;
                contacts = null;
                priority = null;
                update = false;
            } else {
                username = extras.getString("USERNAME");
                title = extras.getString("TITLE");
                description = extras.getString("DESCRIPTION");
                time = extras.getString("TIME");
                location = extras.getString("LOCATION");
                contacts = extras.getString("CONTACTS");
                priority = extras.getString("PRIORITY");
                update = extras.getBoolean("UPDATE");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("USERNAME");
            title = (String) savedInstanceState.getSerializable("TITLE");
            description = (String) savedInstanceState.getSerializable("DESCRIPTION");
            location = (String) savedInstanceState.getSerializable("LOCATION");
            contacts = (String) savedInstanceState.getSerializable("CONTACTS");
            priority = (String) savedInstanceState.getSerializable("PRIORITY");
            update = (Boolean) savedInstanceState.getSerializable("UPDATE");
        }


        originalTitle = title;

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

        if (!isLocationComplete) {
        mSelectLocation=findViewById(R.id.selectLocation);
        mSelectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!update) {

                    Log.d(TAG,"click select location");
                    Intent intent = new Intent(EventActivity.this, SelectLocationActivity.class);
                    intent.putExtra("Get_Location",location);
                    startActivity(intent);

                }
            }
        });} else {
            String locationName= getIntent().getStringExtra("Location_Name");
            locationField.setText(locationName);


        }

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

        addButtonName = findViewById(R.id.textAddEvent);
        deleteButtonName = findViewById(R.id.textDeleteEvent);
        confirmDeleteEvent = findViewById(R.id.deleteEventButton);
        if (update) {
            titleField.setText(title);
            descriptionField.setText(description);
            timeField.setText(time);
            contactsField.setText(contacts);
            addButtonName.setText("Update");
            deleteButtonName.setText("Delete");
        } else {
            confirmDeleteEvent.setVisibility(View.GONE);
            addButtonName.setText("Add");
        }


        confirmAddEvent = findViewById(R.id.addEventButton);
        confirmAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(String.valueOf(priorityField.getSelectedItem())) {
                    case "High":
                        priority = "3";
                        break;
                    case "Regular":
                        priority = "2";
                        break;
                    case "Low":
                        priority = "1";
                        break;
                    default:
                        priority = "1";
                }
                Log.d(TAG,"confirm add event");
                Event event = new Event();
                event.setUserName(username);
                event.setTitle(title);
                event.setDescription(description);
                event.setTime(time);
                event.setLocation(location);
                event.setContacts(contacts);
                event.setPriority(priority);

                if (!update) {
                    UserDAO.addEvent(event, EventActivity.this);
                } else {
                    UserDAO.updateEvent(username, originalTitle, event, EventActivity.this);
                }
                Intent intent = new Intent(EventActivity.this, EventListActivity.class);
                intent.putExtra("USERNAME",username);
                startActivity(intent);
            }
        });

        confirmDeleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"confirm delete event");
                Event event = new Event();
                event.setUserName(username);
                event.setTitle(title);
                event.setDescription(description);
                event.setTime(time);
                event.setLocation(location);
                event.setContacts(contacts);
                event.setPriority(priority);

                UserDAO.deleteEvent(username, originalTitle, EventActivity.this);
                Intent intent = new Intent(EventActivity.this, EventListActivity.class);
                intent.putExtra("USERNAME",username);
                startActivity(intent);
            }
        });
    }

    public void addItemsOnPriority() {

        priorityField = (Spinner) findViewById(R.id.eventPriority);
        List<String> list = new ArrayList<String>();
        list.add("High");
        list.add("Regular");
        list.add("Low");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priorityField.setAdapter(dataAdapter);
    }

}





