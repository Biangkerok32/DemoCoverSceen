package com.example.steadfast_macmini_05.democoversceen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by steadfast-macmini-05 on 1/3/18.
 */

public class OverlayReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            showScreenOVerlay(context);
        }
    }

    //show screen overlay
    private void showScreenOVerlay(Context context) {
        WindowManager mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View mView = mInflater.inflate(R.layout.activity_main, null);

        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 0,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                PixelFormat.RGBA_8888);
        mView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        mView.findViewById(R.id.buttonDismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.setVisibility(View.GONE);
            }
        });
        mView.setVisibility(View.VISIBLE);
        mWindowManager.addView(mView, mLayoutParams);
    }
}
