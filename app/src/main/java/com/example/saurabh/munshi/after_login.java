package com.example.saurabh.munshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import com.google.firebase.auth.FirebaseAuth;


public class after_login extends AppCompatActivity {

    public Button Connect;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public void init() {
        Connect = (Button) findViewById(R.id.connect);
        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(after_login.this, Select.class);
                startActivity(toy);
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        init();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent = new Intent(after_login.this, after_login.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_dashboard:
                        Intent intent1 = new Intent(after_login.this, profile.class);
                        startActivity(intent1);
                        break;

                    case R.id.hello:
                        Intent intent2 = new Intent(after_login.this, list.class);
                        startActivity(intent2);
                        break;
                    case R.id.moving:
                        Intent intent3 = new Intent(after_login.this, notify.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth= FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(mAuth.getCurrentUser()==null){
                    Intent loginIntent=new Intent(after_login.this,before_login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(loginIntent);


                }

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_profile,menu);
        MenuItem item = menu.findItem(R.id.logout);
        // search button linking
       // SearchView searchView = (SearchView) item.getActionView();
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.logout){
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mAuth.signOut();
        Intent toy = new Intent(getApplicationContext(), before_login.class);
        toy.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(toy);
    }
}




