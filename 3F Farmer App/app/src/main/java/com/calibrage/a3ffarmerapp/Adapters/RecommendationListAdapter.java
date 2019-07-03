package com.calibrage.a3ffarmerapp.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Model.FertilizerModel;
import com.calibrage.a3ffarmerapp.Model.RecommendationListModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;


public class RecommendationListAdapter extends RecyclerView.Adapter {
    List<RecommendationListModel> movieList;

    public RecommendationListAdapter(List<RecommendationListModel> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.recommended_list_item, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.txtPlotCode.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtRecommendedChemical.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtDosage.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtComments.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtIssue.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtRecommendedBy.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtRecommendedOn.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtPlotCode.setText(R.string.plot_code);
            rowViewHolder.txtPlotCode.setTextColor(Color.WHITE);
            rowViewHolder.txtRecommendedChemical.setText(R.string.recommendedorfertilizer);
            rowViewHolder.txtRecommendedChemical.setTextColor(Color.WHITE);
            rowViewHolder.txtDosage.setText(R.string.dosage);
            rowViewHolder.txtDosage.setTextColor(Color.WHITE);
            rowViewHolder.txtComments.setText(R.string.comments);
            rowViewHolder.txtComments.setTextColor(Color.WHITE);
            rowViewHolder.txtIssue.setText(R.string.issue_identified);
            rowViewHolder.txtIssue.setTextColor(Color.WHITE);
            rowViewHolder.txtRecommendedBy.setText(R.string.recommended_by);
            rowViewHolder.txtRecommendedBy.setTextColor(Color.WHITE);
            rowViewHolder.txtRecommendedOn.setText(R.string.recommended_on);
            rowViewHolder.txtRecommendedOn.setTextColor(Color.WHITE);
        } else {
            RecommendationListModel modal = movieList.get(rowPos-1);

            // Content Cells. Content appear here
            rowViewHolder.txtPlotCode.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtRecommendedChemical.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtDosage.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtComments.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtIssue.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtRecommendedBy.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtRecommendedOn.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtPlotCode.setText(modal.getPlotCode()+"");
            rowViewHolder.txtRecommendedChemical.setText(modal.getRecommendedChemical());
            rowViewHolder.txtDosage.setText(modal.getDosage()+"");
            rowViewHolder.txtComments.setText(modal.getComments()+"");
            rowViewHolder.txtIssue.setText(modal.getIssue()+"");
            rowViewHolder.txtRecommendedBy.setText(modal.getRecommendedBy()+"");
            rowViewHolder.txtRecommendedOn.setText(modal.getRecommendedOn()+"");
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size()+1; // one more to add header row
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtPlotCode;
        protected TextView txtRecommendedChemical;
        protected TextView txtDosage;
        protected TextView txtComments;
        protected TextView txtIssue;
        protected TextView txtRecommendedBy;
        protected TextView txtRecommendedOn;

        public RowViewHolder(View itemView) {
            super(itemView);

            txtPlotCode = itemView.findViewById(R.id.plotCode);
            txtRecommendedChemical = itemView.findViewById(R.id.recommendedChemical);
            txtComments = itemView.findViewById(R.id.comments);
            txtDosage = itemView.findViewById(R.id.dosage);
            txtIssue = itemView.findViewById(R.id.issue);
            txtRecommendedBy = itemView.findViewById(R.id.recommendedBy);
            txtRecommendedOn = itemView.findViewById(R.id.recommendedOn);

        }
    }
}
