package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class HandleSong {

    private Context context;
    private ArrayList<SongInfo> ListSong = new ArrayList<>();
    HandleSong(Context context){
        this.context = context;

    }

    private Cursor querySong(){

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            cursor = context.getContentResolver().query(uri,null,null,null);
        }
        return  cursor;

    }

    public void LoadSongs(){

        Cursor cursor = querySong();
        Log.d("abc", "LoadSongs: start");
        try{
            cursor.moveToFirst();
            do {



                String songname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String songArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String songAlums = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));

                SongInfo song = new SongInfo(songname,songArtist,"");
                Log.d("abc", "LoadSongs: " + songAlums);
                ListSong.add(song);
            }while (cursor.moveToNext());




        }finally {
            cursor.close();
        }
        Log.d("abc", "LoadSongs: end");

    }

    public ArrayList<SongInfo> getListSong(){
        return ListSong;
    }






}
