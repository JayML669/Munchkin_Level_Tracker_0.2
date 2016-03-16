package com.example.jay.localsavetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    public boolean showToast = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);



}
    @Override
    protected void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView();
    }
    public void toast(String message){
        if (showToast) Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}