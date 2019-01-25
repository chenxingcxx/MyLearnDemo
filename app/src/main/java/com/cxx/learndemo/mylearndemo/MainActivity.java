package com.cxx.learndemo.mylearndemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SecondPresentation secondPresentation;
    private ImageView img_ani;

    AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_ani = findViewById(R.id.img_ani);

        animatorSet = new AnimatorSet();
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(img_ani, "rotationY", 45f, 90f, 135f, 180f, 225f, 270f, 315f, 360f);
        //ObjectAnimator rotationX = ObjectAnimator.ofFloat(img_ani, "rotationX", 60f, 180f, 240f, 360f);
        animatorSet.playTogether(rotationY);

        //定义属性动画操作方式rotationY 在Y方向旋转

// 一个周期时长
        animatorSet.setDuration(1500);
//         重复次数
        //oa.setRepeatCount(ObjectAnimator.INFINITE);
//         重复方式
        //oa.setRepeatMode(ObjectAnimator.REVERSE);


        findViewById(R.id.tv_cccc).

                setOnClickListener(new View.OnClickListener() {
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

        findViewById(R.id.tv_service).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startService(new Intent(MainActivity.this, SecondScreenScreenService.class));
                    }
                });

        findViewById(R.id.tv_stop_service).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            stopService(new Intent(MainActivity.this, SecondScreenScreenService.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        findViewById(R.id.tv_aaaaa).

                setOnClickListener(new View.OnClickListener() {
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

        findViewById(R.id.tv_bbbb).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (secondPresentation != null) {
                            secondPresentation.dismiss();
                            secondPresentation = null;
                        }
                    }
                });

        findViewById(R.id.tv_bbbbccc).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        animatorSet.start();
                    }
                });


    }
}
