package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * @author yhc
 */
public class WaveView extends View {
    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public static final int WAVE_HEIGHT = 20;//二阶贝塞尔曲线控制点和终点落差

    private int mWaveLength;
    //    private int mViewWidth;
//    private int mCenterY;
    private int mWaveCount;
    private int offset;

    private Path mPath;
    private Paint mPaint;
    private ValueAnimator mValueAnimatior;

    private int mStartColor, mEndColor;

    private boolean mIsBottomDrawTriangle;//true为波浪下面拼三角 false为波浪下面拼半圆
    private int current;//当前的档位

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(1);
        mWaveLength = 300;
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 需要计算出屏幕能容纳多少个波形
//        mPath = new Path();
//        mViewWidth = w;
//        mCenterY = h-WAVE_HEIGHT;
//        mWaveCount = (int) Math.round(mViewWidth / mWaveLength + 1.5); // 计算波形的个数
    }

    /**
     * startAngie和sweepAngle递增
     * 波浪宽度递增
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        L.i("w:" + getMeasuredWidth() + ",h:" + getMeasuredHeight());
        mWaveCount = (int) Math.round(getMeasuredWidth() / mWaveLength + 1.5); // 计算波形的个数

        LinearGradient backGradient = new LinearGradient(getMeasuredWidth() / 2, WAVE_HEIGHT,
                getMeasuredWidth() / 2, getMeasuredHeight(),
                new int[]{mStartColor, mEndColor},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(backGradient);

        mPath.reset();
        mPath.moveTo(0, (float) (WAVE_HEIGHT * 1.5));
        if(current<5){
            mPath.lineTo(getMeasuredWidth(), (float) (WAVE_HEIGHT * 1.5));
            mPath.lineTo(getMeasuredWidth()/2,getMeasuredHeight());
            mPath.lineTo(0,(float) (WAVE_HEIGHT * 1.5));
        }else if(current==10||current==9){
            mPath.lineTo(getMeasuredWidth()+20, (float) (WAVE_HEIGHT * 1.5));
            mPath.lineTo(getMeasuredWidth()+20,getMeasuredHeight()/3*2-15);
            mPath.lineTo(getMeasuredWidth()/2,getMeasuredHeight()+10);
            mPath.lineTo(-20,getMeasuredHeight()/3*2-15);
            mPath.lineTo(-20,(float) (WAVE_HEIGHT * 1.5));
        }
        else if(current==8||current==7){
            mPath.lineTo(getMeasuredWidth(), (float) (WAVE_HEIGHT * 1.5));
            mPath.lineTo(getMeasuredWidth()+10,getMeasuredHeight()/3*2-15);
            mPath.lineTo(getMeasuredWidth()/2,getMeasuredHeight());
            mPath.lineTo(0,getMeasuredHeight()/3*2-15);
            mPath.lineTo(-15,(float) (WAVE_HEIGHT * 1.5));
        }
        else if(current==5){
            mPath.lineTo(getMeasuredWidth(), (float) (WAVE_HEIGHT * 1.5));
            mPath.lineTo(getMeasuredWidth()+10,getMeasuredHeight()/3*2-15);
            mPath.lineTo(getMeasuredWidth()/2,getMeasuredHeight());
            mPath.lineTo(0,getMeasuredHeight()/3*2-15);
            mPath.lineTo(-15,(float) (WAVE_HEIGHT * 1.5));
        }
        else{
            mPath.lineTo(getMeasuredWidth(), (float) (WAVE_HEIGHT * 1.5));
            mPath.arcTo(0, -getMeasuredHeight() + (float) (WAVE_HEIGHT * 3), getMeasuredWidth(), getMeasuredHeight(), 0, 180, false);
        }
//        mPath.arcTo(0, -getMeasuredHeight() + (float) (WAVE_HEIGHT * 3)-30, getMeasuredWidth(), getMeasuredHeight(), -20, 200, false);

        canvas.drawPath(mPath, mPaint);

        mPath.moveTo(-mWaveLength, WAVE_HEIGHT + 1);
        for (int i = 0; i < mWaveCount; i++) {
            mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + offset, WAVE_HEIGHT + WAVE_HEIGHT, -mWaveLength / 2 + i * mWaveLength + offset, WAVE_HEIGHT);
            mPath.quadTo(-mWaveLength / 4 + i * mWaveLength + offset, 0, i * mWaveLength + offset, WAVE_HEIGHT);
        }
        mPath.lineTo(getMeasuredWidth(), (float) (WAVE_HEIGHT * 1.5));
        mPath.lineTo(0, (float) (WAVE_HEIGHT * 1.5));
//        mPath.lineTo(getMeasuredWidth()/2, (float) (WAVE_HEIGHT*1.5));
//        mPath.lineTo(0, 0);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mValueAnimatior != null) {
            mValueAnimatior.end();
        }
    }

    public void start() {
        mValueAnimatior = ValueAnimator.ofInt(0, mWaveLength);
        mValueAnimatior.setDuration(1000);
        mValueAnimatior.setInterpolator(new LinearInterpolator());
        mValueAnimatior.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimatior.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimatior.start();
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setHeartColor(int startColor, int endColor) {
        mStartColor = startColor;
        mEndColor = endColor;
    }

    public void setIsBottomDrawTriangle(boolean mIsBottomDrawTriangle) {
        this.mIsBottomDrawTriangle = mIsBottomDrawTriangle;
    }
}
