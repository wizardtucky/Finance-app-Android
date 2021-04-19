package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.widget.*;
import com.example.myapplication.models.*;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import com.example.myapplication.models.*;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import com.example.myapplication.REST.RESTControl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class UserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        TextView textView = findViewById(R.id.activeUser);
        User user = (User) getIntent().getSerializableExtra("user");
        textView.setText("Logged in as: "+user.getName());
        GetUsers getUsers = new GetUsers();
        getUsers.execute("Fake Data");
    }

    public void goToCategories(View v) {
        Intent newWindow = new Intent(UserActivity.this, CategoriesActivity.class);
        startActivity(newWindow);
    }

    public void logOut(View view) {
        Intent newWindow = new Intent(UserActivity.this, LoginActivity.class);
        startActivity(newWindow);
    }

    private final class GetUsers extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = Constants.address + "/users/allusers";
            try {
                return RESTControl.sendGet(url);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("RECEIVED: " + result);
            if (result != null) {
                try {
                    Type listType = new TypeToken<ArrayList<User>>() {
                    }.getType();
                    final List<User> userData = new Gson().fromJson(result, listType);
                    ListView listView = findViewById(R.id.userList);
                    List<String> userStringList = new ArrayList<>();
                    for (User user : userData) {
                        userStringList.add(user.getName() + " | " + user.getAccessLevel());
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, userStringList);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast info = Toast.makeText(UserActivity.this, "Selected company: " + userData.get(position).toString(), Toast.LENGTH_LONG);
                            info.show();
                            Intent loginWindow = new Intent(UserActivity.this, UserActivity.class);
                            startActivity(loginWindow);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(UserActivity.this, "Wrong data", Toast.LENGTH_LONG);
                }
            } else {
                Toast.makeText(UserActivity.this, "Wrong data", Toast.LENGTH_LONG);
            }
        }
    }
}