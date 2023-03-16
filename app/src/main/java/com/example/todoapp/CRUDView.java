package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class CRUDView extends AppCompatActivity {

    static String name;
    static String date;
    static String time;

    static TextInputEditText nameInput;
    static EditText dateInput;
    static EditText timeInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudview);
        nameInput = findViewById(R.id.nameInput);
         dateInput = findViewById(R.id.dateInput);
         timeInput = findViewById(R.id.timeInput);





        Button save = findViewById(R.id.saveTask);
        Button delete = findViewById(R.id.deleteTask);

        save.setOnClickListener(view -> {
            CreateNewTask();
        });

        delete.setOnClickListener(view -> {
            DeleteTask();
        });

    }

    public void CreateNewTask(){
        name = nameInput.getText().toString();
        date = dateInput.getText().toString();
        time = timeInput.getText().toString();
        MainActivity.AddNewTask(name, date, time);

        Intent explicitIntent = new Intent(CRUDView.this, MainActivity.class);
        explicitIntent.putExtra(Intent.EXTRA_TEXT, "Diese Daten empfängt die andere Activity.");
        startActivity(explicitIntent);
    }

    public void DeleteTask(){

        Intent explicitIntent = new Intent(CRUDView.this, MainActivity.class);
        explicitIntent.putExtra(Intent.EXTRA_TEXT, "Diese Daten empfängt die andere Activity.");
        startActivity(explicitIntent);
    }
    public void UpdateTask() {

        Intent explicitIntent = new Intent(CRUDView.this, MainActivity.class);
        explicitIntent.putExtra(Intent.EXTRA_TEXT, "Diese Daten empfängt die andere Activity.");
        startActivity(explicitIntent);
    }
}