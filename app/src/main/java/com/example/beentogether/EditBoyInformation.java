package com.example.beentogether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditBoyInformation extends AppCompatActivity {
    FloatingActionButton done, close, delete;
    EditText edtName, edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_boy_information);
        edtName=findViewById(R.id.edt_name);
        edtPhone=findViewById(R.id.edt_phone);
    }

    public void onClickDone(View view){
        Intent intent= getIntent();
        Bundle bundle= new Bundle();
        bundle.putString("boyName",edtName.getText().toString());
        bundle.putString("boyPhone",edtPhone.getText().toString());
        intent.putExtra("editBoy",bundle);
        setResult(2,intent);
        finish();
    }
    public void onClickClose(View view){
        finish();
    }
}
