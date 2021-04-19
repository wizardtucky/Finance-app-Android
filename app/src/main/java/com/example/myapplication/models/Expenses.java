package com.example.myapplication.models;

import java.io.Serializable;
import java.util.Date;

public class Expenses extends Finance implements Serializable {

    public Expenses() {
    }
    @Override
    public String toString () {
        return getName() + ": " + (-getmoney());
    }
}
