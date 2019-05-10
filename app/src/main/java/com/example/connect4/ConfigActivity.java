package com.example.connect4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ConfigActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button start = findViewById(R.id.start_button);

        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_button:
                EditText alias = findViewById(R.id.editTextAlias);
                RadioGroup graella = findViewById(R.id.editGraella);
                CheckBox temps = findViewById(R.id.time_checkBox);

                int selectedId = graella.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);

                Bundle data = new Bundle();


                data.putString(getString(R.string.aliaskey), alias.getText().toString());
                data.putInt(getString(R.string.graellakey), Integer.parseInt(radioButton.getText().toString()));
                data.putBoolean(getString(R.string.tempskey), temps.isChecked());

                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtras(data);
                startActivity(intent);
                break;
        }
    }
}
