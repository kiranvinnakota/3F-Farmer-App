package com.calibrage.a3ffarmerapp.Adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Activities.LabourActivity;
import com.calibrage.a3ffarmerapp.Model.MovieModal;
import com.calibrage.a3ffarmerapp.Model.RecommendationModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter<TableViewAdapter.ViewHolder>{
    private MovieModal[] listdata;
    public Context mContext;

    // RecyclerView recyclerView;
    public TableViewAdapter(Context ctx,MovieModal[] listdata) {
        this.listdata = listdata;
        this.mContext=ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.table_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieModal myListData = listdata[position];
        holder.txtCollectionId.setText(listdata[position].getCollectionId());
        holder.txtDate.setText(listdata[position].getDate());
        holder.txtWeight.setText(listdata[position].getWeight());
        holder.txtCc.setText(listdata[position].getCc());
        holder.txtStatus.setText(listdata[position].getStatus());
        //      holder.imageView.setImageResource(listdata[position].getImgId());

    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCollectionId;
        public TextView txtDate;
        public TextView txtWeight;
        public TextView txtCc;
        public TextView txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCollectionId = itemView.findViewById(R.id.collection_id);
            txtDate = itemView.findViewById(R.id.date);
            txtWeight = itemView.findViewById(R.id.weight);
            txtCc = itemView.findViewById(R.id.cc);
            txtStatus = itemView.findViewById(R.id.status);
        }


    }
}