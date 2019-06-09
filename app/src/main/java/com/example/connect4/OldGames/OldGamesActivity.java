package com.example.connect4.OldGames;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


import com.example.connect4.R;

public class OldGamesActivity extends FragmentActivity implements FragmentList.PartidaListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_games);

        FragmentList frgList = (FragmentList) getSupportFragmentManager()
                .findFragmentById(R.id.FrgListOld);
        frgList.setPartidaListener(this);
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
}
