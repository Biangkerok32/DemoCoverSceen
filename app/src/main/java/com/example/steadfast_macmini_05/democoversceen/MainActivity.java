package com.example.steadfast_macmini_05.democoversceen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchOverlayService();
    }

    //start service
    private void launchOverlayService() {
        Intent intent = new Intent(MainActivity.this, OverlayService.class);
        startService(intent);
    }
}
