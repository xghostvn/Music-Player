package com.example.myapplication.models;

import java.io.Serializable;

public class SongInfo implements Serializable {
    public String SongName;
    public String Artist;
    public String Url;
    public String Albums;
    private int songID;
    private int duration;

    public SongInfo(String name,String artist,String albums,String url){
        SongName = name;
        Artist = artist;
        Url = url;
        Albums = albums;
    }
    public void setID(int ID){
         songID = ID;
    }
    public void setDuration(int duration) {this.duration = duration;}

    public int getID(){
         return songID;
    }
    public int getDuration() { return duration;}
}
