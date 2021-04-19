package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.REST.RESTControl;
import com.example.myapplication.models.Category;
import com.example.myapplication.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginToServer(View v) {
        EditText login = findViewById(R.id.usernameField);
        EditText psw = findViewById(R.id.passwordField);
        String dataToSend = "{\"username\":\"" + login.getText().toString() + "\", \"password\":\"" + psw.getText().toString() + "\"}";
        System.out.println("DATA TO SEND                          THIS ::    "+dataToSend);

        GetLibraries userLogin = new GetLibraries();
        userLogin.execute(dataToSend);
    }


    private final class GetLibraries extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = Constants.address + "/users/login";
            String postDataParameters = params[0];
            System.out.println("SENT: " + postDataParameters);
            try {
                return RESTControl.sendPost(url, postDataParameters);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("RECEIVED: " + result);
            if (result != null) {
                Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                Type listType = new TypeToken<User>() {
                }.getType();
                final User userData = new Gson().fromJson(result, listType);
                intent.putExtra("user", userData);
                startActivity(intent);
                System.out.println("successful login");
            }
            Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_LONG).show();
        }
    }
}