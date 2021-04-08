package com.ondemande.latihanretrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<MainModel.Result> results;
    private OnAdapterListener adapterListener;

    public MainAdapter(List<MainModel.Result> results, OnAdapterListener adapterListener) {
        this.results = results;
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        MainModel.Result resuult = results.get(position);
        holder.tvText.setText(resuult.getTitle());
        Picasso.get().load(resuult.getImage()).fit().centerCrop().into(holder.ivImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onClick(resuult);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvText = itemView.findViewById(R.id.tvText);
        }
    }

    public void setData (List<MainModel.Result> data){
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }

    interface OnAdapterListener{
        void onClick(MainModel.Result data);
    }
}
