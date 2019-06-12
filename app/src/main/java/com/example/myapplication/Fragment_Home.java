package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class Fragment_Home extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = rootview.findViewById(R.id.viewPager);
        tabLayout = rootview.findViewById(R.id.TabLayout);
        SectionPagerAdapter adapter = new SectionPagerAdapter(getFragmentManager());
        Fragment_SongList fragment_songList = new Fragment_SongList();
        Fragment_Albums fragment_albums = new Fragment_Albums();
        Fragment_Artist fragment_artist = new Fragment_Artist();
        Fragment_Playlist fragment_playlist = new Fragment_Playlist();
        adapter.AddFragment(fragment_songList,"All SONGS");
        adapter.AddFragment(fragment_albums,"ALBUMS");
        adapter.AddFragment(fragment_artist,"ARTIST");
        adapter.AddFragment(fragment_playlist,"PLAYLIST");
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(-1);
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("abc", "onResume: fragment_home");
    }


    @Override
    public void onStart() {
        super.onStart();





        Log.d("abc", "onStart: fragment_home");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("abc", "onActivityCreated: fragment_home");
    }
}
