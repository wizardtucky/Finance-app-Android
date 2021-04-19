package com.example.myapplication.models;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Category implements Serializable {
    private int id;
    private List<Category> subCategories;
    private List<Income> incomes;
    private List<Expenses> Expenses;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<com.example.myapplication.models.Expenses> getExpenses() {
        return Expenses;
    }

    public void setExpenses(List<com.example.myapplication.models.Expenses> expenses) {
        Expenses = expenses;
    }

    public Category() {
    }

    public Category(int id, String name, String description, List<Category> subCategories, List<Income> incomes, List<Expenses> Expenses) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subCategories = subCategories;
        this.incomes = incomes;
        this.Expenses = Expenses;
    }

    @Override
    public String toString () {
        return "ID: (" + getId() + ") | " + getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
