package com.example.jay.localsavetest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    public boolean showToast = true;
    boolean hideActionBar = true;
    private String preferencesFileName = "com.example.jay.localsavetest.preferences";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        if (hideActionBar) hideActionBar();
        setContentView(R.layout.activity_base);
    }


    public void hideActionBar(){

        getSupportActionBar().hide();

    }

public void onResume(){
    super.onResume();

}
    @Override
    protected void onStart(){
        super.onStart();
        SharedPreferences backgroundColor=getApplicationContext().getSharedPreferences(preferencesFileName, Settings.MODE_PRIVATE);
        backgroundColor.getString("backgroundColor", "");
        toast(backgroundColor.getString("backgroundColor", "0x00000000"));
        getWindow().getDecorView().setBackgroundColor(Long.decode("0x"+backgroundColor.getString("backgroundColor", "0x00000000")).intValue());
    }
    public void toast(String message){
        if (showToast) Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}