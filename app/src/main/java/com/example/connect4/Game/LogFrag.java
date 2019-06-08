package com.example.connect4.Game;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.connect4.R;

public class LogFrag extends Fragment {

    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_log, container, false);

        buildLog();

        return view;
    }

    private void buildLog() {
        TextView log = view.findViewById(R.id.loger);
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());

        String Alias = mySharedPreferences.getString(getString(R.string.Alias), "P1");
        String Size = mySharedPreferences.getString(getString(R.string.Grill), "7");
        boolean time = mySharedPreferences.getBoolean(getString(R.string.Time), false);

        log.setText("LOG..." + "\n" + "Alias = " + Alias + "; Mida grealla = " + Size + "\n");

        if (time){
            log.append("Control de Temps");
        }else{
            log.append("Sense Control de Temps");
        }
    }

}
