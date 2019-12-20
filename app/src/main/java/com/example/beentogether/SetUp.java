package com.example.beentogether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SetUp extends AppCompatActivity {
    EditText edtLDay, edtLMonth, edtLYear, edtEvent;
    FloatingActionButton fbtBack, fbtAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        edtLDay = findViewById(R.id.edt_lDay);
        edtLMonth = findViewById(R.id.edt_lMonth);
        edtLYear = findViewById(R.id.edt_lYear);
        edtEvent = findViewById(R.id.edt_event);
        fbtBack = findViewById(R.id.fbt_back);
        fbtAdd = findViewById(R.id.fab_done);

        fbtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetUp.this, MainActivity.class);
                Bundle bundle = new Bundle();
                while (edtLDay.getText().toString() == null || edtLMonth.getText().toString() == null || edtLYear.getText().toString() == null) {
                    Toast.makeText(SetUp.this, "Bạn chưa nhập ngày bắt đầu yêu", Toast.LENGTH_SHORT).show();
                    finish();
                }
                bundle.putString("day", edtLDay.getText().toString());
                bundle.putString("month", edtLMonth.getText().toString());
                bundle.putString("year", edtLYear.getText().toString());
                intent.putExtra("setup", bundle);
                setResult(1, intent);
                finish();
            }
        });

        fbtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SetUp.this, InformationLove.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("event", edtEvent.getText().toString());
                intent1.putExtra("setup", bundle1);
                setResult(4, intent1);
                finish();
            }
        });

    }


}