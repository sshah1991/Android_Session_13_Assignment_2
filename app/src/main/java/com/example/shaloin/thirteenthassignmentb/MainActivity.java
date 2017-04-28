package com.example.shaloin.thirteenthassignmentb;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView displayText;
    private Button show;
    MyService myService;
    boolean x=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayText=(TextView)findViewById(R.id.displayID);
        show=(Button)findViewById(R.id.buttonID);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x){
                    displayText.setText(myService.getTimestamp());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,MyService.class);
        startService(intent);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder=(MyService.MyBinder)service;
            myService=myBinder.getService();
            x=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
                x=false;
        }
    };
}
