package com.example.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistHolder> {
    @NonNull
    @Override
    public ArtistAdapter.ArtistHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistHolder artistHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ArtistHolder extends RecyclerView.ViewHolder {
        public ArtistHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
