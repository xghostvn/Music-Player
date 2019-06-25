package com.example.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.adapters.SectionPagerAdapter;

public class Fragment_Home extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home,container,false);
        Log.d("abc", "onCreateView: fragment_home");
        viewPager = rootview.findViewById(R.id.viewPager);
        tabLayout = rootview.findViewById(R.id.TabLayout);
        FragmentManager fm = getChildFragmentManager(); // fragment child destroy view and create View again
        SectionPagerAdapter adapter = new SectionPagerAdapter(fm);
        Fragment_SongList fragment_songList = new Fragment_SongList();
        Bundle bundle = getArguments();
        if(bundle!=null){
            fragment_songList.setArguments(bundle);
        }


        Fragment_Artist fragment_artist = new Fragment_Artist();
        Fragment_Albums fragment_albums = new Fragment_Albums();
        Fragment_Playlist fragment_playlist = new Fragment_Playlist();
        adapter.AddFragment(fragment_songList);
        adapter.AddFragment(fragment_artist);
        adapter.AddFragment(fragment_albums);
        adapter.AddFragment(fragment_playlist);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("abc", "onDestroy: fragment_home destroy");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("abc", "onDestroyView: fragment_home destroy view");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("abc", "onPause: fragment_home");
    }


}
