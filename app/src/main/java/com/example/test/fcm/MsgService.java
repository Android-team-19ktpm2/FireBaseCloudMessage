package com.example.test.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.test.MainActivity;
import com.example.test.MyApp;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MsgService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        RemoteMessage.Notification notification=message.getNotification();
        if (notification == null) {
            return;
        }

        String title=notification.getTitle();
        String msg=notification.getBody();
        System.out.println(title+"00000000000"+msg+"111111111111111111111111111111111");

        sendNotification(title,msg);
    }

    private void sendNotification(String title, String msg) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, MyApp.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(msg)
                .setContentIntent(pendingIntent);

        Notification notification = notificationBuilder.build();
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null)
            notificationManager.notify(1,notification);
    }
}
