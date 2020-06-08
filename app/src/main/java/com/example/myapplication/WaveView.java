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

    public  int waveHeight = 20;

    private int mWaveLength;
    private int mWaveCount;
    private int offset;

    private Path mPath;
    private Paint mPaint;
    private ValueAnimator mValueAnimatior;

    private int mStartColor, mEndColor;

    private int current;//当前的档位

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(1);
        mWaveLength = L.dp2px(getContext(),100);
        mPath = new Path();
        waveHeight = L.dp2px(getContext(),7);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        L.i("w:" + getMeasuredWidth() + ",h:" + getMeasuredHeight());
        mWaveCount = (int) Math.round(getMeasuredWidth() / mWaveLength + 1.5); // 计算波形的个数

        LinearGradient backGradient = new LinearGradient(getMeasuredWidth() / 2, waveHeight,
                getMeasuredWidth() / 2, getMeasuredHeight(),
                new int[]{mStartColor, mEndColor},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(backGradient);

        mPath.reset();
        mPath.moveTo(0, (float) (waveHeight * 1.5));
        if (current < 6) {
            mPath.lineTo(getMeasuredWidth(), (float) (waveHeight * 1.5));
            mPath.lineTo(getMeasuredWidth() / 2, getMeasuredHeight() + getMeasuredHeight() / 57 * 16);
            mPath.lineTo(0, (float) (waveHeight * 1.5));
        } else if (current == 10) {//258*240
            mPath.lineTo(getMeasuredWidth() + getMeasuredWidth() / 12, (float) (waveHeight * 1.5));
            mPath.lineTo(getMeasuredWidth() + getMeasuredWidth() / 12, getMeasuredHeight() / 3 * 2 - 15);
            mPath.lineTo(getMeasuredWidth() / 2, getMeasuredHeight() + getMeasuredHeight() / 24);
            mPath.lineTo(-getMeasuredWidth() / 12, getMeasuredHeight() / 3 * 2 - getMeasuredHeight() / 30);
            mPath.lineTo(-getMeasuredWidth() / 12, (float) (waveHeight * 1.5));
        } else if (current == 9) {//285*216
            mPath.lineTo(getMeasuredWidth(), (float) (waveHeight * 1.5));
            mPath.lineTo(getMeasuredWidth(), getMeasuredHeight() / 10 * 5);
            mPath.lineTo(getMeasuredWidth() / 2, getMeasuredHeight() + getMeasuredHeight() / 7);
            mPath.lineTo(0, getMeasuredHeight() / 10 * 5);
            mPath.lineTo(0, (float) (waveHeight * 1.5));
        } else if (current == 8) {//285*192
            mPath.lineTo(getMeasuredWidth(), (float) (waveHeight * 1.5));
            mPath.lineTo(getMeasuredWidth(), getMeasuredHeight() / 10 * 4);
            mPath.lineTo(getMeasuredWidth() / 2, getMeasuredHeight() + getMeasuredHeight() / 96 * 13);
            mPath.lineTo(0, getMeasuredHeight() / 10 * 4);
            mPath.lineTo(0, (float) (waveHeight * 1.5));
        } else if (current == 6 || current == 7) {//282*168
            mPath.lineTo(getMeasuredWidth(), (float) (waveHeight * 1.5));
            mPath.lineTo(getMeasuredWidth(), getMeasuredHeight() / 10 * 4);
            mPath.lineTo(getMeasuredWidth() / 2, getMeasuredHeight() + getMeasuredHeight() / 21 * 2);
            mPath.lineTo(0, getMeasuredHeight() / 10 * 4);
            mPath.lineTo(0, (float) (waveHeight * 1.5));
        }
//        else{
//            mPath.lineTo(getMeasuredWidth(), (float) (waveHeight * 1.5));
//            mPath.arcTo(0, -getMeasuredHeight() + (float) (waveHeight * 3), getMeasuredWidth(), getMeasuredHeight(), 0, 180, false);
//        }

        canvas.drawPath(mPath, mPaint);

        mPath.moveTo(-mWaveLength, waveHeight + 1);
        for (int i = 0; i < mWaveCount; i++) {
            mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + offset, waveHeight + waveHeight, -mWaveLength / 2 + i * mWaveLength + offset, waveHeight);
            mPath.quadTo(-mWaveLength / 4 + i * mWaveLength + offset, 0, i * mWaveLength + offset, waveHeight);
        }
        mPath.lineTo(getMeasuredWidth(), (float) (waveHeight * 1.5));
        mPath.lineTo(0, (float) (waveHeight * 1.5));
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

}
