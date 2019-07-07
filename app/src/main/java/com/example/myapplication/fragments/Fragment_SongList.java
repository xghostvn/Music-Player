package com.example.myapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.myapplication.loader.HandleSong;
import com.example.myapplication.R;

import com.example.myapplication.service.ServiceMusic;
import com.example.myapplication.adapters.SongAdapter;
import com.example.myapplication.models.SongInfo;




public class Fragment_SongList extends MusicServiceFragment {
    private RecyclerView recyclerView;
    private LinearLayout mini_player_panel;
    private SongAdapter songAdapter;
    private ServiceMusic serviceMusic;
    private ImageView mini_btn_play;
    private TextView mini_play_name;
    private TextView mini_play_artist;
    private ImageView mini_btn_next;
    private ImageView mini_btn_prev;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.f_songlist,container,false);

        mini_btn_play = rootview.findViewById(R.id.mini_play_play);
        mini_play_name = rootview.findViewById(R.id.mini_play_name);
        mini_play_artist = rootview.findViewById(R.id.mini_play_artist);
        mini_btn_next = rootview.findViewById(R.id.mini_play_next);
        mini_btn_prev = rootview.findViewById(R.id.mini_play_prev);
         HandleView();
        Log.d("abc", "onCreateView: fragment_songlist");
       //  serviceMusic = new ServiceMusic() and start service with intent  is different object
        mini_player_panel = rootview.findViewById(R.id.mini_play_panel);

        recyclerView = rootview.findViewById(R.id.song_list);
        Log.d("abc", "onCreateView: LoadSongs");
        songAdapter = new SongAdapter(HandleSong.get(getContext()).getListSong());
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
                    serviceMusic.start(song);
                 mini_btn_play.setImageResource(R.drawable.ic_media_pause);
                 mini_play_name.setText(song.SongName);
                 mini_play_artist.setText(song.Artist);

            }
        });

        mini_player_panel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SwitchFragment(serviceMusic.currentsong);

            }
        });
        mini_btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceMusic.isPlaying()){
                    mini_btn_play.setImageResource(R.drawable.ic_media_play);
                    serviceMusic.pause();
                }else {
                    mini_btn_play.setImageResource(R.drawable.ic_media_pause);
                    serviceMusic.resume();
                }
            }
        });

        mini_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceMusic.play_next();
                SongInfo songInfo = serviceMusic.currentsong;
                mini_play_name.setText(songInfo.SongName);
                mini_play_artist.setText(songInfo.Artist);
            }
        });

        mini_btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                serviceMusic.play_prev();
                SongInfo songInfo = serviceMusic.currentsong;
                mini_play_name.setText(songInfo.SongName);
                mini_play_artist.setText(songInfo.Artist);


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("abc", "onActivityCreated: fragment_songlist");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("abc", "onStart: FragmentSongList start Service");
    }



    private void SwitchFragment(SongInfo songInfo){
        Bundle SongInfo = new Bundle();
        SongInfo.putString("song_name",serviceMusic.currentsong.SongName);
        SongInfo.putString("song_artist",serviceMusic.currentsong.Artist);
        SongInfo.putInt("song_duration",songInfo.getDuration());
        if(serviceMusic.isPlaying()){
            SongInfo.putBoolean("type",true);
        }else {
            SongInfo.putBoolean("type",false);
        }

        Fragment_PlayerPanel fragment_playerPanel = new Fragment_PlayerPanel();

        fragment_playerPanel.setArguments(SongInfo);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment_playerPanel);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void HandleView(){
        Bundle bundle = getArguments();
        if(bundle!=null){
            Log.d("abc", "HandleView: not null");
            if(bundle.getBoolean("type")){
                mini_btn_play.setImageResource(R.drawable.ic_media_pause);
            }
            else {
                mini_btn_play.setImageResource(R.drawable.ic_media_play);
            }

            mini_play_name.setText(bundle.getString("song_name"));
            mini_play_artist.setText(bundle.getString("song_artist"));

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("abc", "onDestroy: destroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("abc", "onPause: pause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("abc", "onResume: ");
    }
}
