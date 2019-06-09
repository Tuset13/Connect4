package com.example.connect4.OldGames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.connect4.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_old_games);

        FragmentDetail detail = (FragmentDetail)getSupportFragmentManager()
                .findFragmentById(R.id.FrgListOld);

        detail.showGame(getIntent().getStringExtra(getString(R.string.id_key)));
    }
}
