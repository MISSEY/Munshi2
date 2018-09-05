package com.example.saurabh.munshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class guide2 extends AppCompatActivity {

    public Button Connect;

    public void init() {
        Connect = (Button) findViewById(R.id.textView3);
        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(guide2.this, LoginActivity.class);
                startActivity(toy);
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide2);
        init();
    }
}
