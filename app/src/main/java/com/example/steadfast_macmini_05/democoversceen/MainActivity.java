package com.example.steadfast_macmini_05.democoversceen;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {
    public static final int PHONE_PERMISSIONS_REQ_CODE = 1000;
    private ComponentName componentOverlay;
    private ComponentName componentPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        componentOverlay = new ComponentName(this, OverlayReceiver.class);
        componentPhone = new ComponentName(this, PhoneStateReceiver.class);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1 && !checkPermission()) {
            requestPermission();
        }
        launchOverlayService();
    }

    //start service
    private void launchOverlayService() {
        Intent intent = new Intent(MainActivity.this, OverlayService.class);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //if user allow permission: enable receiver
        // disable receiver and finish app if not allow
        if (PHONE_PERMISSIONS_REQ_CODE == requestCode) {
            if (grantResults.length > 0
                    && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                enableReceiver();
            } else {
                disableReceiver();
                finish();
            }
        }
    }

    //send request permission for camera(flash)
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.READ_PHONE_STATE
                }, PHONE_PERMISSIONS_REQ_CODE);

    }

    private boolean checkPermission() {
        int permissionResult = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_PHONE_STATE);
        return permissionResult == PackageManager.PERMISSION_GRANTED;
    }
    //disable broadcast receiver
    private void disableReceiver() {
        getPackageManager().setComponentEnabledSetting(componentPhone, PackageManager
                        .COMPONENT_ENABLED_STATE_DISABLED
                , PackageManager.DONT_KILL_APP);
        getPackageManager().setComponentEnabledSetting(componentOverlay, PackageManager
                        .COMPONENT_ENABLED_STATE_DISABLED
                , PackageManager.DONT_KILL_APP);
    }

    //enable broadcast receiver
    private void enableReceiver() {
        getPackageManager().setComponentEnabledSetting(componentOverlay, PackageManager
                        .COMPONENT_ENABLED_STATE_ENABLED
                , PackageManager.DONT_KILL_APP);
        getPackageManager().setComponentEnabledSetting(componentPhone, PackageManager
                        .COMPONENT_ENABLED_STATE_ENABLED
                , PackageManager.DONT_KILL_APP);
    }
}
