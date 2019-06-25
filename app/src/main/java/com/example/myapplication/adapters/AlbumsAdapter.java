package com.example.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
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
        View view = inflater.inflate(R.layout.item_albums,viewGroup,false);


        return new AlbumsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.AlbumsHolder albumsHolder, int i) {
            albumsHolder.albums_name.setText(List_Albums.get(i).Albums);
            albumsHolder.albums_artist.setText(List_Albums.get(i).Artist);




    }

    @Override
    public int getItemCount() {
        return List_Albums.size();
    }

    public class AlbumsHolder extends RecyclerView.ViewHolder{

        private TextView albums_name;
        private TextView albums_artist;
        public AlbumsHolder(@NonNull View itemView) {
            super(itemView);

            albums_name = itemView.findViewById(R.id.albums_name);
            albums_artist = itemView.findViewById(R.id.albums_artist);


        }
    }
}
