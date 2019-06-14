package com.example.myapplication.loader;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MusicPreferences {
    private static final String LAST_PLAYED_SONG_DURATION = "last_played_song_duration";
    private static final String LAST_PLAYED_SONG = "last_played_song";
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

    public void setCurrentSongStatus(int songID,long timePlayed){

       SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putLong(LAST_PLAYED_SONG_DURATION,timePlayed);
       editor.putInt(LAST_PLAYED_SONG,songID);
       editor.commit();

    }
    public long getLastPlayedCurrent(){
       return sharedPreferences.getLong(LAST_PLAYED_SONG_DURATION,0);
    }
    public int getLastSong(){
       return sharedPreferences.getInt(LAST_PLAYED_SONG,0);
    }


}
