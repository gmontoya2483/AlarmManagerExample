package com.example.gabriel.alarmmanagerexample.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Gabriel on 09/08/2016.
 */
public class AlarmNotificationReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        String ToastText="No message !!!";

       if (intent.hasExtra("MESSAGE")){
            ToastText=intent.getStringExtra("MESSAGE");

        }

        Toast.makeText(context,"Mensaje: "+ToastText,Toast.LENGTH_LONG).show();



    }
}
