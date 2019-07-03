package com.calibrage.a3ffarmerapp.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Model.FertilizerModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;

public class FertilizerAdapter extends RecyclerView.Adapter {
    List<FertilizerModel> movieList;

    public FertilizerAdapter(List<FertilizerModel> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.fertilizer_list_item, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.txtPlotCode.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtAge.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtSize.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtVillage.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtLandmark.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtFertilizer1.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtFertilizer2.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtFertilizer3.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtPlotCode.setText("Plot Code");
            rowViewHolder.txtPlotCode.setTextColor(Color.WHITE);
            rowViewHolder.txtAge.setText("Age");
            rowViewHolder.txtAge.setTextColor(Color.WHITE);
            rowViewHolder.txtSize.setText("Size");
            rowViewHolder.txtSize.setTextColor(Color.WHITE);
            rowViewHolder.txtVillage.setText("Village");
            rowViewHolder.txtVillage.setTextColor(Color.WHITE);
            rowViewHolder.txtLandmark.setText("Land Mark");
            rowViewHolder.txtLandmark.setTextColor(Color.WHITE);
            rowViewHolder.txtFertilizer1.setText("Fertilizer1");
            rowViewHolder.txtFertilizer1.setTextColor(Color.WHITE);
            rowViewHolder.txtFertilizer2.setText("Fertilizer2");
            rowViewHolder.txtFertilizer2.setTextColor(Color.WHITE);
            rowViewHolder.txtFertilizer3.setText("Fertilizer3");
            rowViewHolder.txtFertilizer3.setTextColor(Color.WHITE);
        } else {
            FertilizerModel modal = movieList.get(rowPos-1);

            // Content Cells. Content appear here
            rowViewHolder.txtPlotCode.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtAge.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtSize.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtFertilizer1.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtFertilizer2.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtFertilizer3.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtPlotCode.setText(modal.getPlotCode()+"");
            rowViewHolder.txtAge.setText(modal.getAge());
            rowViewHolder.txtSize.setText(modal.getSize()+"");
            rowViewHolder.txtVillage.setText(modal.getVillage());
            rowViewHolder.txtLandmark.setText(modal.getLandmark()+"");
            rowViewHolder.txtFertilizer1.setText(modal.getFertilizer1()+"");
            rowViewHolder.txtFertilizer2.setText(modal.getFertilizer2()+"");
            rowViewHolder.txtFertilizer3.setText(modal.getFertilizer3()+"");
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size()+1; // one more to add header row
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtPlotCode;
        protected TextView txtAge;
        protected TextView txtSize;
        protected TextView txtVillage;
        protected TextView txtLandmark;
        protected TextView txtFertilizer1;
        protected TextView txtFertilizer2;
        protected TextView txtFertilizer3;


        public RowViewHolder(View itemView) {
            super(itemView);

            txtPlotCode = itemView.findViewById(R.id.plot_code);
            txtAge = itemView.findViewById(R.id.age);
            txtSize = itemView.findViewById(R.id.size);
            txtVillage = itemView.findViewById(R.id.village);
            txtLandmark = itemView.findViewById(R.id.landmark);

            txtFertilizer1 = itemView.findViewById(R.id.fertilizer1);
            txtFertilizer2 = itemView.findViewById(R.id.fertilizer2);
            txtFertilizer3 = itemView.findViewById(R.id.fertilize3);

        }
    }
}
