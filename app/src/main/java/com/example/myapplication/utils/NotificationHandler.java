package com.example.myapplication.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.service.ServiceMusic;

import static com.example.myapplication.activity.App.CHANNEL_ID;

public class NotificationHandler {
    public static Notification createNotification(Context context,boolean Isplaying){


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("MyNotification","MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent prev_action = new Intent(context, ServiceMusic.class);
        prev_action.setAction("prev_action");
        PendingIntent pprev_action =  PendingIntent.getActivity(context,0,prev_action,0);

        Intent play_action = new Intent(context,ServiceMusic.class);
        play_action.setAction("play_action");
        PendingIntent pplay_action = PendingIntent.getActivity(context,0,play_action,0);

        Intent pause_action = new Intent(context,ServiceMusic.class);
        pause_action.setAction("pause_action");
        PendingIntent ppause_action = PendingIntent.getActivity(context,0,pause_action,0);

        Intent next_action = new Intent(context,ServiceMusic.class);
        next_action.setAction("next_action");
        PendingIntent pnext_action = PendingIntent.getActivity(context,0,next_action,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.music_icon)
                .addAction(R.drawable.ic_action_prev,"prev",pprev_action);

        if(Isplaying){
            builder.addAction(R.drawable.ic_media_pause,"pause",pplay_action);
        }else {
            builder.addAction(R.drawable.ic_media_play,"play",ppause_action);
        }

        builder.addAction(R.drawable.ic_action_next,"next",pnext_action);


        return builder.build();



    }
}
