package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MusicPreferences {
    private static final String LAST_PLAYED_SONG_DURATION = "last_played_song_duration";
    private SharedPreferences sharedPreferences;
    private static MusicPreferences sMusicPreferences;
   private MusicPreferences (Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static MusicPreferences get(Context context){
       if(sMusicPreferences == null){
           sMusicPreferences = new MusicPreferences(context);
       }
       return  sMusicPreferences;
    }

    public void setCurrentSongStatus(long timePlayed){

       SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putLong(LAST_PLAYED_SONG_DURATION,timePlayed);
       editor.commit();

    }
    public long getLastPlayedCurren(){
       return sharedPreferences.getLong(LAST_PLAYED_SONG_DURATION,0);
    }


}
