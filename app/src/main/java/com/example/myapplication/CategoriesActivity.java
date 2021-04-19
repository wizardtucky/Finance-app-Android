package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.REST.RESTControl;
import com.example.myapplication.models.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        GetCategories getCategories = new GetCategories();
        getCategories.execute("Fake Data");
    }
    public void goToExpenses(View v) {
        Intent loginWindow = new Intent(CategoriesActivity.this, ExpenseActivity.class);
        startActivity(loginWindow);
    }
    public void goToUsers(View v) {
        Intent loginWindow = new Intent(CategoriesActivity.this, UserActivity.class);
        startActivity(loginWindow);
    }

    public void logOut(View view) {
        Intent loginWindow = new Intent(CategoriesActivity.this, LoginActivity.class);
        startActivity(loginWindow);
    }

    private final class GetCategories extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = Constants.address + "/category/allcategories";
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
                    Type listType = new TypeToken<ArrayList<Category>>() {
                    }.getType();
                    final List<Category> categoryData = new Gson().fromJson(result, listType);
                    ListView listView = findViewById(R.id.categoryList);
                    ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<>(CategoriesActivity.this, android.R.layout.simple_list_item_1, categoryData);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent window = new Intent(CategoriesActivity.this, ExpenseActivity.class);
//                            window.putExtra("Category", categoryData.get(position)); // "categoty" yra id. categoryData tai data
//                            Toast info = Toast.makeText(CategoriesActivity.this, "Selected company: " + categoryData.get(position).toString(), Toast.LENGTH_LONG);
//                            info.show();
                            startActivity(window);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CategoriesActivity.this, "Wrong data", Toast.LENGTH_LONG);
                }
            } else {
                Toast.makeText(CategoriesActivity.this, "Wrong data", Toast.LENGTH_LONG);
            }
        }
    }
}