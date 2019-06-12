package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.zip.Inflater;


public class Fragment_SongList extends MusicServiceFragment {
    private RecyclerView recyclerView;
    private SongPlayerFragment songPlayerFragment;
    private SongAdapter songAdapter;
    private ServiceMusic serviceMusic;
    private View player_panel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.f_songlist,container,false);
        player_panel = inflater.inflate(R.layout.panel_player,container,false);
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

             Bundle bundle = new Bundle();
             bundle.putString("song_name",song.SongName);
             bundle.putString("song_artist",song.Artist);
             bundle.putString("song_albums",song.Albums);
             bundle.putString("song_url",song.Url);
                Log.d("abc", "OnItemClick: "+song.getDuration());
                bundle.putInt("song_duration",song.getDuration());




                Intent intent = new Intent(getContext(),SecondActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);



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
}
