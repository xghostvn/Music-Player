package com.example.myapplication.loader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.myapplication.models.AlbumsInfo;
import com.example.myapplication.models.SongInfo;

import java.util.ArrayList;

public class HandleSong {


    private ArrayList<SongInfo> ListSong = new ArrayList<>();
    private ArrayList<AlbumsInfo> ListAlbums = new ArrayList<>();
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

        try{
            cursor.moveToFirst();
            do {



                String songname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String songArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String songAlbums = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String songUrl = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                int Duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                SongInfo song = new SongInfo(songname,songArtist,songAlbums,songUrl);

                song.setID(ListSong.size());
                song.setDuration(Duration);






                ListSong.add(song);
            }while (cursor.moveToNext());


            AlbumsSort();

            Log.d("abc", "LoadSongs: "+ListAlbums.size());
            for(AlbumsInfo albumsInfo : ListAlbums){
                Log.d("abc", "LoadSongs: "+albumsInfo.getAlbums_name());
                Log.d("abc", "LoadSongs: "+albumsInfo.getAlbums_artist());
                Log.d("abc", "LoadSongs: "+albumsInfo.songInfos.size());
            }

        }finally {
            cursor.close();
        }


    }

    public ArrayList<SongInfo> getListSong(){
        return ListSong;
    }


    public void AlbumsSort(){


      for(SongInfo songInfo : ListSong){

          if(ListAlbums.size()==0){
              AlbumsInfo albumsInfo = new AlbumsInfo();
              albumsInfo.setAlbums_name(songInfo.Albums);
              albumsInfo.setAlbums_artist(songInfo.Artist);
              albumsInfo.songInfos.add(songInfo);
              ListAlbums.add(albumsInfo);
          }else {
                CheckAlbumsExist(songInfo);
          }

      }
    

    }
    private void CheckAlbumsExist(SongInfo songInfo){

     for(AlbumsInfo albumsInfo : ListAlbums){
            if(albumsInfo.getAlbums_name().equals(songInfo.Albums)){
                albumsInfo.songInfos.add(songInfo);
                return;
            }
     }

       AlbumsInfo new_Albums = new AlbumsInfo();
        new_Albums.setAlbums_name(songInfo.Albums);
        new_Albums.setAlbums_artist(songInfo.Artist);
        new_Albums.songInfos.add(songInfo);

        ListAlbums.add(new_Albums);

    }
    public ArrayList<AlbumsInfo> getAlbumsList(){
       return ListAlbums;
    }












}
