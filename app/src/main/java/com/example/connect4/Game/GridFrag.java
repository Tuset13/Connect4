package com.example.connect4.Game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect4.ImageAdapter;
import com.example.connect4.Logic.Game;
import com.example.connect4.Logic.Position;
import com.example.connect4.Logic.Status;
import com.example.connect4.R;
import com.example.connect4.ResultsActivity;

import java.util.Calendar;
import java.util.Date;

public class GridFrag extends Fragment implements AdapterView.OnItemClickListener {

    private ImageAdapter table;
    private Game game;
    private int size;
    private boolean time;
    Date start = new Date();
    Date end;

    View view;
    OnPositionSelectedListener mylistener;

    public interface OnPositionSelectedListener {
        void onPositionSelected(Position pos, Date start, Date end, Long Time, boolean time);
    }

    public void setOnPositionListener(OnPositionSelectedListener listener){
        this.mylistener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_grid, container, false);

        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());

        time = mySharedPreferences.getBoolean(getString(R.string.Time), false);
        size = Integer.parseInt(mySharedPreferences.getString(getString(R.string.Grill),"7"));

        manageGameTable(savedInstanceState);

        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //JUGA EL JUGADOR
        int col = position % size;
        Position pos = game.drop(col);

        table.setChip(R.drawable.redchip, pos.getRow(), pos.getColumn());
        table.notifyDataSetChanged();

        end = new Date();
        Long timerest = ((new Date().getTime() - game.getStartTime()) / 1000) - game.getGameTime();
        if(mylistener!=null)
            mylistener.onPositionSelected(pos, start, end, Math.abs(timerest), time);

        timeControl();
        if (game.checkForFinish()) {
            sendingData(1);
        } else {
            start = new Date();
            //JUGARA LA MAQUINA
            col = game.playOpponent();
            pos = game.drop(col);

            table.setChip(R.drawable.greenchip, pos.getRow(), pos.getColumn());
            table.notifyDataSetChanged();

            end = new Date();
            timerest = ((new Date().getTime() - game.getStartTime()) / 1000) - game.getGameTime();
            if(mylistener!=null)
                mylistener.onPositionSelected(pos, start, end, Math.abs(timerest), time);

            timeControl();
            if (game.checkForFinish())
                sendingData(0);
        }
        start = new Date();
    }

    private void manageGameTable(Bundle savedInstanceState){

        if(savedInstanceState != null && (savedInstanceState.getSerializable("game") != null)){
            game = (Game) savedInstanceState.getSerializable("game");
            table = (ImageAdapter) savedInstanceState.getSerializable("table");
        } else {
            game = new Game(size, 4);
            table = new ImageAdapter(getContext());
            table.setGrid(size);
        }

        GridView gridView = view.findViewById(R.id.grid_view);
        timeControl();

        gridView.setAdapter(table);
        gridView.setNumColumns(size);
        gridView.setOnItemClickListener(this);
    }


    private void timeControl(){
        TextView text = view.findViewById(R.id.clock);

        if (time)
            game.manageTime();

        Long time = ((new Date().getTime() - game.getStartTime()) / 1000) - game.getGameTime();
        if(time>=0)
            text.setText("0 seconds");
        else
            text.setText(Math.abs(time)  + " seconds");
    }


    private void sendingData(int player){
        if (player == 1){
            Bundle data = new Bundle();
            Intent next = new Intent( getActivity(), ResultsActivity.class);
            int time = (int)(new Date().getTime() - game.getStartTime()) / 1000;

            if (game.getStatus() == Status.PLAYER1_WINS) {
                data.putString("statuskey", "HAS GUANYAT");
                Toast.makeText(view.getContext(), getString(R.string.toastwin), Toast.LENGTH_LONG).show();
            }
            if (game.getStatus() == Status.DRAW) {
                data.putString("statuskey", "HAS EMPATAT");
                Toast.makeText(view.getContext(), getString(R.string.toastdraw), Toast.LENGTH_LONG).show();
            }
            if (game.getStatus() == Status.TIMEOVER) {
                data.putString("statuskey", "S'HA ACABAT EL TEMPS, HAS EMPATAT");
                Toast.makeText(view.getContext(), getString(R.string.toasttime), Toast.LENGTH_LONG).show();
            }
            data.putInt("usedTime", time);
            next.putExtras(data);

            startActivity(next);
            getActivity().finish();
        } else {
            Bundle data = new Bundle();
            Intent next = new Intent( getActivity(), ResultsActivity.class);
            int time = (int)(new Date().getTime() - game.getStartTime()) / 1000;

            if (game.getStatus() == Status.PLAYER2_WINS){
                data.putString("statuskey", "HAS PERDUT");
                Toast.makeText(view.getContext(), getString(R.string.toastlost), Toast.LENGTH_SHORT).show();
            }
            if (game.getStatus() == Status.DRAW){
                data.putString("statuskey", "HAS EMPATAT");
                Toast.makeText(view.getContext(), getString(R.string.toastdraw), Toast.LENGTH_SHORT).show();
            }
            if (game.getStatus() == Status.TIMEOVER){
                data.putString("statuskey", "S'HA ACABAT EL TEMPS, HAS EMPATAT");
                Toast.makeText(view.getContext(), getString(R.string.toasttime), Toast.LENGTH_SHORT).show();
            }
            data.putInt("usedTime", time);
            next.putExtras(data);

            startActivity(next);
            getActivity().finish();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable("game",  game);
        state.putSerializable("table", table);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mylistener = (OnPositionSelectedListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnPositionSelectedListener");
        }
    }
}
