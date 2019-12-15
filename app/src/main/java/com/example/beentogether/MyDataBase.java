package com.example.beentogether;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDataBase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "information";

    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "Create table information(id INTEGER Primary Key,name TEXT , phone TEXT, date TEXT)";
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
        values.put("date", information.getDate());
        db.insert("information", null, values);
        db.close();
    }


    public void editInFormation(Information information, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", information.getName());
        values.put("phone", information.getPhone());
        values.put("date", information.getDate());
        db.update("information", values, "name=?", new String[]{name});
    }


    public List<Information> getAllInformation() {
        List<Information> informations = new ArrayList<>();
        String script = "Select*from information";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(script, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Information information = new Information(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            informations.add(information);
            cursor.moveToNext();
        }
        return informations;
    }


    public void deleteInformation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("information", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Information getInformation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("information", null, "id" + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Information information = new Information(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return information;
    }
}
