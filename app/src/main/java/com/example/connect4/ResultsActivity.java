package com.example.connect4;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.connect4.DDBB.PartidasSQLiteHelper;
import com.example.connect4.Game.GameActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResultsActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Button email = findViewById(R.id.email_button);
        Button newGame = findViewById(R.id.newGame_button);
        Button exit = findViewById(R.id.exit_button);
        EditText edthour = findViewById(R.id.editText);
        EditText edtlog = findViewById(R.id.editText1);
        EditText edtMail = findViewById(R.id.editText2);

        email.setOnClickListener(this);
        newGame.setOnClickListener(this);
        exit.setOnClickListener(this);

        //Agafar valors importants
        Intent intent = getIntent();
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String Alias = mySharedPreferences.getString(getString(R.string.Alias), "P1");
        String Size = mySharedPreferences.getString(getString(R.string.Grill),"7");
        String Status = intent.getStringExtra("statuskey");
        int usedTime = intent.getIntExtra("usedTime", 0);
        boolean timeControl = mySharedPreferences.getBoolean(getString(R.string.Time),false);
        Date date = new Date();
        String Log = Logbuilder(Alias, Size, Status, usedTime);//Construeix el String del LOG

        edtlog.setText(Log);
        edthour.setText(date.toString());
        edtMail.requestFocus();

        //BASE DE DADES
        PartidasSQLiteHelper usdbh = new PartidasSQLiteHelper(this, "Partidas",null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null)
           instertinDB(db, Alias, Size, timeControl, Status, date, usedTime);
    }

    private void instertinDB(SQLiteDatabase db, String alias, String size, boolean timeControl, String status, Date date, int usedTime) {
        ContentValues newRegister = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("es", "ES"));

        newRegister.put("alias", alias); //Afegim Alias
        newRegister.put("date", dateFormat.format(date));//Afegim Data
        newRegister.put("grillSize", size);//Afegim Size
        newRegister.put("timeControl", timeControl);//Afegim flag de temps
        newRegister.put("usedTime", usedTime);//Temps durada partida
        newRegister.put("result", status);//Afegim Status

        db.insert("Partidas", null, newRegister);
    }

    private String Logbuilder(String alias, String size, String status, int usedtime) {

        return alias + " | Mida Greaella: "+ size + " | Temps total: "+ usedtime+"s | " + status;
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
