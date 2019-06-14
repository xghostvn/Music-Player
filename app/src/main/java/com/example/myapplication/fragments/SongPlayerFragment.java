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

        fragment_home = new Fragment_Home();

        btn_play = rootView.findViewById(R.id.play_btn);
        btn_next = rootView.findViewById(R.id.next_btn);
        btn_prev = rootView.findViewById(R.id.prev_btn);
        linearLayout = rootView.findViewById(R.id.player_panel);
        song_name = rootView.findViewById(R.id.song_name);
        song_artist = rootView.findViewById(R.id.song_artist);
        seekBar = rootView.findViewById(R.id.my_seekbar);
        seekBar.setProgress(0);


        btn_play.setImageResource(R.drawable.ic_media_pause);

        HandleAllAction();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle songinfo = getArguments();
        if(songinfo!=null){
            song_name.setText(songinfo.getString("song_name"));
            song_artist.setText(songinfo.getString("song_artist"));
        }
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

                           serviceMusic.start(serviceMusic.currentsong);

                   }
               }
           });

           linearLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                   fragmentTransaction.replace(R.id.main_frame,fragment_home);
                   fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                   fragmentTransaction.addToBackStack(null);
                   fragmentTransaction.commit();
               }
           });



    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("abc", "onStart: SongPlayer Fragment Start Service");
    }
}
