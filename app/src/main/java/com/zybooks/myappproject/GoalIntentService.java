package com.zybooks.myappproject;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class GoalIntentService extends IntentService{

    public GoalIntentService() {
        super("GoalIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    private final String CHANNEL_ID_GOAL = "channel_goal";

    private void createGoalNotificationChannel() {
        if (Build.VERSION.SDK_INT < 26) return;

        // Register channel with system
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        //notificationManager.createNotificationChannel(channel);
    }

    private final int NOTIFICATION_ID = 0;

    private void createGoalNotification(String text) {

        // Create notification with various properties
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_GOAL)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        // Get compatibility NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Post notification using ID.  If same ID, this notification replaces previous one
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
