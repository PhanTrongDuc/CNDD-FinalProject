package com.example.beentogether;

public class Information {
    private int Id;
    private String name;
    private String phone;
    private String date;

    public Information() {
    }

    public Information(String name, String phone, String date) {
        this.name = name;
        this.phone = phone;
        this.date = date;
    }

    public Information(int id, String name, String phone, String date) {
        Id = id;
        this.name = name;
        this.phone = phone;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
