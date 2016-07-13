package com.sharif.PomoDo;

import android.app.*;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by mina on 7/10/16.
 */
public class TimerService extends Service {

    public static final String BROADCAST_TIME = "com.sharif.PomoDo.displayevent";
    Intent myintent;
    CountDownTimer timer;
    int counter = 0;

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        super.onTaskRemoved(rootIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(" ", "onStartCommand ");

        long time = intent.getLongExtra("time",0);
        Intent startIntent = new Intent(this,MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,(int)System.currentTimeMillis() , startIntent , 0);
        Notification notif = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Service")
                .setContentText("time:" + PomodoroFragment.getTime(time))
                .setContentIntent(pIntent)
                .build();


        timer = new CountDownTimer(time , 1000) {

            @Override
            public void onTick(long l) {
                myintent.putExtra("counter",l);
                sendBroadcast(myintent);
                updateNotification(l);
//                Log.d(" ", "onTick " + l);
            }

            @Override
            public void onFinish() {
                myintent.putExtra("counter",0);
                sendBroadcast(myintent);
//                Log.d(" ", "onFinish ");
                stopSelf();

            }
        }.start();

        startForeground(1337,notif);

        return START_STICKY;
    }

    /**
     * This is the method that can be called to update the Notification
     */
    private void updateNotification(long l) {

        Intent startIntent = new Intent(this,MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,(int)System.currentTimeMillis() , startIntent , 0);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Service")
                .setContentText("remaining time: "+PomodoroFragment.getTime(l))
                .setContentIntent(pIntent)
                .build();


        NotificationManager mNotificationManager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1337, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        myintent  = new Intent(BROADCAST_TIME);
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
