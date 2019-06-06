package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;



public class ServiceMusic extends Service {
    String TAG = "ServiceMusic : ";
    public SongInfo currentsong;
    private MediaPlayer mediaPlayer;
    private Handler handler;
    private IBinder musicBinder = new MusicBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        handler = new Handler();
        Log.d("abc", "onCreate : mediaPlayer + Hanlder");


    }

    public boolean isPlaying(){
        if(mediaPlayer!=null) return mediaPlayer.isPlaying();
        return  false;
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
    public void pause(){
        if(mediaPlayer!=null) mediaPlayer.pause();

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





   public void onPause(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }

   }







}
