package com.quran.administrator.quranapp;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.quran.administrator.quranapp.API.APIManager;
import com.quran.administrator.quranapp.API.Responses.RadiosItem;
import com.quran.administrator.quranapp.API.Responses.RadiosResponse;
import com.quran.administrator.quranapp.Adapters.RadiosRecyclerAdapter;
import com.quran.administrator.quranapp.Base.BaseFragment;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RadioFragment extends BaseFragment {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager ;
    RadiosRecyclerAdapter RadiosrecyclerAdapter;


    public RadioFragment() {
        // Required empty public constructor
    }

    RadiosResponse allChannels;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_radio, container, false);
         recyclerView = view.findViewById(R.id.recycler_view);
         getRadioChannels();
        return view;
    }




    public void setRadioChannels (List<RadiosItem> RadiosItems)
    {
        RadiosrecyclerAdapter = new RadiosRecyclerAdapter(RadiosItems);
        layoutManager = new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setAdapter(RadiosrecyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
        RadiosrecyclerAdapter.setOnPlay(new RadiosRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, RadiosItem item) {
                PlayRadio(item.getURL());
            }
        });

        RadiosrecyclerAdapter.setOnStop(new RadiosRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, RadiosItem item) {
                stopRadio();
            }
        });




    }

    MediaPlayer mediaPlayer;
    public void stopRadio(){
        if(mediaPlayer!=null&&mediaPlayer.isPlaying())
            mediaPlayer.stop();

    }


    public void PlayRadio(String url){

        stopRadio();


        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void getRadioChannels(){
        ShowProgressBar();
        APIManager.getAPIs()
                .getRadioChannels()
                .enqueue(new Callback<RadiosResponse>() {
                    @Override
                    public void onResponse(Call<RadiosResponse> call,
                                           Response<RadiosResponse> response) {
                        HideProgressBar();
                        if(response.code()==200){
                            Log.e("code","success");
                            allChannels = response.body();
                            Log.e("response",response.body().toString());
                             setRadioChannels(response.body().getRadios());
                        }

                    }

                    @Override
                    public void onFailure(Call<RadiosResponse> call, Throwable t) {
                        HideProgressBar();
                        ShowMessage("error",t.getLocalizedMessage());
                    }
                });
    }

}
