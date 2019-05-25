package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

public class HandleSong {

    private Context context;
    private ArrayList<SongInfo> ListSong;
    HandleSong(Context context,ArrayList listSong){
        this.context = context;
        this.ListSong = listSong;
    }

    public Cursor querySong(){

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(uri,null,null,null);
        return  cursor;

    }

    private void LoadSongs(){

        Cursor cursor = querySong();

        try{
            do {
                cursor.moveToFirst();


                String songname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String songArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String songAlums = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));

                SongInfo song = new SongInfo(songname,songArtist,songAlums);
                
                ListSong.add(song);
            }while (cursor.moveToNext());




        }finally {
            cursor.close();
        }


    }






}
