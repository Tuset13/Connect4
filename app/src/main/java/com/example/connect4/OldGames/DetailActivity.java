package com.example.connect4.OldGames;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.connect4.HelpActivity;
import com.example.connect4.MainActivity;
import com.example.connect4.R;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_old_games);

        FragmentDetail detail = (FragmentDetail)getSupportFragmentManager()
                .findFragmentById(R.id.FrgListOld);

        detail.showGame(getIntent().getStringExtra(getString(R.string.id_key)));

        Button exit = findViewById(R.id.goBack_button);

        exit.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBack_button:
                finish();
                break;
        }
    }
}
