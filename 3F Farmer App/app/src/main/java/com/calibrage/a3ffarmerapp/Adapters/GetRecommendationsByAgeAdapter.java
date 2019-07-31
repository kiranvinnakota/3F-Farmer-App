package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Model.GetRecommendationsByAgeModel;
import com.calibrage.a3ffarmerapp.Model.GetRecommendationsModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;



public class GetRecommendationsByAgeAdapter extends RecyclerView.Adapter<GetRecommendationsByAgeAdapter.ViewHolder> {


    private Context context;

    //List of superHeroes
    List<GetRecommendationsByAgeModel> superHeroes;

    public GetRecommendationsByAgeAdapter(List<GetRecommendationsByAgeModel> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.get_recommendations_by_age_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GetRecommendationsByAgeModel superHero =  superHeroes.get(position);

        holder.fertilizer.setText(superHero.getFertilizer());
        holder.uoM.setText(superHero.getUoM());
        holder.year.setText(superHero.getYear());
        holder.remarks.setText(superHero.getRemarks());
        String powers = "";

    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView fertilizer;
        public TextView uoM;
        public TextView year;

        public TextView  remarks,textViewPowers;

        public ViewHolder(View itemView) {
            super(itemView);

            fertilizer = (TextView) itemView.findViewById(R.id.fertilizer);
            uoM= (TextView) itemView.findViewById(R.id.uoM);
            year= (TextView) itemView.findViewById(R.id.year);
            remarks= (TextView) itemView.findViewById(R.id.remarks);


          //  textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);
        }
    }
}