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
        fillView.setmWaveLength(L.dp2px(getContext(),15));
        fillView.setmWaveHeight(L.dp2px(getContext(),1));
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

    public void startAnim(final int lastProgress,final int targetProgress,final boolean upgrade) {
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
                        addProgressAnim(lastProgress,targetProgress,upgrade);
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

    public void addProgressAnim(final int lastProgress,final int targetProgress,final boolean upgrade) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(lastProgress, targetProgress);
        valueAnimator.setDuration(300);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(upgrade){
                    fillView.setMax(max);
                    addProgressAnim(0,progress,false);
                }else{
                    endAnim();
                }
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

    /**
     * @param max max
     * @param progress progress
     * @param anim 是否要动画，升级和本级内涨经验有动画支持
     * @param upgrade 是否升级
     */
    public void setProgress(int max,int progress,boolean anim,boolean upgrade) {
        int currentProgress = this.progress;
        int currentMax = this.max;
        this.progress = progress;
        this.max = max;
        if(upgrade){
            //升级 先长满 在从0长到新经验值
            startAnim(currentProgress,currentMax,true);
        }else{
            //当前等级升经验，降经验，初始化都在这里
            fillView.setMax(max);
            if(anim&&this.progress>currentProgress){
                startAnim(currentProgress,this.progress,false);
            }else{
                fillView.setProgress(progress);
            }
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
