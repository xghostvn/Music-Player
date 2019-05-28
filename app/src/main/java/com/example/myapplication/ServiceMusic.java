package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;



public class ServiceMusic extends Service {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Handler handler = new Handler();
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void start(SongInfo songInfo) throws IOException {
        Log.d("abc", "start: "+songInfo.SongName);
        PlayHandler(songInfo);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
   public void PlayHandler(final SongInfo songInfo){
        handler.post(new Runnable(){

            @Override
            public void run() {
                if(mediaPlayer !=null){
                    mediaPlayer.reset();
                    try {
                        mediaPlayer.setDataSource(songInfo.Url);
                        mediaPlayer.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
   }







}
