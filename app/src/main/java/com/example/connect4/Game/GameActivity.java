package com.example.connect4.Game;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.connect4.Logic.Position;
import com.example.connect4.R;


public class GameActivity extends AppCompatActivity implements GridFrag.OnPositionSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        getSupportActionBar().hide();

        GridFrag grid = (GridFrag) getSupportFragmentManager().findFragmentById(R.id.game);

        grid.setOnPositionListener(this);
    }


    @Override
    public void onPositionSelected(Position pos) {
        LogFrag log = (LogFrag) getSupportFragmentManager().findFragmentById(R.id.log);
        boolean logexists = (log != null && log.isInLayout());

        if(logexists)
            log.showPosition(pos);
    }
}