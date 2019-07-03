package com.example.myapplication.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.loader.HandleSong;
import com.example.myapplication.loader.MusicPreferences;
import com.example.myapplication.models.SongInfo;
import com.example.myapplication.utils.NotificationHandler;

import java.io.IOException;


public class ServiceMusic extends Service {
    String TAG = "ServiceMusic : ";

    public SongInfo currentsong;
    public MediaPlayer mediaPlayer;
    private Handler handler;
    private IBinder musicBinder = new MusicBinder();
    private boolean first_time = true;
    public int currentPostion;
    MusicPreferences musicPreferences;
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        handler = new Handler();

        Log.d("abc", "onCreate : service create");
        restoreSong();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

      tobeForeground();


        return START_NOT_STICKY;
    }

    private void tobeForeground(){
        Log.d("abc", "tobeForeground: ");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("MyNotification","MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent prev_action = new Intent(getApplicationContext(), ServiceMusic.class);
        prev_action.setAction("prev_action");
        PendingIntent pprev_action =  PendingIntent.getActivity(getApplicationContext(),0,prev_action,0);

        Intent play_action = new Intent(getApplicationContext(),ServiceMusic.class);
        play_action.setAction("play_action");
        PendingIntent pplay_action = PendingIntent.getActivity(getApplicationContext(),0,play_action,0);

        Intent pause_action = new Intent(getApplicationContext(),ServiceMusic.class);
        pause_action.setAction("pause_action");
        PendingIntent ppause_action = PendingIntent.getActivity(getApplicationContext(),0,pause_action,0);

        Intent next_action = new Intent(getApplicationContext(),ServiceMusic.class);
        next_action.setAction("next_action");
        PendingIntent pnext_action = PendingIntent.getActivity(getApplicationContext(),0,next_action,0);

        Notification builder = new NotificationCompat.Builder(getApplicationContext(),"MyNotification")
                .setSmallIcon(R.drawable.music_icon)
                .setContentText("abc")
                .setContentTitle("def")
                .setContentIntent(ppause_action)
                .addAction(R.drawable.ic_action_prev,"prev",pprev_action).build();




        startForeground(1,builder);
    }

    public boolean isPlaying(){
        if(mediaPlayer!=null) return mediaPlayer.isPlaying();
        return  false;
    }

    public void start(SongInfo songInfo){


            PlayHandler(songInfo);

        currentPostion = 0;
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d("abc", "onPrepared: "+mp.getDuration());
                mp.start();
            }

        });

        tobeForeground();
    }
    public void play_prev(){
        SongInfo songInfo = currentsong;
        int ID = 0;
        if(songInfo!=null){
            ID = songInfo.getID();
            if(ID<=0){
                ID = HandleSong.get(getApplicationContext()).getListSong().size()-1;
            }else {
                ID = ID -1;
            }

        }
        currentsong = HandleSong.get(getApplicationContext()).getListSong().get(ID);
        if(isPlaying()){
            start(currentsong);
        }
    }

    public void play_next(){
        SongInfo songInfo = currentsong;
        int ID = 0;
        if(songInfo!=null){
            ID = songInfo.getID();
            Log.d("abc", "onClick: "+ID);
            if(ID>=HandleSong.get(getApplicationContext()).getListSong().size()-1){
                ID = 0;
            }else {
                Log.d("abc", "onClick: "+ID);
                ID = ID + 1;
                Log.d("abc", "onClick: "+ID);
            }
        }

        currentsong = HandleSong.get(getApplicationContext()).getListSong().get(ID);

        if(isPlaying()){
            start(currentsong);
        }
    }
    public void resume(){
        PlayHandler(currentsong);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.seekTo(currentPostion);
                mp.start();
            }

        });

    }
    public void pause(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            currentPostion = mediaPlayer.getCurrentPosition();
        }

    }


   public void PlayHandler(final SongInfo songInfo){
        handler.post(new Runnable(){
            @Override
            public void run() {
                if(mediaPlayer !=null){
                    mediaPlayer.reset();
                    try {
                        mediaPlayer.setDataSource(songInfo.Url);
                        currentsong = songInfo;
                        mediaPlayer.prepareAsync();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
   }

   public class MusicBinder extends Binder{
        public ServiceMusic getService(){
            return ServiceMusic.this;
        }
   }

    private void restoreSong(){

        Log.d("abc", "restoreSong: ");
        musicPreferences = MusicPreferences.get(this);
        int last_song = musicPreferences.getLastSong();
        if(last_song != 0){
            currentsong = HandleSong.get(this).getListSong().get(last_song-1);
        }else {
            currentsong = HandleSong.get(this).getListSong().get(0);
        }


    }
    private void save_currentSong(int song_ID,long duration){
        MusicPreferences.get(this).setCurrentSongStatus(song_ID,duration);
    }



   public void onPause(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }

   }

   public long getMaxDuration(){
        return mediaPlayer.getDuration();
   }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("abc", "onDestroy: service destroy");
    }
    
}
