package com.example.connect4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResultatsActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultats);

        Button btn1 = findViewById(R.id.email_button);
        Button btn2 = findViewById(R.id.newGame_button);
        Button btn3 = findViewById(R.id.exit_button);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        EditText edtMail = findViewById(R.id.editText2);

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
                Intent intent1 = new Intent(ResultatsActivity.this, ConfiguracioInicial.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.exit_button:
                finish();
                break;
        }
    }
}
