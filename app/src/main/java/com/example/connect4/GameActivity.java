package com.example.connect4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import com.example.connect4.Logic.Game;
import com.example.connect4.Logic.Position;
import com.example.connect4.Logic.Status;
import java.util.Date;

public class GameActivity extends Activity implements AdapterView.OnItemClickListener {

    private ImageAdapter table;
    private Game game;

    private int size;
    private boolean time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        time = mySharedPreferences.getBoolean(getString(R.string.Time), false);
        size = Integer.parseInt(mySharedPreferences.getString(getString(R.string.Grill),"7"));

        manageGameTable(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //JUGA EL JUGADOR
        int col = position % size;
        Position pos = game.drop(col);

        table.setChip(R.drawable.redchip, pos.getRow(), pos.getColumn());
        table.notifyDataSetChanged();

        timeControl();
        if (game.checkForFinish()) {
            sendingData(1);
        } else {

            //JUGARA LA MAQUINA
            col = game.playOpponent();
            pos = game.drop(col);

            table.setChip(R.drawable.greenchip, pos.getRow(), pos.getColumn());
            table.notifyDataSetChanged();

            timeControl();
            if (game.checkForFinish())
                sendingData(0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable("game",  game);
        state.putSerializable("table",table);
    }

    private void manageGameTable(Bundle savedInstanceState){

        if(savedInstanceState != null && (savedInstanceState.getSerializable("game") != null)){
            game = (Game) savedInstanceState.getSerializable("game");
            table = (ImageAdapter) savedInstanceState.getSerializable("table");
        } else {
            game = new Game(size, 4);
            table = new ImageAdapter(this);
            table.setGrid(size);
        }

        TextView text = findViewById(R.id.clock);
        GridView gridView = findViewById(R.id.grid_view);

        text.setText(game.getGameTime() + " seconds");

        gridView.setAdapter(table);
        gridView.setNumColumns(size);
        gridView.setOnItemClickListener(this);
    }

    private void sendingData(int player){
        if (player == 1){
            Bundle data = new Bundle();
            Intent next = new Intent(GameActivity.this, ResultsActivity.class);
            if (game.getStatus() == Status.PLAYER1_WINS) data.putString("statuskey", "HAS GUANYAT");
            if (game.getStatus() == Status.DRAW) data.putString("statuskey", "HAS EMPATAT");
            if (game.getStatus() == Status.TIMEOVER) data.putString("statuskey", "S'HA ACABAT EL TEMPS, HAS EMPATAT");
            next.putExtras(data);
            startActivity(next);
            finish();
        } else {
            Bundle data = new Bundle();
            Intent next = new Intent(GameActivity.this, ResultsActivity.class);
            if (game.getStatus() == Status.PLAYER2_WINS) data.putString("statuskey", "HAS PERDUT");
            if (game.getStatus() == Status.DRAW) data.putString("statuskey", "HAS EMPATAT");
            if (game.getStatus() == Status.TIMEOVER) data.putString("statuskey", "S'HA ACABAT EL TEMPS, HAS EMPATAT");
            next.putExtras(data);
            startActivity(next);
            finish();
        }
    }

    private void timeControl(){
        TextView text = findViewById(R.id.clock);

        if (time)
            game.manageTime();

        Long time = ((new Date().getTime() - game.getStartTime()) / 1000) - game.getGameTime();
        if(time>=0)
            text.setText("0 seconds");
        else
            text.setText(Math.abs(time)  + " seconds");
    }
}
