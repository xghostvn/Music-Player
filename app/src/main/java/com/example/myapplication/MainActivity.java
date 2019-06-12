package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Fragment_Home(),"main_content").commit();


        Log.d("check", "onCreate: ");
    }


    private void checkPermission(){
        if(Build.VERSION.SDK_INT >=23){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
                return;
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("permit", "onRequestPermissionsResult:  success");
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    checkPermission();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public void abc(String songname,String songartist){
        TextView song_artist = findViewById(R.id.tac_gia);
        TextView song_name   = findViewById(R.id.ten_BH);
        song_artist.setText(songartist);
        song_name.setText(songname);
        ImageView imageView = findViewById(R.id.btn_play);
        imageView.setImageResource(R.drawable.ic_media_pause);
    }

    public void LoadsCurrentSong(){


        SongInfo songInfo = HandleSong.get(this).getListSong().get(MusicPreferences.get(this).getLastSong());
        TextView song_artist = findViewById(R.id.tac_gia);
        TextView song_name   = findViewById(R.id.ten_BH);

        song_artist.setText(songInfo.Artist);
        song_name.setText(songInfo.SongName);


    }


}
