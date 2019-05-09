package com.example.connect4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class GameActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        GridView gridView = findViewById(R.id.grid_view);

        ImageAdapter imgAdapt = new ImageAdapter(this);
        imgAdapt.setGrid(6);

        gridView.setAdapter(imgAdapt);
        gridView.setNumColumns(6);  //extreure del intent
        //gridView.setOnItemClickListener(this);

        //imgAdapt.setImage(R.drawable.red_chip, 3, 4);
    }
}
