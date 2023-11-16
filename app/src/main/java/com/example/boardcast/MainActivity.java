package com.example.boardcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
       /* AirplanModeReciever airplanModeReciever = new AirplanModeReciever();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");

        registerReceiver(airplanModeReciever, intentFilter);*/
        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.Boardcast.LOCAL_BOARDCAST");
               intent.setComponent(new ComponentName("com.example.boardcast",
                       "com.example.boardcast.MyReceiver"));
                sendBroadcast(intent, null);
            }
        });
    }

    class AirplanModeReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"飞行模式", Toast.LENGTH_LONG).show();
        }
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            Network networkInfo= connectivityManager.getActiveNetwork();
            if(networkInfo != null){
                Toast.makeText(context,"network is available",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context,"network is not available",Toast.LENGTH_LONG).show();
            }
        }
    }

}