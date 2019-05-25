package com.example.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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




}
