package com.example.myapplication;

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


public class Fragment_SongList extends Fragment {
    private RecyclerView recyclerView;
    private HandleSong handleSong;
    private SongAdapter songAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.f_songlist,container,false);
        handleSong = new HandleSong(getContext());
        handleSong.LoadSongs();
        Log.d("Loadsongs", "onCreateView: "+handleSong.getListSong().size());
        recyclerView = rootview.findViewById(R.id.song_list);
        songAdapter = new SongAdapter(handleSong.getListSong());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(songAdapter);


        return rootview;
    }
}
