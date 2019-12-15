package com.example.beentogether;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class InformationLove extends AppCompatActivity {
    private ListView lvEvent;
    private ArrayList<Event> arrEvent;
    private CustomAdapter customAdapter;
    private String event="";
    private int dayEvent, monthEvent, yearEvent;
    CalendarView cvEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_love);
        cvEvent = findViewById(R.id.cv_event);
        arrEvent = new ArrayList<>();
        lvEvent = findViewById(R.id.lv_event);



        customAdapter = new CustomAdapter(this, R.layout.row_listview, arrEvent);
        lvEvent.setAdapter(customAdapter);
        cvEvent.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int day, int month, int year) {
                Intent intent= new Intent(InformationLove.this, SetUp.class);
                startActivityForResult(intent,4);
                dayEvent=day;
                monthEvent=month+1;
                yearEvent=year;

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==4 && requestCode==4){
            Bundle bundle=data.getBundleExtra("setup");
            event=bundle.getString("event");
            arrEvent.add(new Event(event, dayEvent, monthEvent, yearEvent));
            customAdapter.notifyDataSetChanged();
        }
    }
}