package com.example.myapplication.models;

import java.util.ArrayList;

public class AlbumsInfo {

    private String albums_name;
    private String albums_artist;
    public ArrayList<SongInfo> songInfos;

   public AlbumsInfo(){
        songInfos = new ArrayList<>();
    }


    public String getAlbums_name() {
        return albums_name;
    }

    public void setAlbums_name(String albums_name) {
        this.albums_name = albums_name;
    }

    public String getAlbums_artist() {
        return albums_artist;
    }

    public void setAlbums_artist(String albums_artist) {
        this.albums_artist = albums_artist;
    }






}
