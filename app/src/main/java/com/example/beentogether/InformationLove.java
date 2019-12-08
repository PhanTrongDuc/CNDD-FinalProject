package com.example.beentogether;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class InformationLove extends AppCompatActivity {
    private ListView lvEvent;
    private ArrayList<Event> arrEvent;
    private CustomAdapter customAdapter;
    //private Context context;
    CalendarView cvEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_love);
        cvEvent=findViewById(R.id.cv_event);
        arrEvent= new ArrayList<>();
        lvEvent=findViewById(R.id.lv_event);


        customAdapter=new CustomAdapter(this, R.layout.row_listview, arrEvent);
        lvEvent.setAdapter(customAdapter);
        //context=this;


        cvEvent.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                arrEvent.add(new Event("Sinh nhật bạn ấy",(int) calendarView.getDate(),12,2019));
                customAdapter.notifyDataSetChanged();
            }
        });
    }

}
