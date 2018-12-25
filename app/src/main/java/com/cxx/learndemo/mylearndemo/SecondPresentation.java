package com.cxx.learndemo.mylearndemo;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class SecondPresentation extends Presentation {

    private final static String TAG = SecondPresentation.class.getSimpleName();

    public SecondPresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    public SecondPresentation(Context outerContext, Display display, int theme) {
        super(outerContext, display, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        setContentView(R.layout.layout_presentation);
    }

    @Override
    public void dismiss() {
        Log.d(TAG, "SecondPresentation_dismiss");
        super.dismiss();
    }
}
