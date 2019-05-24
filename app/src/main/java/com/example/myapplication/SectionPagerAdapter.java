package com.example.myapplication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> Fragment_List = new ArrayList<>();
    private ArrayList<String>  List_Title = new ArrayList<>();

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void AddFragment(Fragment fragment,String title){
        Fragment_List.add(fragment);
        List_Title.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return List_Title.get(position);
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
