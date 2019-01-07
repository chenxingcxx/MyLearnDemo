package com.cxx.learndemo.mvvmaccdemo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.VH> {
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class VH extends RecyclerView.ViewHolder {
        public final TextView title;

        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
        }
    }
}

