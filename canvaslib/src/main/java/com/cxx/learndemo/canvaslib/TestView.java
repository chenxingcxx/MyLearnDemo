package com.cxx.learndemo.canvaslib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TestView extends View {

    private Context mContext;
    private Paint mPaint;
    private int color = Color.YELLOW;
    private int RECT = 100;
    private int Magin = 30;

    private int viewWith;

    public TestView(Context context) {
        super(context);
        mContext = context;
        initPaint();
    }


    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    //初始化画笔
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        viewWith = getHeight();
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        LinearGradient linearGradient = new LinearGradient(0, 400, viewWith, 400 + RECT, Color.BLACK, Color.YELLOW, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawRect(0, 400, viewWith, 400 + RECT, mPaint);
        mPaint.setShader(null);

        mPaint.setColor(color);
        canvas.drawRect(100, 800, RECT + 100, RECT + 800, mPaint);

        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLUE);
        String str = "嘿嘿的电视剧肯定很爽";
        float textTop = 800 + RECT / 2 + (-mPaint.getFontMetrics().ascent + mPaint.getFontMetrics().descent) / 2 - mPaint.getFontMetrics().descent;
        canvas.drawText(str, 100 + mPaint.measureText(str) / 2 + RECT + Magin, textTop, mPaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //手指按下
                break;
            case MotionEvent.ACTION_MOVE:
                //
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return super.onTouchEvent(event);
    }
}
