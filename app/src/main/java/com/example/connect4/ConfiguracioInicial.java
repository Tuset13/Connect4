package com.example.connect4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfiguracioInicial extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracio_inicial);

        Button start = findViewById(R.id.start_button);

        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_button:
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
        }
    }
}
