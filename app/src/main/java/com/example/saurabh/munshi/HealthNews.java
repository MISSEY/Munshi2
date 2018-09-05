package com.example.saurabh.munshi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import Utils.Downloader;

public class HealthNews extends AppCompatActivity {

    final static String urlAddress="http://feeds.bbci.co.uk/news/health/rss.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthnews);


        final ListView lv= (ListView) findViewById(R.id.lv);

                new Downloader(HealthNews.this,urlAddress,lv).execute();
            }


}
