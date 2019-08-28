package com.example.meetingsystemandroid.main.search;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchActivityAdapter extends RecyclerView.Adapter<SearchActivityAdapter.SearchItemViewHolder> {

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class SearchItemViewHolder extends RecyclerView.ViewHolder {

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
