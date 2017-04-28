package com.example.shaloin.thirteenthassignmentb;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Chronometer;

public class MyService extends Service {
    private IBinder mBinder=new MyBinder();
    private Chronometer chronometer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        chronometer=new Chronometer(this);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    public String getTimestamp(){
        long elapsedMillis= SystemClock.elapsedRealtime() - chronometer.getBase();
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }

    public class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }
}
