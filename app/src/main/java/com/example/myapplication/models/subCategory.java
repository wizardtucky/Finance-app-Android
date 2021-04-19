package com.example.myapplication.models;

import java.util.List;

public class subCategory extends Category {

    protected Category parentCategory;

    public subCategory() { }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
