package com.quran.administrator.quranapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quran.administrator.quranapp.Model.HadethItem;
import com.quran.administrator.quranapp.R;

import java.util.ArrayList;

public class AhadethRecyclerAdapter extends RecyclerView.Adapter<AhadethRecyclerAdapter.ViewHolder> {

    ArrayList<HadethItem> items;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public  AhadethRecyclerAdapter(ArrayList<HadethItem> items ){
            this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate new item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sura_item_view ,parent,false);
        //create view holder and returns it
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       final HadethItem item= items.get(position);
       holder.title.setText(item.getTitle());
       if(onItemClickListener!=null){
           holder.parent.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                  onItemClickListener.onItemClick(position,item);
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
        public ViewHolder(View view){
            super(view);
            parent = view;
            title = view.findViewById(R.id.title);
        }

    }

    public interface OnItemClickListener{

        void onItemClick(int position, HadethItem item);

    }
}
