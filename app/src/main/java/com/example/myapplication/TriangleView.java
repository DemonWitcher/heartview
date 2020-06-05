package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TriangleView extends View {
    public TriangleView(Context context) {
        super(context);
        init();
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        /**
         * 1.心放大
         * 2.开始波浪线
         * 3.小心从下面飘上来
         * 4.波浪线抬高
         * 5.心缩小回去并提高进度
         */

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(8);
//        mPaint.setColor(Color.GREEN);

        mPath = new Path();
    }
    private Path mPath;
    private Paint mPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.lineTo(getMeasuredWidth(), 0);
        mPath.lineTo(getMeasuredWidth()/2, getMeasuredHeight());
        mPath.lineTo(0, 0);
        LinearGradient backGradient = new LinearGradient(getMeasuredWidth()/2, 0, getMeasuredWidth()/2, getMeasuredHeight(), new int[]{Color.RED, Color.GREEN }, null, Shader.TileMode.CLAMP);
        mPaint.setShader(backGradient);
        canvas.drawPath(mPath, mPaint);
    }
}
