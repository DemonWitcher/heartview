package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * @author yhc
 */
public class SweetHeartView extends FrameLayout {

    public SweetHeartView(@NonNull Context context) {
        super(context);
        init();
    }

    public SweetHeartView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SweetHeartView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int max = 100;
    private int progress = 50;
    private int level = 1;
    private FillView fillView;
    private ImageView borderView;

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_sweet_heart,this);
        borderView = findViewById(R.id.iv_border);
        fillView = findViewById(R.id.fill_view);
    }

    private void resetRes() {
        FrameLayout.LayoutParams fillLp = (LayoutParams) fillView.getLayoutParams();
        switch (level) {
            case 0: {
                borderView.setImageResource(R.mipmap.ic_lv0_boder);
                fillView.setHeartColor(Color.parseColor("#F5515F"), Color.parseColor("#F140AA"),R.mipmap.ic_heart_fill0);
                fillLp.height = L.dp2px(getContext(),90);
                fillLp.width = L.dp2px(getContext(),104);
                fillLp.topMargin = 0;
            }
            break;
            case 1: {
                borderView.setImageResource(R.mipmap.ic_lv1_boder);
                fillView.setHeartColor(Color.parseColor("#B696E2"), Color.parseColor("#7DE1F9"),R.mipmap.ic_heart_fill0);
                fillLp.height = L.dp2px(getContext(),82);
                fillLp.width = L.dp2px(getContext(),95);
                fillLp.topMargin = L.dp2px(getContext(),4);
            }
            break;
            case 2: {
                borderView.setImageResource(R.mipmap.ic_lv2_boder);
                fillView.setHeartColor(Color.parseColor("#FFE200"), Color.parseColor("#FFAB00"),R.mipmap.ic_heart_fill0);
                fillLp.height = L.dp2px(getContext(),82);
                fillLp.width = L.dp2px(getContext(),95);
                fillLp.topMargin = L.dp2px(getContext(),4);
            }
            break;
            case 3: {
                borderView.setImageResource(R.mipmap.ic_lv3_boder);
                fillView.setHeartColor(Color.parseColor("#D631E1"), Color.parseColor("#814FFF"),R.mipmap.ic_heart_fill0);
                fillLp.height = L.dp2px(getContext(),80);
                fillLp.width = L.dp2px(getContext(),95);
                fillLp.topMargin = L.dp2px(getContext(),6);
            }
            break;
        }
        fillView.requestLayout();
        fillView.invalidate();
        requestLayout();
        invalidate();
    }

    private void anim(final int lastProgress){
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(lastProgress,progress);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                fillView.setProgress(lastProgress+value);
            }
        });
        valueAnimator.start();
    }

    public void setMax(int max) {
        this.max = max;
        fillView.setMax(max);
    }

    public void setProgress(int progress,boolean anim) {
        int currentProgress = this.progress;
        this.progress = progress;
        if(anim&&this.progress>currentProgress){
            anim(currentProgress);
        }else{
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
