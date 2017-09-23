package com.example.rasik.firebasedemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.R.id.message;

/**
 * Created by rasik on 15/9/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG="FCM Service";



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG,"From: "+ remoteMessage.getFrom());
        Log.d(TAG,"Notification Message body: "+remoteMessage.getNotification().getBody());



        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        // Check if message contains a notification payload.

        if (remoteMessage.getNotification() != null)
        {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());

        }



    }

    private void sendNotification(String messageBody){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("message", messageBody);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
        sendBroadCast(messageBody);
    }

    private void sendBroadCast(String messageBody) {

        Intent intent = new Intent();
        intent.setAction("com.example.rasik.firebasedemo.NOTIFICATION");
        intent.putExtra("message", messageBody);
        sendBroadcast(intent);
    }

}
