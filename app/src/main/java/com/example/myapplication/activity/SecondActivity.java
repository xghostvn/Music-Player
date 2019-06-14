package com.example.myapplication.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.service.ServiceMusic;

public class SecondActivity extends AppCompatActivity {
    private ServiceConnection serviceConnection;
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_main);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("abc", "onServiceConnected: connected");
                ServiceMusic.MusicBinder musicBinder =(ServiceMusic.MusicBinder) service;
                serviceMusic = musicBinder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        initView();
        HandleAction();




    }
    private void initView(){
        Intent intent = this.getIntent();

        song_current_time = findViewById(R.id.song_current_time);
        song_duration = findViewById(R.id.song_duration);
        song_name = findViewById(R.id.song_name);
        song_artist = findViewById(R.id.song_artist);
        btn_next = findViewById(R.id.next_btn);
        btn_play = findViewById(R.id.play_btn);
        btn_prev = findViewById(R.id.prev_btn);
        seekBar = findViewById(R.id.my_seekbar);
        seekBar.setMax(intent.getIntExtra("song_duration",0));
        song_name.setText(intent.getStringExtra("song_name"));
        song_artist.setText(intent.getStringExtra("song_artist"));
        boolean type = intent.getBooleanExtra("Type",false);
        if(type){
            btn_play.setImageResource(R.drawable.ic_media_pause);
        }



        song_duration.setText(String.valueOf(HandleTimeSong(intent.getIntExtra("song_duration",0))));


    }
    private void HandleAction(){



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

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(serviceMusic!=null){
                    serviceMusic.mediaPlayer.seekTo(progress);
                }



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        runThread runThread = new runThread();
        runThread.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("abc", "onStart: service start");
        intent = new Intent(this,ServiceMusic.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        startService(intent);
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

    private double HandleTimeSong(int song_time){
        double duration = (double) song_time/1000/60;
        return (Math.round(duration*100.0)/100.0);

    }


}
