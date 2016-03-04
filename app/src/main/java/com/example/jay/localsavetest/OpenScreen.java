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
public class OpenScreen extends AppCompatActivity {
    String preferencesFileName = "package com.example.jay.localsavetest.NameEntry.prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_screen);

        TextView h=(TextView) this.findViewById(R.id.textView2);
        SharedPreferences userPrefs = this.getApplicationContext().getSharedPreferences(preferencesFileName, this.MODE_PRIVATE);
        String Username = userPrefs.getString("Username", "");
        h.setText(Username+", Welcome"+" To:");

        Button open = (Button) this.findViewById(R.id.button4);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });
    }




}
