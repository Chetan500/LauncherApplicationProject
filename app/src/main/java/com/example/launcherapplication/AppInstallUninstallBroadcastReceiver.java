package com.example.launcherapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AppInstallUninstallBroadcastReceiver extends BroadcastReceiver
{
    private int notificationId = 1000;
    private String channelId = "apps.notifications";
    private String description = "App Install/Unistall Status Updates";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        NotificationChannel notificationChannel = null;
        NotificationCompat.Builder builder = null;
        NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notificationChannel = new NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);

            builder = new NotificationCompat.Builder(context.getApplicationContext(), channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("App Package Status")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }
        else
        {
            builder = new NotificationCompat.Builder(context.getApplicationContext())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("App Package Status")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }

        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_PACKAGE_ADDED))
        {
            builder.setContentText(intent.getDataString() + "App Installed");
        }
        else if (intent.getAction().equalsIgnoreCase(Intent.ACTION_PACKAGE_REMOVED))
        {
            builder.setContentText(intent.getDataString() + "App Uninstalled");
        }

        notificationManager.notify(notificationId, builder.build());
    }
}
