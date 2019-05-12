package com.example.connect4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    private Bundle data = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        TextView text = findViewById(R.id.clock);

        Intent intent = getIntent();
        size = intent.getIntExtra("graellakey", -1);
        time = intent.getBooleanExtra("tempskey", false);
        data.putInt("midakey", size);
        data.putString("aliaskey", intent.getStringExtra("aliaskey"));

        game = new Game(size, 4);
        text.setText(game.getGameTime() + " seconds");
        GridView gridView = findViewById(R.id.grid_view);
        table = new ImageAdapter(this);

        table.setGrid(size);
        gridView.setAdapter(table);
        gridView.setNumColumns(size);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int col = position % size;
        Position pos = game.drop(col);
        TextView text = findViewById(R.id.clock);

        table.setChip(R.drawable.redchip, pos.getRow(), pos.getColumn());
        table.notifyDataSetChanged();
        if (time)
            game.manageTime();

        long temps = new Date().getTime();
        text.setText(Math.abs(((temps - game.getStartTime()) / 1000) - game.getGameTime()) + " seconds");

        if (game.checkForFinish()) {
            Intent next = new Intent(GameActivity.this, ResultsActivity.class);
            if (game.getStatus() == Status.PLAYER1_WINS) data.putString("statuskey", "HAS GUANYAT");
            if (game.getStatus() == Status.DRAW) data.putString("statuskey", "HAS EMPATAT");
            next.putExtras(data);
            startActivity(next);
        } else {

            col = game.playOpponent();
            pos = game.drop(col);

            //JUGARA LA MAQUINA

            table.setChip(R.drawable.greenchip, pos.getRow(), pos.getColumn());
            table.notifyDataSetChanged();
            if (time)
                game.manageTime();

            if (game.checkForFinish()) {
                Intent next = new Intent(GameActivity.this, ResultsActivity.class);
                if (game.getStatus() == Status.PLAYER2_WINS)
                    data.putString("statuskey", "HAS PERDUT");
                if (game.getStatus() == Status.DRAW) data.putString("statuskey", "HAS EMPATAT");
                next.putExtras(data);
                startActivity(next);
            }
        }
    }
}
