package com.cxx.learndemo.mylearndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SecondPresentation secondPresentation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_cccc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
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
                if (displays.length > 1) {
                    Display display = displays[1];
                    secondPresentation = new SecondPresentation(MainActivity.this, display);
                    secondPresentation.show();
                }
            }
        });
    }
}
