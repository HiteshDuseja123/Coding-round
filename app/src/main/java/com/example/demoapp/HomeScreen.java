package com.example.demoapp;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.database.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    Button addEmployee;
    TextView emplyeeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        addEmployee = findViewById(R.id.addEmployee);
        emplyeeText = findViewById(R.id.emplyeeText);
        DatabaseHelper dbHelper = new DatabaseHelper(HomeScreen.this);

        List<EmployeeModel> employees = dbHelper.getAllEmployees();

        StringBuilder stringBuilder = new StringBuilder();
        for (EmployeeModel employee : employees) {
            stringBuilder.append("Name: ").append(employee.getName()).append(", Age: ").append(employee.getAge()).append("\n").append(", Address: ").append(employee.getAddress()).append(", City: ").append(employee.getCity()).append("\n");
        }

        emplyeeText.setText(stringBuilder.toString());
        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, AddEmployeeScreen.class);
                startActivity(i);

            }
        });
    }
}