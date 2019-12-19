package com.example.beentogether;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EvenDB extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "event";

    public EvenDB(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "Create table event(date TEXT primary Key)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", event.toString());
        db.insert("event", null, values);
    }


    public void editEvent(Event event, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", event.toString());
        db.update("event", values, "date=?", new String[]{date});
    }


    public ArrayList<Event> getAllEvent() {
        ArrayList<Event> events = new ArrayList<>();
        String script = "Select*from event";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(script, null);
        while (cursor.moveToNext()) {
            Event event = new Event();
            String string = cursor.getString(0);
            String date = string.substring(0, string.lastIndexOf(": "));
            String[] infOfDate = date.split("-");
            event.setDay(Integer.valueOf(infOfDate[0]));
            event.setMonth(Integer.valueOf(infOfDate[1]));
            event.setYear(Integer.valueOf(infOfDate[2]));
            event.setNote(string.substring(string.lastIndexOf(": ")));
            events.add(event);
        }
        return events;
    }

    public void deleteEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("event", "date=?", new String[]{
                String.valueOf(event)
        });
        db.close();
    }
}
