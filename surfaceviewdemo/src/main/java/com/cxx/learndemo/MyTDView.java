package com.cxx.learndemo;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.AttributeSet;

import com.cxx.learndemo.shape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by admin on 2017/5/10.
 */

public class MyTDView extends GLSurfaceView {

	final float ANGLE_SPAN = 0.375f;
	SceneRenderer mRenderer;
	RotateThread mThread;

	public MyTDView(Context context) {
		super(context);
		this.setEGLContextClientVersion(2);
		mRenderer=new SceneRenderer();
		this.setRenderer(mRenderer);
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	public MyTDView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private class SceneRenderer implements Renderer {

		Triangle tle;

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			//设置屏幕背景色RGBA
			GLES20.glClearColor(0, 0, 0, 1.0f);
			//创建三角形对象
			tle = new Triangle(MyTDView.this);
			//打开深度检测
			GLES20.glEnable(GLES20.GL_DEPTH_TEST);
			//旋转参数线程中进行更改
//			mThread = new RotateThread();
//			mThread.start();
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			//设置视窗口大小及位置
			GLES20.glViewport(0, 0, width, height);
			//计算GLSurfaceView的宽高比
			float ration = (float) width / height;
			//产生透视投影矩阵
			Matrix.frustumM(Triangle.mProjMatrix, 0, -ration, ration, -1, 1, 1, 10);
			//设置眼睛的位置
			Matrix.setLookAtM(Triangle.mVMatrix, 0, 0, 0, 6f, 0f, 0f, 0f, 0f, 1.0f, 0f);
		}

		@Override
		public void onDrawFrame(GL10 gl) {
			//清楚深度缓冲区
			GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
			//三角形的绘制
			tle.drawSelf();
		}
	}

	//旋转参数在线程中进行更改
	public class RotateThread extends Thread {
		public boolean flag = true;

		@Override
		public void run() {
			super.run();
			while (flag) {
				mRenderer.tle.xAngle = mRenderer.tle.xAngle + ANGLE_SPAN;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
