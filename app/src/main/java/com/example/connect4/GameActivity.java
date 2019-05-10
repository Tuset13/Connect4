package com.example.connect4;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.connect4.logic_code.Game;
import com.example.connect4.logic_code.Position;

public class GameActivity extends Activity implements AdapterView.OnItemClickListener {

    private ImageAdapter table;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        game = new Game(6,4);
        GridView gridView = findViewById(R.id.grid_view);
        table = new ImageAdapter(this);


        table.setGrid(6);
        gridView.setAdapter(table);
        gridView.setNumColumns(6);  //extreure del intent
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int col = position % 6;
        Position pos = game.drop(col);

        table.setChip(R.drawable.redchip, pos.getRow(), pos.getColumn());
        table.notifyDataSetChanged();
        if(game.checkForFinish()) finish();


        col = game.playOpponent();
        pos = game.drop(col);
        table.setChip(R.drawable.greenchip, pos.getRow(), pos.getColumn());
        table.notifyDataSetChanged();
        if(game.checkForFinish()) finish();
    }
}
