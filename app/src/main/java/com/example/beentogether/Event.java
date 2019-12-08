package com.example.beentogether;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {
    private String note;
    private int day, month, year;

    public Event() {
    }

    public Event(String note,int day, int month, int year) {
        note = this.note;
        day=this.day;
        month=this.month;
        year=this.year;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @NonNull
    @Override
    public String toString() {
        return day+"-"+month+"-"+year+": "+ note;
    }
}

