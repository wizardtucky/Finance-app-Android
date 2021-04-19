package com.example.myapplication.models;

import java.io.Serializable;

public class Income extends Finance implements Serializable {

    public Income() {
    }
    @Override
    public String toString () {
        return getName() + ": " + getmoney();
    }
}
