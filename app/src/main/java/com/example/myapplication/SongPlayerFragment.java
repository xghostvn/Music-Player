package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;

public class SongPlayerFragment extends MusicServiceFragment {

        private ImageView btn_play;
        private ImageView btn_next;
        private ImageView btn_prev;
        private ServiceMusic serviceMusic;



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.panel_player,container,false);

        btn_play = rootView.findViewById(R.id.btn_play);
        btn_next = rootView.findViewById(R.id.btn_next);
        btn_prev = rootView.findViewById(R.id.btn_prev);

        HandleAllAction();
        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onServiceConnected(ServiceMusic serviceMusic) {
        this.serviceMusic = serviceMusic;
    }

    @Override
    public void onServiceDisconnected() {

    }

    public void HandleAllAction(){

           btn_play.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(serviceMusic.isPlaying()){
                       btn_play.setImageResource(R.drawable.ic_media_play);
                       serviceMusic.pause();
                   }else {
                       btn_play.setImageResource(R.drawable.ic_media_pause);
                       try {
                           serviceMusic.start(serviceMusic.currentsong);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }
           });


    }
}
