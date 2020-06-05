package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author yhc
 */
public class CircleProgressView extends View {

    //进度条起始颜色和终点颜色  进度条颜色是渐变的
    public static final int PROGRESS_START_COLOR = Color.parseColor("#7C77E2");
    public static final int PROGRESS_END_COLOR = Color.parseColor("#A16DD7");

    private Paint mPaintBg,mPaintProgress;
    private int mIntProgress = 97;
    private int mIntMax = 136;
    private RectF rectF;
    private int mStrokeWidth = 10;
    private Matrix mMatrix;

    public CircleProgressView(Context context) {
        super(context);
        init();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaintBg = new Paint();
        mPaintProgress = new Paint();

        mPaintBg.setAntiAlias(true);
        mPaintProgress.setAntiAlias(true);

        mPaintBg.setColor(Color.WHITE);

        mMatrix = new Matrix();
        mMatrix.setRotate(-90, 100, 100);

        mPaintBg.setStyle(Paint.Style.STROKE);
        mPaintProgress.setStyle(Paint.Style.STROKE);

        mPaintBg.setStrokeWidth(mStrokeWidth);
        mPaintProgress.setStrokeWidth(mStrokeWidth);

        rectF = new RectF(0,0,0,0);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int strokeWidth2 = mStrokeWidth/2;
        rectF.set(strokeWidth2,strokeWidth2,w-strokeWidth2,h-strokeWidth2);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectF,0,360,false,mPaintBg);

        float midProgress = (float) mIntProgress/mIntMax;
        int progress = (int) (midProgress * 360);
        SweepGradient sweepGradient = new SweepGradient(getMeasuredWidth()/2,getMeasuredHeight()/2,new int[]{
                PROGRESS_START_COLOR,
                PROGRESS_END_COLOR
        },new float[]{0f,midProgress});
        Matrix matrix = new Matrix();
        matrix.setRotate(-90, getMeasuredWidth()/2,getMeasuredHeight()/2);
        sweepGradient.setLocalMatrix(matrix);
        mPaintProgress.setShader(sweepGradient);

        canvas.drawArc(rectF,-90,progress,false,mPaintProgress);
    }

    public int getProgress() {
        return mIntProgress;
    }

    public void setProgress(int progress) {
        this.mIntProgress = progress;
    }

    public Matrix getMatrix() {
        return mMatrix;
    }

    public void setMatrix(Matrix matrix) {
        this.mMatrix = matrix;
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.mStrokeWidth = strokeWidth;
    }
}
