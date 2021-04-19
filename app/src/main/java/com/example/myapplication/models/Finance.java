package com.example.myapplication.models;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Finance implements Serializable {

    private int id;
    private String name;
    private double money;
    private Date dateCreated;

    public Finance(int id, double money, String name) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getmoney() {
        return money;
    }

    public void setmoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Finance() {
    }
    @Override
    public String toString() {
        return id + " " + name + " " + money;
    }

}
