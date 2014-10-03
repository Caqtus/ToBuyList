package com.caqtus.tobuylist;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class AlarmScheduler extends IntentService {


    public static final String TITLE = "title";

    public AlarmScheduler() {

        super(AlarmScheduler.class.getSimpleName());


    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Cursor cursor = getContentResolver()
                .query(MyContentProvider.ITEMS_URI,
                        null, SQLHelper.TIMESTAMP + ">?",
                        new String[]{String.valueOf(System.currentTimeMillis())},
                        SQLHelper.TIMESTAMP + " LIMIT 1"
                );
        System.out.println("Alarming you maybe!!");
        if (cursor.moveToFirst()) {
            long timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(SQLHelper.TIMESTAMP));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(SQLHelper.TITLE));


            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getService(this, 0, new Intent(this,NotifyShower.class).putExtra(TITLE, title), PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent);
            System.out.println("Alarming you!!");

        }
        cursor.close();
    }
}
