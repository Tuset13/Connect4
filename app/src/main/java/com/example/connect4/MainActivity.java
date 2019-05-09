package com.example.connect4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ajuda = findViewById(R.id.ajuda_button);
        Button start = findViewById(R.id.config_button);
        Button sortir = findViewById(R.id.sortir_button);

        sortir.setOnClickListener(this);
        start.setOnClickListener(this);
        ajuda.setOnClickListener(this);

    }

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
            case R.id.sortir_button:
                finish();
                break;
        }
    }
}

