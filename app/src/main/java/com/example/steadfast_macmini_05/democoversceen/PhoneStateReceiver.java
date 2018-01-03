package com.example.steadfast_macmini_05.democoversceen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by steadfast-macmini-05 on 1/3/18.
 */

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context,"Ringing State ",Toast.LENGTH_SHORT).show();
                //stop service when have incoming call
                context.stopService(new Intent(context, OverlayService.class));
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context,"Idle State",Toast.LENGTH_SHORT).show();
                //restart service when end call
                context.startService(new Intent(context, OverlayService.class));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
