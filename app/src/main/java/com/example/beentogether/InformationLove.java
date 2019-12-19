package com.example.beentogether;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class InformationLove extends AppCompatActivity {
    EvenDB evenDB= new EvenDB(this);
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
        arrEvent=evenDB.getAllEvent();
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

        lvEvent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(InformationLove.this);
                alert.setMessage("Bạn có muốn xóa sự kiện này không?");
                alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        arrEvent.remove(i);
                       evenDB.deleteEvent(arrEvent.get(i));
                        customAdapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //Nothing
                    }
                });
                alert.show();
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==4 && requestCode==4){
            Bundle bundle=data.getBundleExtra("setup");
            event=bundle.getString("event");
            Event eventadd= new Event(event, dayEvent, monthEvent, yearEvent);
            arrEvent.add(eventadd);
            evenDB.addEvent(eventadd);
            customAdapter.notifyDataSetChanged();
        }
    }
}