package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Model.MyReqModel;
import com.calibrage.a3ffarmerapp.Model.RecommendationListModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;



public class MyReqAdapter extends RecyclerView.Adapter {
    List<MyReqModel> movieList;
    Context context;

    public MyReqAdapter( Context contextm,List<MyReqModel> movieList) {
        this.context=contextm;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.my_req_list
                        , parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.txtPlotId.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtDate.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTime.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtComments.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtDateNTime.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtReqDate.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtApproveDate.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtname.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtMobileNo.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtPin.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtPlotId.setText(R.string.plot_id);
            rowViewHolder.txtPlotId.setTextColor(Color.WHITE);
            rowViewHolder.txtDate.setText(R.string.only_date);
            rowViewHolder.txtDate.setTextColor(Color.WHITE);
            rowViewHolder.txtTime.setText(R.string.only_time);
            rowViewHolder.txtTime.setTextColor(Color.WHITE);
            rowViewHolder.txtComments.setText(R.string.comments);
            rowViewHolder.txtComments.setTextColor(Color.WHITE);
            rowViewHolder.txtDateNTime.setText(R.string.date_n_time);
            rowViewHolder.txtDateNTime.setTextColor(Color.WHITE);
            rowViewHolder.txtReqDate.setText(R.string.req_date);
            rowViewHolder.txtReqDate.setTextColor(Color.WHITE);
            rowViewHolder.txtApproveDate.setText(R.string.approve_date);
            rowViewHolder.txtApproveDate.setTextColor(Color.WHITE);
            rowViewHolder.txtStatus.setText(R.string.only_status);
            rowViewHolder.txtStatus.setTextColor(Color.WHITE);
            rowViewHolder.txtname.setText(R.string.name_field);
            rowViewHolder.txtname.setTextColor(Color.WHITE);
            rowViewHolder.txtMobileNo.setText(R.string.mobile_number);
            rowViewHolder.txtMobileNo.setTextColor(Color.WHITE);
            rowViewHolder.txtPin.setText(R.string.pin);
            rowViewHolder.txtPin.setTextColor(Color.WHITE);
        } else {
            MyReqModel modal = movieList.get(rowPos-1);

            // Content Cells. Content appear here
            rowViewHolder.txtPlotId.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtDate.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtComments.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtDateNTime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtReqDate.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtApproveDate.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtComments.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtMobileNo.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtPin.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtPlotId.setText(modal.getPlotId()+"");
            rowViewHolder.txtDate.setText(modal.getDate());
            rowViewHolder.txtTime.setText(modal.getTime()+"");
            rowViewHolder.txtComments.setText(modal.getComments()+"");
            rowViewHolder.txtDateNTime.setText(modal.getDateNTime()+"");
            rowViewHolder.txtReqDate.setText(modal.getReqDate()+"");
            rowViewHolder.txtApproveDate.setText(modal.getApproveDate()+"");
            rowViewHolder.txtStatus.setText(modal.getStatus()+"");
            rowViewHolder.txtname.setText(modal.getName()+"");
            rowViewHolder.txtMobileNo.setText(modal.getMobileNumber()+"");
            rowViewHolder.txtPin.setText(modal.getPin()+"");
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size()+1; // one more to add header row
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtPlotId;
        protected TextView txtDate;
        protected TextView txtTime;
        protected TextView txtComments;
        protected TextView txtDateNTime;
        protected TextView txtReqDate;
        protected TextView txtApproveDate;
        protected TextView txtStatus;
        protected TextView txtname;
        protected TextView txtMobileNo;
        protected TextView txtPin;

        public RowViewHolder(View itemView) {
            super(itemView);

            txtPlotId = itemView.findViewById(R.id.plotId);
            txtDate = itemView.findViewById(R.id.date);
            txtTime = itemView.findViewById(R.id.time);
            txtComments = itemView.findViewById(R.id.comments);
            txtDateNTime = itemView.findViewById(R.id.dateNTime);
            txtReqDate = itemView.findViewById(R.id.reqDate);
            txtApproveDate = itemView.findViewById(R.id.approveDate);
            txtStatus = itemView.findViewById(R.id.status);
            txtname = itemView.findViewById(R.id.name);
            txtMobileNo = itemView.findViewById(R.id.mobileNo);
            txtPin = itemView.findViewById(R.id.pin);

        }
    }
}
