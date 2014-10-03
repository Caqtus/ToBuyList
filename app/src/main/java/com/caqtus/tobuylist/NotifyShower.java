package com.caqtus.tobuylist;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class NotifyShower extends IntentService {


    public NotifyShower() {
        super(NotifyShower.class.getSimpleName());

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String title = intent.getStringExtra(AlarmScheduler.TITLE);
        Notification.Builder builder = new Notification.Builder(this);
        Intent activityIntent = new Intent(this, MyActivity.class);
        Notification notification = builder
                .setContentTitle("New message")
                .setContentText(title)
                .setTicker(title)
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true)
                .setLights(0x00ff0000, 500, 500)
                .setContentIntent(PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT))
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);



    }
}
