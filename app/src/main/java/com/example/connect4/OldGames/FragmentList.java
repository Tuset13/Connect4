package com.example.connect4.OldGames;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.connect4.R;

public class FragmentList extends Fragment {

    private PartidaListener listener;

    public interface PartidaListener{
        void onPartidaSeleccionada(String id);
    }

    public void setPartidaListener(PartidaListener listener){
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list_old_games,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //TODO
        //Funcionalitat ListView
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            listener = (PartidaListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    " must implement OnPartidaSeleccionada()");
        }
    }
}
