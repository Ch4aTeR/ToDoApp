package com.example.todoapp.Service;

import static com.example.todoapp.MainActivity.tasks;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.todoapp.Task;

import java.time.LocalDateTime;

public class SendNotification extends Service {
    private static final String CHANNEL_ID = "defaultChannel";
    private static final String CHANNEL_NAME = "Default Channel";

    private NotificationManager notificationManager;

    // This method run only one time. At the first time of service created and running
    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        Log.d("onCreate()", "After service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        for (Task item : tasks.values()) {
            if (item.getTime().equals(LocalDateTime.now())) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("ToDo App")
                        .setContentText("You have to do ur Task: " + item.getName())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                notificationManager.notify(0, builder.build());
                return START_STICKY;
            }
        }
        return flags;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}