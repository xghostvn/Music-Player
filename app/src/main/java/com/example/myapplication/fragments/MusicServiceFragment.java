package com.example.myapplication.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.myapplication.service.ServiceMusic;

public abstract class MusicServiceFragment extends Fragment {
    private String TAG = "MusicServiceFragment : ";
    private ServiceMusic serviceMusic;
    private Intent intent;
    ServiceConnection serviceConnection;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("abc", "onCreate: ");
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                      Log.d("abc", " onServiceConnected ");
                    ServiceMusic.MusicBinder musicBinder = (ServiceMusic.MusicBinder) service;
                    serviceMusic = musicBinder.getService();
                    MusicServiceFragment.this.onServiceConnected(serviceMusic);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                MusicServiceFragment.this.onServiceDisconnected();
                Log.d(TAG, " onServiceDisconnected");
            }
        };

    }


    public abstract void onServiceConnected(ServiceMusic serviceMusic);
    public abstract void onServiceDisconnected();

    @Override
    public void onStart() {
        super.onStart();
        Log.d("def", "onStart: ");
        intent = new Intent(getContext(),ServiceMusic.class);
        getActivity().bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);
    }
}
