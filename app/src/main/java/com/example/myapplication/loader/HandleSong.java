package com.example.myapplication.loader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.myapplication.models.SongInfo;

import java.util.ArrayList;

public class HandleSong {


    private ArrayList<SongInfo> ListSong = new ArrayList<>();
    private static HandleSong handleSong;
   public HandleSong(Context context){

        LoadSongs(context);

    }


    public static HandleSong get(Context context){
        if(handleSong == null){
            handleSong = new HandleSong(context);
        }
        return handleSong;
    }

    private Cursor querySong(Context context){

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            cursor = context.getContentResolver().query(uri,null,null,null);
        }
        return  cursor;

    }

    private void LoadSongs(Context context){

        Cursor cursor = querySong(context);
        Log.d("abc", "LoadSongs: start");
        try{
            cursor.moveToFirst();
            do {



                String songname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String songArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String songAlums = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String songUrl = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                int Duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                SongInfo song = new SongInfo(songname,songArtist,"",songUrl);
                song.setID(ListSong.size());
                song.setDuration(Duration);

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
