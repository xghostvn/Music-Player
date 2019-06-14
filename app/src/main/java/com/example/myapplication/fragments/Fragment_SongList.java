package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myapplication.loader.HandleSong;
import com.example.myapplication.R;
import com.example.myapplication.activity.SecondActivity;
import com.example.myapplication.service.ServiceMusic;
import com.example.myapplication.adapters.SongAdapter;
import com.example.myapplication.models.SongInfo;


public class Fragment_SongList extends MusicServiceFragment {
    private RecyclerView recyclerView;
    private SongPlayerFragment songPlayerFragment;
    private SongAdapter songAdapter;
    private ServiceMusic serviceMusic;
    private FloatingActionButton floatingActionButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.f_songlist,container,false);
        floatingActionButton = getActivity().findViewById(R.id.switch_panelplay);
       //  serviceMusic = new ServiceMusic() and start service with intent  is different object

        recyclerView = rootview.findViewById(R.id.song_list);
        Log.d("abc", "onCreateView: LoadSongs");
        songAdapter = new SongAdapter(HandleSong.get(getContext()).getListSong());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(songAdapter);
        songPlayerFragment = new SongPlayerFragment();
        HandlePlaySongClick();

        return rootview;
    }


   public void HandlePlaySongClick(){
        songAdapter.setOnItemClickListener(new SongAdapter.SongItemClickLitener() {
            @Override
            public void OnItemClick(View view, SongInfo song, int pos) {
                    serviceMusic.start(song);
                    SwitchPanel(song,true);

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchPanel(serviceMusic.currentsong,false);
            }
        });




    }


    @Override
    public void onServiceConnected(ServiceMusic serviceMusic) {
        this.serviceMusic = serviceMusic;
    }

    @Override
    public void onServiceDisconnected() {

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("abc", "onStart: FragmentSongList start Service");
    }

    private void SwitchPanel(SongInfo songInfo,Boolean type){

        if(songInfo!=null){
            Bundle bundle = new Bundle();
            bundle.putString("song_name",songInfo.SongName);
            bundle.putString("song_artist",songInfo.Artist);
            bundle.putInt("song_duration",songInfo.getDuration());
            bundle.putBoolean("Type",type);
            Intent intent = new Intent(getContext(), SecondActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }


    }
}
