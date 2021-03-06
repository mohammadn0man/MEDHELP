package com.example.medhelp.ui.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.example.medhelp.ui.alarm.Alarm;


public class AlarmScheduler {

    /**
     * Schedule a reminder alarm at the specified time for the given task.
     *
     * @param context Local application or activity context
     * @param reminderTask Uri referencing the task in the content provider
     */

    public void setAlarm(Context context, long alarmTime, Uri reminderTask) {
        AlarmManager manager = AlarmManagerProvider.getAlarmManager(context);

        Intent intent = new Intent(context, Alarm.class);                                 // alternative
        PendingIntent operation = PendingIntent.getBroadcast(context, 234, intent, 0);


//        PendingIntent operation = ReminderAlarmService.getReminderPendingIntent(context, reminderTask);


        if (Build.VERSION.SDK_INT >= 23) {

            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTime, operation);

        } else if (Build.VERSION.SDK_INT >= 19) {

            manager.setExact(AlarmManager.RTC_WAKEUP, alarmTime, operation);

        } else {

            manager.set(AlarmManager.RTC_WAKEUP, alarmTime, operation);

        }
    }

    public void setRepeatAlarm(Context context, long alarmTime, Uri reminderTask, long RepeatTime) {
        AlarmManager manager = AlarmManagerProvider.getAlarmManager(context);

        Intent intent = new Intent(context, Alarm.class);                                 // alternative
        PendingIntent operation = PendingIntent.getBroadcast(context, 234, intent, 0);


//        PendingIntent operation = ReminderAlarmService.getReminderPendingIntent(context, reminderTask);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime, RepeatTime, operation);


    }

    public void cancelAlarm(Context context, Uri reminderTask) {
        AlarmManager manager = AlarmManagerProvider.getAlarmManager(context);

        PendingIntent operation =
                ReminderAlarmService.getReminderPendingIntent(context, reminderTask);

        manager.cancel(operation);

    }

}