package com.quran.administrator.quranapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quran.administrator.quranapp.Adapters.AhadethRecyclerAdapter;
import com.quran.administrator.quranapp.Base.BaseFragment;
import com.quran.administrator.quranapp.Model.HadethItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AhadethFragment extends BaseFragment {


    public AhadethFragment() {
        // Required empty public constructor
    }


    RecyclerView recyclerView;
    AhadethRecyclerAdapter adapter;
    LinearLayoutManager layoutManager;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_ahadeth, container, false);
        ArrayList<HadethItem> ahadeth = ReadAhadethFile();
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new AhadethRecyclerAdapter(ahadeth);

        layoutManager  = new LinearLayoutManager(activity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(new AhadethRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, HadethItem item) {
                HadethDialogFragment dialogFragment = new HadethDialogFragment();

                dialogFragment.setHadethString(item.getContent());

                dialogFragment.show(getChildFragmentManager(),"hadeth");
            }
        });

        return view;
    }

    public ArrayList<HadethItem> ReadAhadethFile(){
        ArrayList<HadethItem> ahadeth = new ArrayList<>();

        BufferedReader reader;

        try{
            final InputStream file = activity.getAssets().open("ahadeth.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line;
            //= reader.readLine();
            while((line = reader.readLine()) != null){
                String hadethTitle = line;
                HadethItem item = new HadethItem();
                item.setTitle(hadethTitle);

                while((line = reader.readLine()) != null){
                    if(line.trim().equals("#")){
                        break;
                    }
                    item.AddLine(line);
                }
                ahadeth.add(item);

            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        return ahadeth;

    }

}
