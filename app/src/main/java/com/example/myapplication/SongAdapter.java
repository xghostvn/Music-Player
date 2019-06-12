package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private ArrayList<SongInfo> ListSong;
    public SongItemClickLitener onSongClickListener;



    SongAdapter(ArrayList<SongInfo> listSong){
        this.ListSong = listSong;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { // return layout xml ->  object

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_song,viewGroup,false);

        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongHolder songHolder, final int i) { // set value for view


                songHolder.song_name.setText(ListSong.get(i).SongName);
                songHolder.song_artist.setText(ListSong.get(i).Artist);

                songHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onSongClickListener!=null){

                            onSongClickListener.OnItemClick(v,ListSong.get(i),i);
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return ListSong.size();
    }


    public class SongHolder extends RecyclerView.ViewHolder{ // get view
        TextView song_name;
        TextView song_artist;
        TextView song_albums;
        LinearLayout linearLayout;
       public SongHolder(@NonNull View itemView) {
           super(itemView);
           song_name = itemView.findViewById(R.id.song_name);
            song_albums = itemView.findViewById(R.id.tac_gia);
           song_artist = itemView.findViewById(R.id.song_artist);
           linearLayout = itemView.findViewById(R.id.linearLayout);
       }
   }


   //customs Interface
    public interface SongItemClickLitener{
            void OnItemClick(View view,SongInfo song,int pos);
    }

    public void setOnItemClickListener(SongItemClickLitener onSongItemClickListener){
            this.onSongClickListener = onSongItemClickListener;
    }





}
