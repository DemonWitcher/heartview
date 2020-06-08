package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AnimSweetHeartView extends FrameLayout {
    public AnimSweetHeartView(@NonNull Context context) {
        super(context);
        init();
    }

    public AnimSweetHeartView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimSweetHeartView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int max = 100;
    private int progress = 50;
    private int level = 1;
    private FillView fillView;
    private ImageView borderView;

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_anim_sweet_heart, this);
        borderView = findViewById(R.id.iv_border);
        fillView = findViewById(R.id.fill_view);
    }

    private void resetRes() {
        switch (level) {
            case 0: {
                borderView.setImageResource(R.mipmap.ic_lv0_heart);
                fillView.setHeartColor(Color.parseColor("#F5515F"), Color.parseColor("#F140AA"), R.mipmap.ic_heart_fill0);
            }
            break;
            case 1: {
                borderView.setImageResource(R.mipmap.ic_lv1_heart);
                fillView.setHeartColor(Color.parseColor("#B696E2"), Color.parseColor("#7DE1F9"), R.mipmap.ic_heart_fill0);
            }
            break;
            case 2: {
                borderView.setImageResource(R.mipmap.ic_lv2_heart);
                fillView.setHeartColor(Color.parseColor("#FFE200"), Color.parseColor("#FFAB00"), R.mipmap.ic_heart_fill0);
            }
            break;
            case 3: {
                borderView.setImageResource(R.mipmap.ic_lv3_heart);
                fillView.setHeartColor(Color.parseColor("#D631E1"), Color.parseColor("#814FFF"), R.mipmap.ic_heart_fill0);
            }
            break;
        }
        fillView.invalidate();
        invalidate();
    }

    public void startAnim(final int lastProgress) {
        setPivotX(getMeasuredWidth());
        setPivotY(getMeasuredHeight()/2);

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(1.0f, 2.0f);
        valueAnimator.setDuration(300);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //放大动画(同时开始飘出心动画)-增长动画-结束动画
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addProgressAnim(lastProgress);
                    }
                },1200);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                setScaleX(value);
                setScaleY(value);
            }
        });
        valueAnimator.start();

    }

    public void endAnim() {
        setPivotX(getMeasuredWidth());
        setPivotY(getMeasuredHeight()/2);

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(2.0f, 1.0f);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                setScaleX(value);
                setScaleY(value);
            }
        });
        valueAnimator.start();
    }

    public void addProgressAnim(final int lastProgress) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(lastProgress, progress);
        valueAnimator.setDuration(300);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                endAnim();
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                fillView.setProgress(value);
            }
        });
        valueAnimator.start();
    }

    public void setMax(int max) {
        this.max = max;
        fillView.setMax(max);
    }

    public void setProgress(int progress, boolean anim) {
        int lastProgress = this.progress;
        this.progress = progress;
        if (anim && this.progress > lastProgress) {
            startAnim(lastProgress);
        } else {
            fillView.setProgress(progress);
        }
    }

    public void setLevel(int level) {
        this.level = level;
        resetRes();
    }

    public int getMax() {
        return max;
    }

    public int getProgress() {
        return progress;
    }

    public int getLevel() {
        return level;
    }
}
