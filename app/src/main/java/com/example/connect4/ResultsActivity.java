package com.example.connect4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class ResultsActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Button email = findViewById(R.id.email_button);
        Button newGame = findViewById(R.id.newGame_button);
        Button exit = findViewById(R.id.exit_button);

        email.setOnClickListener(this);
        newGame.setOnClickListener(this);
        exit.setOnClickListener(this);

        Intent intent = getIntent();
        EditText edthour = findViewById(R.id.editText);
        EditText edtlog = findViewById(R.id.editText1);
        EditText edtMail = findViewById(R.id.editText2);

        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String Alias =mySharedPreferences.getString(getString(R.string.Alias), "No name");
        String Size = mySharedPreferences.getString(getString(R.string.Grill),"6");

        edtlog.setText(Alias +" Mida Graella: " + Size + intent.getStringExtra("statuskey"));
        edthour.setText(new Date().toString());
        edtMail.requestFocus();
    }

    @Override
    public void onClick(View v) {

        EditText edtMail = findViewById(R.id.editText2);
        EditText edtDia = findViewById(R.id.editText);
        EditText edtLog = findViewById(R.id.editText1);

        switch (v.getId()) {
            case R.id.email_button:
                Toast.makeText(this, getString(R.string.sendingEmail), Toast.LENGTH_LONG).show();
                Intent in = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + edtMail.getText().toString()));
                in.putExtra(Intent.EXTRA_SUBJECT, "Log - " + edtDia.getText().toString());
                in.putExtra(Intent.EXTRA_TEXT, edtLog.getText().toString());
                startActivity(in);
                break;
            case R.id.newGame_button:
                Intent intent1 = new Intent(ResultsActivity.this, GameActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.exit_button:
                finish();
                break;
        }
    }
}
