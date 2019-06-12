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
import java.util.ArrayList;


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

        Log.d("abc", "onCreate : mediaPlayer + Hanlder");
        restoreSong();

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







}
