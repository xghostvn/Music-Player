package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.zip.Inflater;


public class Fragment_SongList extends Fragment {
    private RecyclerView recyclerView;
    private HandleSong handleSong;
    private SongAdapter songAdapter;
    private ServiceMusic serviceMusic;
    private View player_panel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.f_songlist,container,false);
        player_panel = inflater.inflate(R.layout.panel_player,container,false);
        Intent intent = new Intent(getContext(),ServiceMusic.class);
        getActivity().startService(intent);
        serviceMusic = new ServiceMusic();
        handleSong = new HandleSong(getContext());
        handleSong.LoadSongs();
        Log.d("Loadsongs", "onCreateView: "+handleSong.getListSong().size());
        recyclerView = rootview.findViewById(R.id.song_list);
        songAdapter = new SongAdapter(handleSong.getListSong());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(songAdapter);
        HandlePlaySongClick();

        return rootview;
    }


   public void HandlePlaySongClick(){
        songAdapter.setOnItemClickListener(new SongAdapter.SongItemClickLitener() {
            @Override
            public void OnItemClick(View view, SongInfo song, int pos) {

                final TextView song_name = player_panel.findViewById(R.id.ten_BH);
                TextView song_artist = player_panel.findViewById(R.id.tac_gia);
                Log.d("abc", "OnItemClick: "+song_name.getText());
                song_name.setText(song.SongName);
                song_artist.setText(song.Artist);



                Log.d("abc", "OnItemClick: "+song_name.getText());

                ((MainActivity)getActivity()).abc(song_name.getText().toString(),song_artist.getText().toString());

                try {
                    serviceMusic.start(song);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
