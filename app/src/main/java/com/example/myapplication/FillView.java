package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
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
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LinearGradient backGradient = new LinearGradient(getMeasuredWidth() / 2, L.dp2px(getContext(),7),
                getMeasuredWidth() / 2, getMeasuredHeight(),
                new int[]{mStartColor, mEndColor},
//                new int[]{Color.GREEN, Color.GREEN},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(backGradient);

        canvas.drawRect(0,getMeasuredHeight()/100*30,getMeasuredWidth(),getMeasuredHeight()/100*84,mPaint);
    }

    public void setHeartColor(int startColor, int endColor) {
        mStartColor = startColor;
        mEndColor = endColor;
    }
}
