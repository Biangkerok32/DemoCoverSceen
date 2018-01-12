package com.example.steadfast_macmini_05.democoversceen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OverlayReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            showOverlayActivity(context);
        }
    }

    //show screen overlay
    private void showOverlayActivity(Context context) {
        Intent intent = new Intent(context, OverlayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
