package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private ArrayList<SongInfo> ListSong = new ArrayList<>();

    public void AddSong(SongInfo song){
        ListSong.add(song);
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { // return layout xml ->  object

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_song,viewGroup,false);

        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder songHolder, int i) { // set value for view

    }

    @Override
    public int getItemCount() {
        return ListSong.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder{ // get view
        TextView song_name;
        TextView song_artist;

       public SongHolder(@NonNull View itemView) {
           super(itemView);
           song_name = itemView.findViewById(R.id.song_name);
           song_artist = itemView.findViewById(R.id.song_artist);
       }
   }
}