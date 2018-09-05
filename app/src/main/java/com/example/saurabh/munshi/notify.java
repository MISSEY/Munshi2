package com.example.saurabh.munshi;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class notify extends AppCompatActivity {

   NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
    }

    public void buttonClicked (View view)
    {
        //Build the notifications

        notification.setSmallIcon(R.drawable.ic_add_location_black_24dp);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Here is the title");
        notification.setContentText("Body of your notification");

        Intent intent = new Intent(this,notify.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent , PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID , notification.build());



    }
}
