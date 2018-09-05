package com.example.saurabh.munshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class before_login extends AppCompatActivity {

    public Button signing;
    public TextView jh;
    public TextView yu;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseUsers;
    private FirebaseAuth mAuth;
    public void init() {
        signing = (Button) findViewById(R.id.login);
        signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(before_login.this, LoginActivity.class);
                startActivity(toy);
            }
        });
    }
 public void hi() {

 }
     public void ui() {
         yu = (TextView) findViewById(R.id.yu);
         yu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent toy = new Intent(before_login.this, guide.class);
                 startActivity(toy);
             }


         });
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);
        init();
       // hi();

        jh = (TextView) findViewById(R.id.hnews);
        jh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(before_login.this, HealthNews.class);
                startActivity(toy);
            }
        });
        ui();
    }



    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()==null){
                    Intent loginIntent=new Intent(before_login.this,after_login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);

                }
            }
        };
    }
}
