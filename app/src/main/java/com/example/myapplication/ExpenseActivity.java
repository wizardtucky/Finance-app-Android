package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.REST.RESTControl;
import com.example.myapplication.models.Expenses;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExpenseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_expense);
        GetExpenses getExpenses = new GetExpenses();
        getExpenses.execute("Fake Data");
    }


    public void goToIncome(View v) {
        Intent loginWindow = new Intent(ExpenseActivity.this, IncomeActivity.class);
        startActivity(loginWindow);
    }
    public void goToCategories(View v) {
        Intent loginWindow = new Intent(ExpenseActivity.this, CategoriesActivity.class);
        startActivity(loginWindow);
    }

    public void logOut(View view) {
        Intent loginWindow = new Intent(ExpenseActivity.this, LoginActivity.class);
        startActivity(loginWindow);
    }

    private final class GetExpenses extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = Constants.address + "/expenses/all";
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
                    Type listType = new TypeToken<ArrayList<Expenses>>() {
                    }.getType();
                    final List<Expenses> incomeData = new Gson().fromJson(result, listType);
                    ListView listView = findViewById(R.id.expenseList);
                    ArrayAdapter<Expenses> arrayAdapter = new ArrayAdapter<>(ExpenseActivity.this, android.R.layout.simple_list_item_1, incomeData);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent window = new Intent(ExpenseActivity.this, ExpenseActivity.class);
                            window.putExtra("Expenses", incomeData.get(position)); // "incomeData" yra id. categoryData tai data
                            Toast info = Toast.makeText(ExpenseActivity.this, "Selected company: " + incomeData.get(position).toString(), Toast.LENGTH_LONG);
                            info.show();
                            startActivity(window);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ExpenseActivity.this, "Wrong data", Toast.LENGTH_LONG);
                }
            } else {
                Toast.makeText(ExpenseActivity.this, "Wrong data", Toast.LENGTH_LONG);
            }
        }
    }
}
