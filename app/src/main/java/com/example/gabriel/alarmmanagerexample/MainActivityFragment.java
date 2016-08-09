package com.example.gabriel.alarmmanagerexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gabriel.alarmmanagerexample.receivers.AlarmNotificationReceiver;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private Button mBtn_SingleAlarm, mBtnRepeatingAlarm,mBtnInexactRepeatingAlarm, mBtnCancelRepeatingAlarm;

    //
    // Define the AlarmManager objects
    //
    private AlarmManager mAlarmManager;
    private Intent mNotificationReceiverIntent;
    private PendingIntent mNotificationReceiverPendingIntent;
    private static final long INITIAL_ALARM_DELAY= 1*60*1000L;





    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot= inflater.inflate(R.layout.fragment_main, container, false);




        //Get the Views from the layout
        mBtn_SingleAlarm=(Button) viewRoot.findViewById(R.id.btn_singleAlarm);
        mBtnRepeatingAlarm=(Button) viewRoot.findViewById(R.id.btn_repeatingAlarm);
        mBtnInexactRepeatingAlarm=(Button) viewRoot.findViewById(R.id.btn_inexactRepeatingAlarm);
        mBtnCancelRepeatingAlarm=(Button) viewRoot.findViewById(R.id.btn_cancelRepeatingAlarm);


        //
        //  Initialize the AlarmManager objects
        //

        // 1 - set the AlarmManager service
        mAlarmManager=(AlarmManager) getActivity().getSystemService (Context.ALARM_SERVICE);


        // 2 - Set the Intent which will be sent through the Pending Intent.
        mNotificationReceiverIntent=new Intent(getActivity(), AlarmNotificationReceiver.class);


        // 3 - Set the Pending Intent
        mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(getActivity(),0,mNotificationReceiverIntent,0);














        // Add the onClick listeners

        mBtn_SingleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ///
                // Put extras at this point doesn¡t work mst be added before defining the pending intent
                mNotificationReceiverIntent.putExtra("MESSAGE","Single Alarm message");



                // AlarmManager.set  trigger the alarm after X amount of time (for example: System.currentTimeMillis()+INITIAL_ALARM_DELAY)
                // set is a one shot alarm
                // RTC_WEKEUP is the type, RTC is how the time is used and WAKEUP means if the application is down it will be waked up ant the intent will be dispatched.
                mAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+INITIAL_ALARM_DELAY,mNotificationReceiverPendingIntent);


                Toast.makeText(getActivity(),"Single alarm was defined",Toast.LENGTH_SHORT).show();


            }
        });

        mBtnRepeatingAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mNotificationReceiverIntent.putExtra("MESSAGE","Repeating Alarm message");


                // AlarmManager.setRepeating  trigger the alarm to start at an speciufic time for example: SystemClock.elapsedRealtime()+INITIAL_ALARM_DELAY)
                // setRepeating means that the alarm will be triggered during intervals of time for example: AlarmManager.INTERVAL_FIFTEEN_MINUTES
                // ELAPSED_REALTIME is the type, it will use REAL TIME. As it has not the WAKEUP, it  means if the application is down it won¡t be waked up the intent will be on hold until the application starts again.

                mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+INITIAL_ALARM_DELAY,AlarmManager.INTERVAL_FIFTEEN_MINUTES, mNotificationReceiverPendingIntent);




                Toast.makeText(getActivity(),"Repeating Alarm was defined",Toast.LENGTH_SHORT).show();



            }
        });




        mBtnInexactRepeatingAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mNotificationReceiverIntent.putExtra("MESSAGE","Inexact Repeating Alarm message");


                // AlarmManager.setInexactRepeating  trigger the alarm to start at an speciufic time for example: SystemClock.elapsedRealtime()+INITIAL_ALARM_DELAY)
                // setInexactRepeating means that the alarm will be triggered during intervals of time for example: AlarmManager.INTERVAL_FIFTEEN_MINUTES, however it won¡t be sharp, or accurate.
                // ELAPSED_REALTIME is the type, it will use REAL TIME. As it has not the WAKEUP, it  means if the application is down it won¡t be waked up the intent will be on hold until the application starts again.

                mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+INITIAL_ALARM_DELAY,AlarmManager.INTERVAL_FIFTEEN_MINUTES, mNotificationReceiverPendingIntent);




                Toast.makeText(getActivity(),"Inexact Repeating Alarm was defined",Toast.LENGTH_SHORT).show();

            }
        });


        mBtnCancelRepeatingAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAlarmManager.cancel(mNotificationReceiverPendingIntent);

                Toast.makeText(getActivity(),"Repeating alarms were cancelled",Toast.LENGTH_SHORT).show();



            }
        });







        return viewRoot;

    }
}
