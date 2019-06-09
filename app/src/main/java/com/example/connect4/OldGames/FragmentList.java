package com.example.connect4.OldGames;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.connect4.DDBB.PartidasSQLiteHelper;
import com.example.connect4.R;

public class FragmentList extends ListFragment {

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
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        PartidasSQLiteHelper usdbh = new PartidasSQLiteHelper(
                getContext(), "Partidas",null, 1);
        SQLiteDatabase db = usdbh.getReadableDatabase();

        String[] campos = new String[]{"_id", "alias", "date", "result"};
        Cursor c = db.query(
                "Partidas", campos, null, null, null,null,null);

        String[] from = new String[]{"alias", "date", "result"};
        int[] to = new int[]{R.id.gamedata1, R.id.gamedata2, R.id.gamedata3};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getContext(), R.layout.fragment_game_data, c, from, to, 0);

        this.setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        if(listener!=null){
            String stringId = String.valueOf(id);
            listener.onPartidaSeleccionada(stringId);
        }
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
