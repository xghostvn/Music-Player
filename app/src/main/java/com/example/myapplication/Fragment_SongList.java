package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;


public class Fragment_SongList extends Fragment {
    private RecyclerView recyclerView;
    private HandleSong handleSong;
    private SongAdapter songAdapter;
    private ServiceMusic serviceMusic;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.f_songlist,container,false);
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


   private void HandlePlaySongClick(){
        songAdapter.setOnItemClickListener(new SongAdapter.SongItemClickLitener() {
            @Override
            public void OnItemClick(View view, SongInfo song, int pos) {
                Log.d("abc", "OnItemClick: click");

                try {
                    serviceMusic.start(song);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
