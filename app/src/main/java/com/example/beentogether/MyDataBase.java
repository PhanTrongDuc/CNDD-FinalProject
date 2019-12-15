package com.example.beentogether;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Information";

    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "Create table information(name TEXT Primary Key, phone TEXT, date TEXT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addInformation(Information information) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", information.getName());
        values.put("phone", information.getPhone());
        values.put("date",information.getDate());
        db.insert("information", null, values);
    }


    public void editInFormation(Information information, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", information.getName());
        values.put("phone", information.getPhone());
        values.put("date",information.getDate());
        db.update("information", values, "name=?", new String[]{name});
    }


    public ArrayList<Information> getAllContact() {
        ArrayList<Information> informations = new ArrayList<>();
        String script = "Select*from information";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(script, null);
        while (cursor.moveToNext()) {
            Information information = new Information();
            information.setName(cursor.getString(1));
            information.setPhone(cursor.getString(2));
            information.setDate(cursor.getString(3));
            informations.add(information);
        }
        return informations;
    }


    public void deleteInformation(Information information) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("information", "name=?", new String[]{
                String.valueOf(information.getName())
        });
        db.close();
    }
}
