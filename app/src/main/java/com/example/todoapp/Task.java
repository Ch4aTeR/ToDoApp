package com.example.todoapp;

import java.time.LocalDateTime;
import java.util.Date;

public class Task {
    String Name;
    String date;
    String time;


    public Task(String name, String date, String time) {
        Name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
