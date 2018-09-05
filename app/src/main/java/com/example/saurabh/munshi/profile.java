package com.example.saurabh.munshi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent = new Intent(profile.this, after_login.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_dashboard:
                        Intent intent1 = new Intent(profile.this, profile.class);
                        startActivity(intent1);
                        break;
                    case R.id.hello:
                        Intent intent2 = new Intent(profile.this, list.class);
                        startActivity(intent2);
                        break;
                    case R.id.moving:
                        Intent intent3 = new Intent(profile.this, notify.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

    }
}

