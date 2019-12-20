package com.example.beentogether;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView tvBoyName, tvBoyPhone, tvGirlName, tvGirlPhone;
    TextView tvLoveDay;
    FloatingActionButton setUp, love;
    ImageButton avtBoy, avtGirl;
    String loveTime = "0", day = "1", month = "1", year = "1", boyName = "", girlName = "", boyPhone, girlPhone;
    MyDataBase myDataBase = new MyDataBase(this);
    Information informationBoy=new Information();
    Information informationGirl= new Information();
    List<Information> information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvBoyName = findViewById(R.id.tv_boyName);
        tvBoyPhone = findViewById(R.id.tv_boyPhone);
        tvGirlName = findViewById(R.id.tv_girlName);
        tvGirlPhone = findViewById(R.id.tv_girlPhone);

        setUp = findViewById(R.id.fbt_setUp);
        love = findViewById(R.id.fbt_love);
        avtBoy = findViewById(R.id.fbt_avtMan);
        avtGirl = findViewById(R.id.fbt_avtWoman);
        tvLoveDay = findViewById(R.id.tv_loveDay);
        information = new ArrayList<>();

        information = myDataBase.getAllInformation();

        if (information.size() >=2) {
            informationGirl = information.get(information.size()-1);
            informationBoy = information.get(information.size()-2);
            tvBoyName.setText(informationBoy.getName());
            tvBoyPhone.setText(informationBoy.getPhone());
            tvGirlName.setText(informationGirl.getName());
            tvGirlPhone.setText(informationGirl.getPhone());
            tvLoveDay.setText(informationGirl.getDate());
        }


        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InformationLove.class);
                startActivity(intent);
            }
        });


        setUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetUp.class);
                startActivityForResult(intent, 1);
            }
        });

        avtBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditBoyInformation.class);
                startActivityForResult(intent, 2);
            }
        });

        avtGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditGirlInformation.class);
                startActivityForResult(intent, 3);
            }
        });




    }

    private String getLoveTime(Calendar start, Calendar end) {
        long duration = end.getTimeInMillis() - start.getTimeInMillis();
        long days = TimeUnit.MILLISECONDS.toDays(duration);

        long remainingHours = duration - TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(remainingHours);

        long remainingMinutes = remainingHours - TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingMinutes);
        String result = days + " ngày";
        informationBoy.setDate(result);
        informationGirl.setDate(result);
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            Bundle bundle = data.getBundleExtra("setup");
            year = bundle.getString("year");
            month = bundle.getString("month");
            day = bundle.getString("day");
            Calendar datingDate = Calendar.getInstance();
            if (year.length() == 0 || month.length() == 0 || year.length() == 0) {
                if (tvLoveDay.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập ngày bắt đầu yêu", Toast.LENGTH_SHORT).show();
                }
            } else {
                datingDate.set(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day));
                Calendar currentDate = Calendar.getInstance();
                loveTime = getLoveTime(datingDate, currentDate);
                tvLoveDay.setText(loveTime);
                informationGirl.setDate(loveTime);
                informationBoy.setDate(loveTime);
                myDataBase.addInformation(informationGirl);
                myDataBase.addInformation(informationBoy);
            }
        }

        if (requestCode == 2 && resultCode == 2) {
            Bundle bundle = data.getBundleExtra("editBoy");
            boyName = bundle.getString("boyName");
            boyPhone = bundle.getString("boyPhone");
            tvBoyName.setText(boyName);
            tvBoyPhone.setText(boyPhone);
            informationBoy.setName(boyName);
            informationBoy.setPhone(boyPhone);
        }

        if (requestCode == 3 && resultCode == 3) {
            Bundle bundle = data.getBundleExtra("editGirl");
            girlName = bundle.getString("girlName");
            girlPhone = bundle.getString("girlPhone");
            tvGirlName.setText(girlName);
            tvGirlPhone.setText(girlPhone);
            informationGirl.setPhone(girlPhone);
            informationGirl.setName(girlName);
        }
        myDataBase.addInformation(informationBoy);
        myDataBase.addInformation(informationGirl);
    }
}
