package com.example.saurabh.munshi;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class navigate extends AppCompatActivity {

    private TextView mTextMessage;


    public static void disableShiftMode(BottomNavigationView bottomNavigationView) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);

    }

}
