package com.cxx.learndemo.mylearndemo;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SecondPresentation secondPresentation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_cccc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.cxx.learndemo.mytest");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "没有安装", Toast.LENGTH_LONG).show();
                }
            }
        });

//        findViewById(R.id.tv_service).setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                return false;
//            }
//        });

        findViewById(R.id.tv_servicess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, SecondScreenScreenService.class));
            }
        });

        findViewById(R.id.tv_stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    stopService(new Intent(MainActivity.this, SecondScreenScreenService.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.tv_aaaaa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayManager displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
                if (displayManager == null) {
                    return;
                }
                Display[] displays = displayManager.getDisplays();
                for (Display d : displays) {
                    Log.d("Display_log", d.toString());
                }
                if (displays.length > 1) {
                    Display display = displays[1];
                    secondPresentation = new SecondPresentation(MainActivity.this, display);
                    secondPresentation.show();
                }
            }
        });

        findViewById(R.id.tv_bbbb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secondPresentation != null) {
                    secondPresentation.dismiss();
                    secondPresentation = null;
                }
            }
        });
    }
}
