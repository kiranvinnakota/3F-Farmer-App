package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.calibrage.a3ffarmerapp.Model.Album;
import com.calibrage.a3ffarmerapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;
    int minteger = 0;
    HashMap<String,Integer> positiveNumbers = new HashMap<String,Integer>() ;
    

    public  TextView displayInteger;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail, overflow;
        Button plus, minus;
        public TextView number,price;
        public TextView disc,size;
        public String uniqueKey;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            //  count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            number = (TextView) view.findViewById(
                    R.id.integer_number);
            price = (TextView) view.findViewById(
                    R.id.price);
            disc = (TextView) view.findViewById(
                    R.id.desc);
            size = (TextView) view.findViewById(
                    R.id.size);
            plus = (Button) view.findViewById(
                    R.id.increase);

            minus = (Button) view.findViewById(R.id.decrease);

        }

    }
    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }
   /* public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        display(minteger);
    }
*/

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ArrayList<Integer> count = new ArrayList<>();
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.price.setText(album.getPrice());
        holder.disc.setText(album.getDisc());
        holder.size.setText(album.getSize());
        //   holder.count.setText(album.getNumOfSongs() + " songs");

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer i = 0;
                i++;
                count.add(holder.getAdapterPosition());
               // Log.e("", ""+Arrays.(count));
                holder.number.setText(""+count.size());
                positiveNumbers.put(holder.uniqueKey, count.size()); //Key -> String.valueOf(position) and Value -> int count
               // notifyDataSetChanged();

            }


        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count.size()>0 ){
                    count.remove(count.size()-1);
                }
                holder.number.setText(""+count.size());
                positiveNumbers.put(holder.uniqueKey, count.size());   //Key -> String.valueOf(position) and Value -> int count
               // notifyDataSetChanged();
            }
        });

        ;



      /*  holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showPopupMenu(holder.overflow);
            }
        });*/
    }


    /**
     * Showing popup menu when tapping on 3 dots
    /* *//*
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    *//**
     * Click listener for popup menu items
     *//*
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }*/

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
