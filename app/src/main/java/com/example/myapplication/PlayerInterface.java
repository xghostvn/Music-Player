package com.example.myapplication;

import com.example.myapplication.models.SongInfo;

public interface PlayerInterface {

    interface CallBack{
        void onTrackChange(SongInfo songInfo);
        void onPause();
        void onStart();
        void onResume();

    }

}
