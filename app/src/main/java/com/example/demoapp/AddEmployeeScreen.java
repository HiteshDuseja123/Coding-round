package com.example.demoapp;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demoapp.database.DatabaseHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AddEmployeeScreen extends AppCompatActivity {
    EditText name, age, address, city;
    Button addEmplyee;
    List<String> nameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_screen);
        addEmplyee = findViewById(R.id.addEmplyee);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        addEmplyee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() && age.getText().toString().isEmpty() && address.getText().toString().isEmpty() && city.getText().toString().isEmpty()) {
                    Toast.makeText(AddEmployeeScreen.this, "Please Fill All fields", Toast.LENGTH_SHORT).show();
                } else {
                    nameList.add(name.getText().toString());
                    DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                    long id = dbHelper.addEmployee(name.getText().toString(), age.getText().toString(), address.getText().toString(), city.getText().toString());
                    Intent i = new Intent(AddEmployeeScreen.this, HomeScreen.class);
                    startActivity(i);

                }

            }
        });

    }
}