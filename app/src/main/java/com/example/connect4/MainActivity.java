package com.example.connect4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener MainListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ajuda_button:
                        Intent intent = new Intent(MainActivity.this, AjudaActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.config_button:
                        Intent intent1 = new Intent(MainActivity.this, ConfiguracioInicial.class);
                        startActivity(intent1);
                        break;
                    case R.id.surtir_button:
                        finish();
                }
            }
        };
        Button ajuda = findViewById(R.id.ajuda_button);
        Button start = findViewById(R.id.config_button);
        Button surtir = findViewById(R.id.config_button);
        surtir.setOnClickListener(MainListener);
        start.setOnClickListener(MainListener);
        ajuda.setOnClickListener(MainListener);
    }
}

