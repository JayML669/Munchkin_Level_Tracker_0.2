package com.example.jay.localsavetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by Jay on 2/19/2016.
 */
public class NameEntry extends AppCompatActivity{

    String preferencesFileName = "package com.example.jay.localsavetest.NameEntry.prefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_entry);

        Button ok = (Button) this.findViewById(R.id.button3);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ok(view);

            }
        });



    }
    public void ok(View view){

        TextView n=(TextView) this.findViewById(R.id.editText3);
        SharedPreferences userPrefs = getApplicationContext().getSharedPreferences(preferencesFileName, this.MODE_PRIVATE);
        SharedPreferences.Editor edit = userPrefs.edit();
        edit.putString("Username", n.getText().toString());
        edit.commit();
        Intent i = new Intent(getApplicationContext(),StartingScreen.class);
        startActivity(i);
    }
}
