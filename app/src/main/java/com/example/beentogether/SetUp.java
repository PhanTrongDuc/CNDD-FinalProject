package com.example.beentogether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SetUp extends AppCompatActivity {
    EditText edtLDay, edtLMonth, edtLYear;
    FloatingActionButton fbtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        edtLDay = findViewById(R.id.edt_lDay);
        edtLMonth = findViewById(R.id.edt_lMonth);
        edtLYear = findViewById(R.id.edt_lYear);
        fbtBack = findViewById(R.id.fbt_back);

        fbtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetUp.this, MainActivity.class);
                Bundle bundle = new Bundle();
                while (edtLDay.getText().toString()== null || edtLMonth.getText().toString() == null || edtLYear.getText().toString() == null) {
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
    }


}
