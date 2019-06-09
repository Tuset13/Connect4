package com.example.connect4.OldGames;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.example.connect4.R;

public class OldGamesActivity extends AppCompatActivity implements FragmentList.PartidaListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_games);
        getSupportActionBar().hide();

        FragmentList frgList = (FragmentList) getSupportFragmentManager()
                .findFragmentById(R.id.FrgListOld);
        frgList.setPartidaListener(this);

        Button exit = findViewById(R.id.TornarMenu);

        exit.setOnClickListener(this);
    }

    @Override
    public void onPartidaSeleccionada(String id) {
        FragmentDetail frgDetail = (FragmentDetail)getSupportFragmentManager()
                .findFragmentById(R.id.FrgDetailOld);
        boolean isDetail = (frgDetail != null && frgDetail.isInLayout());

        if(isDetail){
            frgDetail.showGame(id);
        }
        else{
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra(getString(R.string.id_key), id);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TornarMenu:
                finish();
                break;
        }
    }
}
