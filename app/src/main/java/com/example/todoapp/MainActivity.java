package com.example.todoapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.todoapp.Service.AlarmReceiver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static HashMap<String, Task> tasks = new HashMap<>();
    static List<String> keyList = new ArrayList<>(tasks.keySet());
    static ArrayAdapter adapter;
    CRUDView crud = new CRUDView();

    static int selectedTask = 0;

    public static void AddNewTask(String name, String date, String time) {

        if (keyList.contains(name)) {
            keyList.remove(name);
            keyList.add(name);
            tasks.replace(name, new Task(name, date, time));

        } else {
            keyList.add(name);
            tasks.put(name, new Task(name, date, time));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createTaskButton = findViewById(R.id.createTaskButton);
        ListView taskList = findViewById(R.id.taskList);
        LocalDateTime dateNow = LocalDateTime.now();

        AlarmReceiver alarm = new AlarmReceiver();

                alarm.setAlarm(this);

        createTaskButton.setOnClickListener(view -> {
            Intent explicitIntent = new Intent(MainActivity.this, CRUDView.class);
            explicitIntent.putExtra(Intent.EXTRA_TEXT, "Diese Daten empfängt die andere Activity.");
            startActivity(explicitIntent);
        });

        ListView listView = findViewById(R.id.taskList);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, keyList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Task dataObject = tasks.get(keyList.get(position));
                selectedTask = position;

                String key = dataObject.getName();
                String name = tasks.get(key).getName();
                String date = tasks.get(key).getDate();
                String time = tasks.get(key).getTime();

                Intent intent = new Intent(MainActivity.this, CRUDView.class);
                startActivity(intent);

                crud.UpdateTask(name, date, time);


            }
        });


    } public static void removetask(){
        tasks.remove(keyList.get(selectedTask));
        keyList.remove(selectedTask);
    }
}