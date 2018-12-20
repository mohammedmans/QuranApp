package com.quran.administrator.quranapp.Adapters;

import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.quran.administrator.quranapp.API.Responses.RadiosItem;
import com.quran.administrator.quranapp.Model.HadethItem;
import com.quran.administrator.quranapp.R;

import java.util.ArrayList;
import java.util.List;

public class RadiosRecyclerAdapter extends RecyclerView.Adapter<RadiosRecyclerAdapter.ViewHolder> {

    List<RadiosItem> items;
    OnItemClickListener onPlay;
    OnItemClickListener onStop;


    public void setOnPlay(OnItemClickListener onPlay) {
        this.onPlay = onPlay;
    }

    public void setOnStop(OnItemClickListener onStop) {
        this.onStop = onStop;
    }

    public RadiosRecyclerAdapter(List<RadiosItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate new item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.radio_item ,parent,false);
        //create view holder and returns it
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       final RadiosItem item= items.get(position);
       holder.title.setText(item.getName());
       if(onPlay!=null){
           holder.Play.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                  onPlay.onItemClick(position,item);
               }
           });
       }

        if(onStop!=null){
            holder.Stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStop.onItemClick(position,item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title ;
        View parent;
        Button Play;
        Button Stop;
        public ViewHolder(View view){
            super(view);
            parent = view;
            Play = view.findViewById(R.id.play);
            Stop = view.findViewById(R.id.stop);
            title = view.findViewById(R.id.title);
        }

    }



    public interface OnItemClickListener{

        void onItemClick(int position, RadiosItem item);

    }
}
