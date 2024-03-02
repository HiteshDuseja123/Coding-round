package com.example.demoapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.demoapp.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "employees.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_EMPLOYEES = "employees";

    // Columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CITY = "city";

    // Create table SQL query
    private static final String CREATE_TABLE_EMPLOYEES = "CREATE TABLE " + TABLE_EMPLOYEES +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," +
            COLUMN_AGE + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_CITY + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EMPLOYEES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);

        // Create tables again
        onCreate(db);
    }
    public long addEmployee(String name, String age, String address, String city) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_CITY, city);

        // Inserting Row
        long id = db.insert(TABLE_EMPLOYEES, null, values);
        // Closing database connection
        db.close();
        return id;
    }
    @SuppressLint("Range")
    public List<EmployeeModel> getAllEmployees() {
        List<EmployeeModel> employeeList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_EMPLOYEES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EmployeeModel employee = new EmployeeModel();
                employee.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                employee.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
                employee.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                employee.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)));
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        db.close();
        return employeeList;
    }
}
