package com.example.connect4.OldGames;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.connect4.R;

public class DetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_old_games);

        FragmentDetail detail = (FragmentDetail)getSupportFragmentManager()
                .findFragmentById(R.id.FrgListOld);

        //TODO
        //Realitzar funcio de FragmentDetail
    }
}
