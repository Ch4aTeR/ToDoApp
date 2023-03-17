package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.Service.AlarmReceiver;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static HashMap<String, Task> tasks = new HashMap<>();
    static List<String> keyList = new ArrayList<>(tasks.keySet());
    static ArrayAdapter adapter;
    static int selectedTask = 0;
    CRUDView crud = new CRUDView();

    private static String serializeTaskToJson(Task task) {
        Gson gson = new Gson();
        return gson.toJson(task);
    }

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

        String json = serializeTaskToJson(new Task(name, date, time));
        File file = new File("D:\\Documents\\ToDoApp\\app\\src\\main\\res\\Files", "D:\\Documents\\ToDoApp\\app\\src\\main\\res\\Files\\tasks.json");
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(json + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void removeTask() {
        tasks.remove(keyList.get(selectedTask));
        keyList.remove(selectedTask);
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
            startActivity(explicitIntent);
        });

        ListView listView = findViewById(R.id.taskList);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, keyList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent explicitIntent = new Intent(MainActivity.this, CRUDView.class);
                startActivity(explicitIntent);
                Task dataObject = tasks.get(keyList.get(position));
                selectedTask = position;

                String key = dataObject.getName();
                String name = tasks.get(key).getName();
                String date = tasks.get(key).getDate();
                String time = tasks.get(key).getTime();

                crud.UpdateTask(name, date, time);
            }
        });
    }
}