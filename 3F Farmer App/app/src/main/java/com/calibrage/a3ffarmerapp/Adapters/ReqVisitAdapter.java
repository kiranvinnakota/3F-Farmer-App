package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Activities.RecommendationListActivity;
import com.calibrage.a3ffarmerapp.Activities.RequestVisitDailogActivity;
import com.calibrage.a3ffarmerapp.Model.RecommendationModel;
import com.calibrage.a3ffarmerapp.R;

public class ReqVisitAdapter extends RecyclerView.Adapter<ReqVisitAdapter.ViewHolder>{
    private RecommendationModel[] listdata;
    public Context mContext;

    // RecyclerView recyclerView;
    public ReqVisitAdapter(Context ctx,RecommendationModel[] listdata) {
        this.listdata = listdata;
        this.mContext=ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recommendation_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RecommendationModel myListData = listdata[position];
        holder.textViewplotId.setText(listdata[position].getPlotId());
        holder.textViewpalmArea.setText(listdata[position].getPalmArea());
        holder.textViewLocation.setText(listdata[position].getLocation());
        holder.textViewstatus.setText(listdata[position].getStatus());
        //      holder.imageView.setImageResource(listdata[position].getImgId());
        holder.card_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RequestVisitDailogActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);


            }

        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewplotId;
        public TextView textViewpalmArea;
        public TextView textViewLocation;
        public TextView textViewstatus;
        public CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewplotId = (TextView) itemView.findViewById(R.id.plotId);
            this.textViewpalmArea = (TextView) itemView.findViewById(R.id.palmArea);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.location);
            this.textViewstatus = (TextView) itemView.findViewById(R.id.status);
            this.card_view =  (CardView) itemView.findViewById(R.id.card_view);

        }


    }
}