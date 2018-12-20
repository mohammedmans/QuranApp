package com.quran.administrator.quranapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quran.administrator.quranapp.Adapters.VersesRecyclerAdapter;
import com.quran.administrator.quranapp.Base.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SuraContent extends BaseActivity {

    RecyclerView recyclerView;
    VersesRecyclerAdapter adapter;
    LinearLayoutManager layoutManager;
    TextView title;
    ImageView bookMark ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sura_content);
        final String fileName = getIntent().getStringExtra("fileName");
        final String suraName = getIntent().getStringExtra("suraName");
        final String scroll = getIntent().getStringExtra("scroll");
        title = findViewById(R.id.suraName);
        title.setText(suraName);
        bookMark = findViewById(R.id.book_mark);
        bookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int scroll = layoutManager.findFirstVisibleItemPosition();
                SaveData("scroll",scroll+"");
                SaveData("fileName",fileName);
                SaveData("suraName",suraName);
            }
        });
       // recyclerView.smoothScrollToPosition();
        //read file content
        ArrayList<String>SuraContent = ReadSuraFile(fileName);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new VersesRecyclerAdapter(SuraContent);
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if(scroll!=null){
            recyclerView.smoothScrollToPosition(Integer.valueOf(scroll));
        }

        //Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
    }

    ArrayList<String> ReadSuraFile(String FileName){
        ArrayList<String> content = new ArrayList<>();

        BufferedReader reader;

        try{
            final InputStream file = getAssets().open(FileName);
            reader = new BufferedReader(new InputStreamReader(file));
            String line;
            //= reader.readLine();
            while((line = reader.readLine()) != null){
                if(!line.trim().equals(""))
                    content.add(line);
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        return content;

    }
}
