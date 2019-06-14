package com.example.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.models.SongInfo;

import java.util.ArrayList;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsHolder> {
    private ArrayList<SongInfo> List_Albums;
    public AlbumsAdapter(ArrayList<SongInfo> list_Albums){
        this.List_Albums = list_Albums;
    }
    @NonNull
    @Override
    public AlbumsAdapter.AlbumsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.AlbumsHolder albumsHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return List_Albums.size();
    }

    public class AlbumsHolder extends RecyclerView.ViewHolder{
        public AlbumsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
