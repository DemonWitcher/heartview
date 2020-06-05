package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author yhc
 */
public class SimpleSweetHeartView extends FrameLayout {


    public SimpleSweetHeartView(@NonNull Context context) {
        super(context);
        init();
    }

    public SimpleSweetHeartView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleSweetHeartView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private WaveView waveView;
    private ImageView boderView;
    private int max = 100;
    private int progress = 50;
    private int level = 1;

    private void init() {
        /**
         * 1.心放大
         * 2.开始波浪线
         * 3.小心从下面飘上来
         * 4.波浪线抬高
         * 5.心缩小回去并提高进度
         */
        LayoutInflater.from(getContext()).inflate(R.layout.view_simple_sweet_heart, this);
        boderView = findViewById(R.id.iv_boder);
        waveView = findViewById(R.id.wave_view);
        waveView.setIsBottomDrawTriangle(true);

        // TODO: 2020/6/5 记得动态修改marginBottom
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        setLevel(0);
//        setProgress(0);
//        setMax(100);
    }

    private void resetRes() {
        ViewGroup.LayoutParams lp = getLayoutParams();
        switch (level) {
            case 0: {
                boderView.setImageResource(R.mipmap.ic_lv0_boder);
                waveView.setHeartColor(Color.parseColor("#F5515F"), Color.parseColor("#F140AA"));
                lp.width = L.dp2px(getContext(), 130);
                lp.height = L.dp2px(getContext(), 130);
            }
            break;
            case 1: {
                boderView.setImageResource(R.mipmap.ic_lv1_boder);
                waveView.setHeartColor(Color.parseColor("#B696E2"), Color.parseColor("#7DE1F9"));
                lp.width = L.dp2px(getContext(), 130);
                lp.height = L.dp2px(getContext(), 130);
            }
            break;
            case 2: {
                boderView.setImageResource(R.mipmap.ic_lv2_boder);
                waveView.setHeartColor(Color.parseColor("#FFE200"), Color.parseColor("#FFAB00"));
                lp.width = L.dp2px(getContext(), 130);
                lp.height = L.dp2px(getContext(), 130);
            }
            break;
            case 3: {
                boderView.setImageResource(R.mipmap.ic_lv3_boder);
                waveView.setHeartColor(Color.parseColor("#D631E1"), Color.parseColor("#814FFF"));
                lp.width = L.dp2px(getContext(), 182);
                lp.height = L.dp2px(getContext(), 130);
            }
            break;
        }

        waveView.requestLayout();
        waveView.invalidate();
        requestLayout();
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        lv1Width = new int[]{1,
//                getMeasuredWidth() * 28 / 130,
//                getMeasuredWidth() * 28 / 130,
//                getMeasuredWidth() * 45 / 130,
//                getMeasuredWidth() * 60 / 130,
//                getMeasuredWidth() * 75 / 130,
//                getMeasuredWidth() * 80 / 130,
//                getMeasuredWidth() * 85 / 130,
//                getMeasuredWidth() * 90 / 130,
//                getMeasuredWidth() * 90 / 130,
//                getMeasuredWidth() * 95 / 130
//        };
//        lv1Height = new int[]{1,
//                getMeasuredHeight() * 14 / 130,
//                getMeasuredHeight() * 20 / 130,
//                getMeasuredHeight() * 26 / 130,
//                getMeasuredHeight() * 32 / 130,
//                getMeasuredHeight() * 40 / 130,
//                getMeasuredHeight() * 48 / 130,
//                getMeasuredHeight() * 56 / 130,
//                getMeasuredHeight() * 64 / 130,
//                getMeasuredHeight() * 72 / 130,
//                getMeasuredHeight() * 80 / 130
//        };
        lv1Width = new int[]{1,
                getMeasuredWidth() * 28 / 130,//0.01
                getMeasuredWidth() * 38 / 130,//0.11
                getMeasuredWidth() * 58 / 130,//0.21
                getMeasuredWidth() * 70 / 130,//0.31
                getMeasuredWidth() * 85 / 130,//0.41
                getMeasuredWidth() * 90 / 130,//0.51
                getMeasuredWidth() * 94 / 130,//0.61
                getMeasuredWidth() * 95 / 130,//0.71
                getMeasuredWidth() * 95 / 130,//0.81
                getMeasuredWidth() * 90 / 130//0.91
        };
        lv1Height = new int[]{1,
                getMeasuredHeight() * 12 / 130,//0.01
                getMeasuredHeight() * 19 / 130,//0.11
                getMeasuredHeight() * 26 / 130,//0.21
                getMeasuredHeight() * 32 / 130,//0.31
                getMeasuredHeight() * 42 / 130,//0.41
                getMeasuredHeight() * 48 / 130,//0.51
                getMeasuredHeight() * 56 / 130,//0.61
                getMeasuredHeight() * 64 / 130,//0.71
                getMeasuredHeight() * 72 / 130,//0.81
                getMeasuredHeight() * 80 / 130//0.91
        };
    }

    int lv1Width[] = new int[11];
    int lv1Height[] = new int[11];

    private void resetWave() {
        ViewGroup.LayoutParams lp = waveView.getLayoutParams();
        float percent = (float) progress / max;
        //满的百分比是 总高度的80/130,

        L.i("resetWave percent: " + percent);
        int current = percent2int(percent);
        resetBottomShape(current);
        lp.width = lv1Width[current];
        lp.height = lv1Height[current];
        L.i("resetWave lp.width:" + lp.width);
        L.i("resetWave lp.height:" + lp.height);
        waveView.requestLayout();
        waveView.invalidate();
        requestLayout();
        invalidate();
    }

    private void resetBottomShape(int current){
        waveView.setCurrent(current);
    }

    private void resetWave2(int w, int h,int current) {
       resetBottomShape(current);
        ViewGroup.LayoutParams lp = waveView.getLayoutParams();
        L.i("resetWave2 w: " + w + ",h:" + h);
        lp.width = w;
        lp.height = h;
        waveView.requestLayout();
        waveView.invalidate();
        requestLayout();
        invalidate();
    }

    private int percent2int(float percent) {
        if (percent == 0f) {
            return 0;
        } else {
            return (int) ((percent + 0.1f) * 10);
        }
    }

    public void test(final int start,final int end) {
        if(start >= end){
            return;
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(0f, 1.0f);
        valueAnimator.setDuration(32);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                test(start+1,end);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int target = start + 1;
                int offsetWidth = lv1Width[target] - lv1Width[start];
                int offsetHeight = lv1Height[target] - lv1Height[start];
                float value = (float) animation.getAnimatedValue();
                int currentWidth = (int) (lv1Width[start] + (offsetWidth * value));
                int currentHeight = (int) (lv1Height[start] + (offsetHeight * value));
                resetWave2(currentWidth, currentHeight,target);
            }
        });
        valueAnimator.start();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        resetWave();
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        resetWave();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        resetRes();
    }
}
