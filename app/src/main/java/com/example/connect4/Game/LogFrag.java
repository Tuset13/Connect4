package com.example.connect4.Game;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.example.connect4.Logic.Game;
import com.example.connect4.Logic.Position;
import com.example.connect4.R;
import java.util.ArrayList;

public class LogFrag extends Fragment {

    View view;
    AdapterItem adapter;
    ArrayList<Item> items;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_log, container, false);

        buildLogTitle();
        buildLog(savedInstanceState);

        return view;
    }


    private void buildLogTitle() {
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


    private void buildLog(Bundle savedInstanceState) {

        if(savedInstanceState != null && (savedInstanceState.getSerializable("adapter") != null)){
            items = (ArrayList<Item>) savedInstanceState.getSerializable("itemArray");
            adapter = (AdapterItem) savedInstanceState.getSerializable("adapter");
        } else {
            items = new ArrayList<Item>();
            adapter = new AdapterItem(getActivity(), items);
        }

        ListView listview =  view.findViewById(R.id.milista);
        listview.setAdapter(adapter);
    }


    public void showPosition(Position pos){

        String Text1 = ("Casella Ocupada:    (" + (pos.getColumn() + 1) +", "+ (pos.getRow() + 1) + ") \n" );

        Item item = new Item(Text1,"PROVA MES LLARGA NO TINC NIDEA QUE FICAR");
        items.add(item);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable("adapter",  adapter);
        state.putSerializable("itemArray",  items);
    }

}
