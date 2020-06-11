package com.example.myapplication;

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

import androidx.annotation.Nullable;

/**
 * @author yhc
 */
public class FillView extends View {

    private int mStartColor, mEndColor;
    private Paint mPaint;
    private int max, progress;
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

        mPath = new Path();
        mWaveHeight = L.dp2px(getContext(), 7);
    }

    public int mWaveHeight;


    private Path mPath;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        L.i("width:"+getMeasuredWidth()+",height:"+getMeasuredHeight());

        float percent = (float) progress / max;
        L.i("percent:" + percent + ",progress:" + progress + ",max:" + max);
        percent = 1f - percent;


        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);

        LinearGradient backGradient = new LinearGradient(getMeasuredWidth() / 2, getMeasuredHeight() * percent,
                getMeasuredWidth() / 2, getMeasuredHeight(),
                new int[]{mStartColor, mEndColor},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(backGradient);

        Rect src = new Rect(0, (int) (bitmapFillHeart.getHeight() * percent), bitmapFillHeart.getWidth(), bitmapFillHeart.getHeight());
        RectF rectF = new RectF(0, getMeasuredHeight() * percent, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawBitmap(bitmapFillHeart, src, rectF, mPaint);

        mPaint.setShader(null);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        if (progress == max) {
            canvas.drawRect(0, getMeasuredHeight() * percent, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        } else {
            mPath.reset();
            mPath.moveTo(0, getMeasuredHeight() * percent + mWaveHeight);
            mPath.quadTo(getMeasuredWidth() / 4,
                    getMeasuredHeight() * percent + mWaveHeight + mWaveHeight,
                    getMeasuredWidth() / 2,
                    getMeasuredHeight() * percent + mWaveHeight);
            mPath.quadTo(getMeasuredWidth() / 4 * 3,
                    getMeasuredHeight() * percent,
                    getMeasuredWidth(),
                    getMeasuredHeight() * percent + mWaveHeight);
            mPath.lineTo(getMeasuredWidth(), getMeasuredHeight());
            mPath.lineTo(0, getMeasuredHeight());
            mPath.lineTo(0, mWaveHeight + getMeasuredHeight() * percent);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    public int getmWaveHeight() {
        return mWaveHeight;
    }

    public void setmWaveHeight(int mWaveHeight) {
        this.mWaveHeight = mWaveHeight;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setHeartColor(int startColor, int endColor, int id) {
        mStartColor = startColor;
        mEndColor = endColor;
        bitmapFillHeart = BitmapFactory.decodeResource(getResources(), id);
        invalidate();
    }
}
