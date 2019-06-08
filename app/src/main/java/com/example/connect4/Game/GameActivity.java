package com.example.connect4.Game;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.connect4.R;


public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        getSupportActionBar().hide();

    }


}
