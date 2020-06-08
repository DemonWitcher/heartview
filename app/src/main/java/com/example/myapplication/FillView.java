package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
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
        mPaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        L.i("width:"+getMeasuredWidth()+",height:"+getMeasuredHeight());

        float percent = (float) progress/max;
        L.i("percent:"+percent+",progress:"+progress+",max:"+max);
        percent = 1f-percent;

        LinearGradient backGradient = new LinearGradient(getMeasuredWidth() / 2, getMeasuredHeight()*percent,
                getMeasuredWidth() / 2, getMeasuredHeight(),
                new int[]{mStartColor, mEndColor},
//                new int[]{Color.GREEN, Color.GREEN},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(backGradient);

        canvas.drawRect(0, getMeasuredHeight()*percent+1, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        Rect src=new Rect(0, (int) (bitmapFillHeart.getHeight()*percent),bitmapFillHeart.getWidth(),bitmapFillHeart.getHeight());
        RectF rectF = new RectF(0,getMeasuredHeight()*percent,getMeasuredWidth(),getMeasuredHeight());
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapFillHeart,src,rectF,mPaint);
        mPaint.setXfermode(null);
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
}
