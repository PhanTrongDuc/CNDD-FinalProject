package com.example.beentogether;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event implements Serializable {
    private String note;
    private int day, month, year;

    public Event() {
    }

    public Event(String note, int day, int month, int year) {
        this.note = note;
        this.day = day;
        this.month = month;
        this.year = year;
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
        return day + "-" + month + "-" + year + ": " + note;
    }
}

