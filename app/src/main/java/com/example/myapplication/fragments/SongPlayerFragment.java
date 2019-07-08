package com.example.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.fragments.Fragment_Home;
import com.example.myapplication.fragments.MusicServiceFragment;
import com.example.myapplication.service.ServiceMusic;

public class SongPlayerFragment extends MusicServiceFragment {

        private ImageView btn_play;
        private ImageView btn_next;
        private ImageView btn_prev;
        private ServiceMusic serviceMusic;
        private LinearLayout linearLayout;
        private TextView song_name;
        private TextView song_artist;
        private Fragment_Home fragment_home;
        private SeekBar seekBar;



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.play_main,container,false);

       return  rootView;
    }


    @Override
    public void onServiceConnected(ServiceMusic serviceMusic) {

    }

    @Override
    public void onServiceDisconnected() {

    }
}
