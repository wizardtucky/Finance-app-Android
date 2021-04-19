package com.example.myapplication.models;

import java.io.Serializable;

public class User implements Serializable {
        protected int id;

    public User(int id, String name, String password, int accessLevel) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected String name;
        protected String password;
        protected int accessLevel;

    public User(String name, String password, int accessLevel) {
        this.name = name;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}

