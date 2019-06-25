package com.example.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AlbumsAdapter;
import com.example.myapplication.loader.HandleSong;

public class Fragment_Albums extends Fragment {
    private RecyclerView albums_recycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_albums,container,false);
        albums_recycler = rootView.findViewById(R.id.albums_recyclerView);
        AlbumsAdapter albumsAdapter = new AlbumsAdapter(HandleSong.get(getContext()).getListSong());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        albums_recycler.setLayoutManager(linearLayoutManager);
        albums_recycler.setAdapter(albumsAdapter);

        return rootView;
    }
}
