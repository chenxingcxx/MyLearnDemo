package com.cxx.learndemo.mylearndemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;

import androidx.annotation.Nullable;

public class SecondScreenScreenService extends Service {

    private static final String TAG = SecondScreenScreenService.class.getSimpleName();

    private SecondPresentation secondPresentation;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
        initPresentation();
    }

    private void initPresentation() {
        DisplayManager displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        if (displayManager == null) {
            return;
        }

        Display[] presentationDisplays = displayManager.getDisplays();
        if (presentationDisplays.length > 1) {
            Display display = presentationDisplays[1];
            secondPresentation = new SecondPresentation(this, display);
            secondPresentation.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
        if (secondPresentation != null) {
            secondPresentation.dismiss();
            secondPresentation = null;
            Log.e(TAG, "secondPresentationon dismiss");
        }
    }
}
