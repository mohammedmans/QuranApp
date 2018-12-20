package com.quran.administrator.quranapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.quran.administrator.quranapp.Adapters.SurasRecyclerAdapter;
import com.quran.administrator.quranapp.Base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuranFragment extends BaseFragment {

    public static  String []ArSuras={"الفاتحه","البقرة","آل عمران","النساء","المائدة","الأنعام","الأعراف","الأنفال","التوبة","يونس","هود"
            ,"يوسف","الرعد","إبراهيم","الحجر","النحل","الإسراء","الكهف","مريم","طه","الأنبياء","الحج","المؤمنون"
            ,"النّور","الفرقان","الشعراء","النّمل","القصص","العنكبوت","الرّوم","لقمان","السجدة","الأحزاب","سبأ"
            ,"فاطر","يس","الصافات","ص","الزمر","غافر","فصّلت","الشورى","الزخرف","الدّخان","الجاثية","الأحقاف"
            ,"محمد","الفتح","الحجرات","ق","الذاريات","الطور","النجم","القمر","الرحمن","الواقعة","الحديد","المجادلة"
            ,"الحشر","الممتحنة","الصف","الجمعة","المنافقون","التغابن","الطلاق","التحريم","الملك","القلم","الحاقة","المعارج"
            ,"نوح","الجن","المزّمّل","المدّثر","القيامة","الإنسان","المرسلات","النبأ","النازعات","عبس","التكوير","الإنفطار"
            ,"المطفّفين","الإنشقاق","البروج","الطارق","الأعلى","الغاشية","الفجر","البلد","الشمس","الليل","الضحى","الشرح"
            ,"التين","العلق","القدر","البينة","الزلزلة","العاديات","القارعة","التكاثر","العصر",
            "الهمزة","الفيل","قريش","الماعون","الكوثر","الكافرون","النصر","المسد","الإخلاص","الفلق","الناس"};


    public QuranFragment() {
        // Required empty public constructor
    }


    RecyclerView recyclerView;
    SurasRecyclerAdapter adapter;
    LinearLayoutManager layoutManager;
    ImageView gotoBookMark;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_quran, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(activity);
        ArrayList<String > data =new ArrayList<>(Arrays.asList(ArSuras)) ;
        adapter  = new SurasRecyclerAdapter (data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(new SurasRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String SuraName) {
                Intent intent = new Intent(getActivity(),SuraContent.class);
                intent.putExtra("fileName",(position+1)+".txt");
                intent.putExtra("suraName",SuraName);
                //intent.putExtra("suraName",SuraName);
                startActivity(intent);
            }
        });
        gotoBookMark = view.findViewById(R.id.book_mark);
        gotoBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName=activity.getData("fileName");
                String suraName=activity.getData("suraName");
                String scroll=activity.getData("scroll");
                if(scroll!=null){
                    Intent intent = new Intent(getActivity(),SuraContent.class);
                    intent.putExtra("fileName",fileName);
                    intent.putExtra("suraName",suraName);
                    intent.putExtra("scroll",scroll);
                    startActivity(intent);
                }else {
                    ShowMessage("warning","no saved bookMark");
                }
            }
        });
        return view;

    }



}
