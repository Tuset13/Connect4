package com.example.connect4.OldGames;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.connect4.DDBB.PartidasSQLiteHelper;
import com.example.connect4.R;

public class FragmentDetail extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_detail_old_games,container,false);
        return view;
    }

    public void showGame(String id){
        TextView view = getView().findViewById(R.id.gameInfo);
        String[] gameId = new String[]{id};

        PartidasSQLiteHelper usdbh = new PartidasSQLiteHelper(
                getContext(), "Partidas",null, 1);
        SQLiteDatabase db = usdbh.getReadableDatabase();

        String[] campos = new String[]{"_id", "alias", "date", "grillSize", "timeControl", "usedTime", "result"};
        Cursor c = db.query(
                "Partidas", campos, "_id=?", gameId, null,null,null);
        c.moveToFirst();
        String gameInfo = c.getString(1) + "\n" + c.getString(2) + "\n" + c.getString(3) + "\n" +
                c.getString(4) + "\n" + c.getString(5) + "\n" + c.getString(6);

        view.setText(gameInfo);
    }
}
