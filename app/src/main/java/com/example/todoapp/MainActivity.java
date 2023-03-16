package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static HashMap<String, Task> tasks = new HashMap<>();

    static List<String> keyList = new ArrayList<>(tasks.keySet());

    static ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createTaskButton = findViewById(R.id.createTaskButton);
        ListView taskList = findViewById(R.id.taskList);



        createTaskButton.setOnClickListener(view -> {
            Intent explicitIntent = new Intent(MainActivity.this, CRUDView.class);
            explicitIntent.putExtra(Intent.EXTRA_TEXT, "Diese Daten empfängt die andere Activity.");
            startActivity(explicitIntent);
        });

        ListView listView = findViewById(R.id.taskList);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, keyList);
        listView.setAdapter(adapter);



    }
    public static void AddNewTask(String name, String date, String time){
        tasks.put(name, new Task(name, date, time));
        keyList.add(name);
        adapter.notifyDataSetChanged();

    }
}