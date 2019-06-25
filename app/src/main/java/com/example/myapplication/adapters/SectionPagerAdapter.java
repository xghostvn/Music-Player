package com.example.myapplication.adapters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication.fragments.Fragment_Albums;
import com.example.myapplication.fragments.Fragment_Artist;
import com.example.myapplication.fragments.Fragment_Playlist;
import com.example.myapplication.fragments.Fragment_SongList;

import java.util.ArrayList;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> Fragment_List = new ArrayList<>();
    private String[]  List_Title = {"Song","Artist","Albums","PlayList"};
    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return List_Title[position];
    }

    public void AddFragment(Fragment fragment){
        Fragment_List.add(fragment);

    }
    @Override
    public Fragment getItem(int i) {
      return Fragment_List.get(i);
    }
    @Override
    public int getCount() {
        return Fragment_List.size();
    }
}
