package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * @author yhc
 */
public class FillView extends View {

    private int mStartColor, mEndColor;
    private Paint mPaint;
    private int max,progress;
    private Bitmap bitmapFillHeart;

    public FillView(Context context) {
        super(context);
        init();
    }

    public FillView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FillView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mWaveLength = L.dp2px(getContext(),100);
        mPath = new Path();
        waveHeight = L.dp2px(getContext(),7);
    }
    public int waveHeight;

    private int mWaveLength;
    private int mWaveCount;
    private int offset;

    private Path mPath;
    private ValueAnimator mValueAnimatior;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float percent = (float) progress/max;
        percent = 1f-percent;

        LinearGradient backGradient = new LinearGradient(getMeasuredWidth() / 2, getMeasuredHeight()*percent,
                getMeasuredWidth() / 2, getMeasuredHeight(),
                new int[]{mStartColor, mEndColor},
//                new int[]{Color.GREEN, Color.GREEN},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(backGradient);


        if(progress == max){
            canvas.drawRect(0, getMeasuredHeight()*percent+1, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        }else{
            mPath.reset();
            mWaveCount = (int) Math.round(getMeasuredWidth() / mWaveLength + 1.5); // 计算波形的个数
            mPath.moveTo(-mWaveLength,  getMeasuredHeight()*percent+1+waveHeight + 1);
            for (int i = 0; i < mWaveCount; i++) {
                mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + offset, waveHeight + waveHeight+ getMeasuredHeight()*percent+1, -mWaveLength / 2 + i * mWaveLength + offset, waveHeight+ getMeasuredHeight()*percent+1);
                mPath.quadTo(-mWaveLength / 4 + i * mWaveLength + offset,  getMeasuredHeight()*percent+1, i * mWaveLength + offset, waveHeight+ getMeasuredHeight()*percent+1);
            }
            mPath.lineTo(getMeasuredWidth(), getMeasuredHeight());
            mPath.lineTo(0, getMeasuredHeight());
            mPath.lineTo(0, (float) (waveHeight * 1.5)+ getMeasuredHeight()*percent+1);
            mPath.close();
            canvas.drawPath(mPath, mPaint);

        }

        Rect src=new Rect(0, (int) (bitmapFillHeart.getHeight()*percent),bitmapFillHeart.getWidth(),bitmapFillHeart.getHeight());
        RectF rectF = new RectF(0,getMeasuredHeight()*percent,getMeasuredWidth(),getMeasuredHeight());
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapFillHeart,src,rectF,mPaint);
        mPaint.setXfermode(null);
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

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setHeartColor(int startColor, int endColor,int id) {
        mStartColor = startColor;
        mEndColor = endColor;
        bitmapFillHeart = BitmapFactory.decodeResource(getResources(),id);
        invalidate();
    }

    public int getmWaveLength() {
        return mWaveLength;
    }

    public void setmWaveLength(int mWaveLength) {
        this.mWaveLength = mWaveLength;
    }

    public int getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(int waveHeight) {
        this.waveHeight = waveHeight;
    }
}
