package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class HeartView  extends View {
    private int mMeasureWidth;
    private int mWidthMode;
    private int mMeasureHeight;
    private int mHeightMode;
    private Paint paint;

    public HeartView(Context context) {
        super(context);
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();//实例画笔
        paint.setAntiAlias(true);//抗锯齿
        paint.setStrokeWidth(2);//画笔宽度
        paint.setColor(Color.RED);//画笔颜色
        paint.setStyle(Paint.Style.STROKE);//画笔样式
    }

    /**
     * 测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        mHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (mWidthMode == MeasureSpec.AT_MOST && mHeightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);
        } else if (mWidthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, mMeasureHeight);
        } else if (mHeightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mMeasureWidth, 200);
        } else {
            setMeasuredDimension(mMeasureWidth, mMeasureHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();//获取屏幕宽
        int height = getHeight();//获取屏幕高

        /**
         *  绘制心形
         */
        //左半面
        Path path = new Path();
        path.moveTo(width / 2, height / 4);
        path.cubicTo((width * 6) / 7, height / 9, (width * 12) / 13, (height * 2) / 5, width / 2, (height * 7) / 12);
        canvas.drawPath(path, paint);
        //右半面
        Path path2 = new Path();
        path2.moveTo(width / 2, height / 4);
        path2.cubicTo(width / 7, height / 9, width / 13, (height * 2) / 5, width / 2, (height * 7) / 12);
        canvas.drawPath(path2, paint);

    }

}
