package com.example.myapplication.fragments;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.SecondActivity;
import com.example.myapplication.models.SongInfo;
import com.example.myapplication.service.ServiceMusic;

public class Fragment_PlayerPanel extends MusicServiceFragment {

    private ServiceMusic serviceMusic;
    private TextView song_name;
    private TextView song_artist;
    private ImageView btn_next;
    private ImageView btn_play;
    private ImageView btn_prev;
    private Intent intent;
    private SeekBar seekBar;
    private TextView song_duration;
    private TextView song_current_time;
    private Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.play_main,container,false);

        song_current_time = rootView.findViewById(R.id.song_current_time);
        song_duration = rootView.findViewById(R.id.song_duration);
        song_name = rootView.findViewById(R.id.song_name);
        song_artist = rootView.findViewById(R.id.song_artist);
        btn_next = rootView.findViewById(R.id.next_btn);
        btn_play = rootView.findViewById(R.id.play_btn);
        btn_prev = rootView.findViewById(R.id.prev_btn);
        seekBar = rootView.findViewById(R.id.my_seekbar);

        HandleView();

        HandleAllAction();
        return rootView;

    }
    private void HandleAllAction(){
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceMusic!=null){
                    if(serviceMusic.isPlaying()){
                        serviceMusic.pause();
                        btn_play.setImageResource(R.drawable.ic_media_play);
                    }else {
                        serviceMusic.resume();
                        btn_play.setImageResource(R.drawable.ic_media_pause);
                    }
                }
            }
        });
        runThread runThread = new runThread();
        runThread.start();

    }
    private void HandleView(){
        bundle = getArguments();
        if(bundle!=null){
            boolean type = bundle.getBoolean("type");
            if(type){
                btn_play.setImageResource(R.drawable.ic_media_pause);
            }else {
                btn_play.setImageResource(R.drawable.ic_media_play);
            }
            song_name.setText(bundle.getString("song_name"));
            song_artist.setText(bundle.getString("song_artist"));
            seekBar.setMax(bundle.getInt("song_duration",0));
        }
    }

    @Override
    public void onServiceConnected(ServiceMusic serviceMusic) {
        this.serviceMusic = serviceMusic;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("abc", "onDestroy: destroy player panel");
        if(serviceMusic.isPlaying()){
            bundle.putBoolean("type",true);
        }else {
            bundle.putBoolean("type",false);
        }
        Fragment_Home fragment_home = new Fragment_Home();
        fragment_home.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment_home);
        fragmentTransaction.commit();
    }

    @Override
    public void onServiceDisconnected() {

    }

    private class runThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seekBar.post(new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if(serviceMusic!=null){
                            seekBar.setProgress(serviceMusic.mediaPlayer.getCurrentPosition());
                        }
                    }
                });
            }
        }
    }
}
