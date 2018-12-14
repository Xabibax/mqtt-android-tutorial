package com.frost.mqtttutorial;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import static java.lang.Integer.parseInt;

public class MyNotificationsManager {

    private Context context;

    private String channel_name;
    private String channel_description;
    private String channel_id;

    private int notification_id;

    private NotificationCompat.Builder notif_Builder;

    private NotificationManager notif_Manager;

    public MyNotificationsManager(Context context) {
        this.context = context;

        channel_name = context.getString(R.string.channel_name);
        channel_description = context.getString(R.string.channel_description);
        channel_id = context.getString(R.string.channel_id);

        notification_id = parseInt(context.getString(R.string.notification_id));

        notif_Manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public MyNotificationsManager setChannel() {
        return setChannel(channel_name, channel_description);
    }

    public MyNotificationsManager setChannel(String channel_name, String channel_description) {
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel mChannel = new NotificationChannel(channel_id, channel_name,importance);

        // Configure the notification channel.
        mChannel.setDescription(channel_description);

        mChannel.enableLights(true);
        // Sets the notification light color for notifications posted to this
        // channel, if the device supports this feature.
        mChannel.setLightColor(Color.RED);

        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        notif_Manager.createNotificationChannel(mChannel);

        return this;
    }

    public MyNotificationsManager setNotification(String message) {

        //Create the intent that’ll fire when the user taps the notification//
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.androidauthority.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        notif_Builder = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText(message)
                .setOngoing(true)
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                .setChannelId(channel_id)
                .setContentIntent(pendingIntent)
        ;

        return this;
    }

    public MyNotificationsManager sendNotification() {
        notif_Manager.notify(notification_id, notif_Builder.build());

        return this;
    }

}
