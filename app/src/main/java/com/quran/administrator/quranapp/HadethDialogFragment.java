package com.quran.administrator.quranapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HadethDialogFragment extends DialogFragment {


    public HadethDialogFragment(){

    }

    String HadethString;

    public void setHadethString(String hadethString) {
        HadethString = hadethString;
    }

    TextView hadeth_content;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hadeth_dialog_fragment ,container,false);
        hadeth_content = view.findViewById(R.id.hadeth_content);
        Log.e("text",HadethString);
        hadeth_content.setText(HadethString);

        return view;

    }
}
