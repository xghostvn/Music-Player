package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.TabLayout);
        viewPager.setOffscreenPageLimit(4);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        Log.d("check", "onCreate: ");





    }


    private void setupViewPager(ViewPager viewPager){
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        Fragment_SongList fragment_songList = new Fragment_SongList();
        Fragment_Albums fragment_albums = new Fragment_Albums();
        Fragment_Artist fragment_artist = new Fragment_Artist();
        Fragment_Playlist fragment_playlist = new Fragment_Playlist();
        adapter.AddFragment(fragment_songList,"All SONGS");
        adapter.AddFragment(fragment_albums,"ALBUMS");
        adapter.AddFragment(fragment_artist,"ARTIST");
        adapter.AddFragment(fragment_playlist,"PLAYLIST");
        viewPager.setAdapter(adapter);

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






}
