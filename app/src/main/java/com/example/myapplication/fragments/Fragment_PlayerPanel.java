package com.example.myapplication.fragments;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.PlayerInterface;
import com.example.myapplication.R;

import com.example.myapplication.loader.HandleSong;
import com.example.myapplication.models.SongInfo;
import com.example.myapplication.service.ServiceMusic;
import com.example.myapplication.utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_PlayerPanel extends MusicServiceFragment {
    private BottomSheetBehavior bottomSheetBehavior;
    private ServiceMusic serviceMusic;
    private TextView song_name;
    private TextView song_artist;
    private ImageView btn_next;
    private ImageView btn_play;
    private ImageView btn_prev;
    private Intent intent;

    private TextView song_duration;
    private TextView song_current_time;
    @BindView(R.id.cl_player_interface)
    ConstraintLayout constraintLayout;
    @BindView(R.id.iv_pn_action_btn)
    ImageView actionbtn;
    @BindView(R.id.tv_pn_total_time)
    TextView total_time;
    @BindView(R.id.tv_pn_remain_time)
    TextView remain_time;
    @BindView(R.id.sb_pn_player)
    SeekBar seekBar;
    private ConstraintLayout.LayoutParams params;
    boolean servicestatus = false;
    private Bundle bundle;
    String TAG = "abc";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.play_main,container,false);
        ButterKnife.bind(this,rootView);
        bottomSheetBehavior = BottomSheetBehavior.from(constraintLayout);
        song_name = rootView.findViewById(R.id.tv_panel_song_name);
        song_artist = rootView.findViewById(R.id.tv_panel_artist_name);
        btn_next = rootView.findViewById(R.id.iv_pn_next_btn);
        btn_play = rootView.findViewById(R.id.iv_pn_play_btn);
        btn_prev = rootView.findViewById(R.id.iv_pn_prev_btn);

        params = (ConstraintLayout.LayoutParams) song_name.getLayoutParams();

        HandleView();


        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SeekBarThread seekBarThread = new SeekBarThread();
        seekBarThread.start();
    }

    private void HandleAllAction(){
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceMusic!=null){
                    if(serviceMusic.isPlaying()){
                        serviceMusic.pause();

                    }else {
                        serviceMusic.resume();

                    }
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                serviceMusic.play_next();




            }
        });


        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                serviceMusic.play_prev();




            }
        });

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        actionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceMusic.isPlaying()){
                    serviceMusic.pause();
                }else {
                    serviceMusic.resume();
                }
            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newstate) {

            }

            @Override
            public void onSlide(@NonNull View view, float slideOffset) {
                params.topMargin = Helper.dpToPx(getActivity(), slideOffset * 30 + 5);
                actionbtn.setAlpha(1 - slideOffset);
                song_name.setLayoutParams(params);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(serviceMusic!=null && fromUser){
                   serviceMusic.SeekTo(progress);
               }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //update UI
        serviceMusic.setCallBack(new PlayerInterface.CallBack() {
            @Override
            public void onTrackChange(SongInfo songInfo) {
                Log.d("abc", "onTrackChange: UI update");
                UpdateUiOntrackChange(songInfo);
                actionbtn.setImageResource(R.drawable.ic_media_pause);
            }

            @Override
            public void onPause() {
                actionbtn.setImageResource(R.drawable.ic_media_play);
                btn_play.setImageResource(R.drawable.ic_media_play);
                servicestatus = false;
            }

            @Override
            public void onStart() {
                Log.d(TAG, "onStart: ");
                servicestatus = true;
                seekBar.setMax(serviceMusic.getMaxDuration());
                seekBar.setProgress(0);
                actionbtn.setImageResource(R.drawable.ic_media_pause);
                btn_play.setImageResource(R.drawable.ic_media_pause);
            }

            @Override
            public void onResume() {
                servicestatus = true;
                seekBar.setMax(serviceMusic.getMaxDuration());
                seekBar.setProgress(serviceMusic.getCurrentStreamPosition());
                actionbtn.setImageResource(R.drawable.ic_media_pause);
                btn_play.setImageResource(R.drawable.ic_media_pause);
            }

        });




    }
    private void HandleView(){
        actionbtn.setImageResource(R.drawable.ic_media_play);
    }

    @Override
    public void onServiceConnected(ServiceMusic serviceMusic) {
        Log.d("abc", "onServiceConnected: Service Player Panel Connected ");
        this.serviceMusic = serviceMusic;
        servicestatus = true;
        UpdateUI();
        HandleAllAction();
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
    /*
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
    }*/


        public void UpdateUiOntrackChange(final SongInfo songInfo){
            Thread updateThread = new Thread(){
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            song_name.setText(songInfo.SongName);
                            song_artist.setText(songInfo.Artist);
                        }
                    });
                }
            };

            updateThread.start();

        }

        private class SeekBarThread extends Thread{

            @Override
            public void run() {
               while (true){
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                    if(servicestatus){
                        final String total = Helper.toTimeFormat(serviceMusic.getMaxDuration());
                        final String remain = Helper.toTimeFormat(serviceMusic.getCurrentStreamPosition());
                        if(serviceMusic.CheckSongOver()){
                            Log.d(TAG, "run: play next");
                            serviceMusic.play_next();
                        }else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    total_time.setText(total);
                                    remain_time.setText(remain);
                                    seekBar.setProgress(serviceMusic.getCurrentStreamPosition());


                                }
                            });
                        }

                    }

               }



            }
        }

    @Override
    public void onResume() {
        Log.d("abc", "onResume: on resume player panel called");
        super.onResume();
        UpdateUI();

    }

    private void UpdateUI(){
            if(serviceMusic!=null){
                SongInfo songInfo = serviceMusic.currentsong;
                UpdateUiOntrackChange(songInfo);
            }
    }

}
