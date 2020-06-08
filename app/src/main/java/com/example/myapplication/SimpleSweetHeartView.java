package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
    private FillView fillView;
    private ImageView borderView;
    private int max = 100;
    private int progress = 50;
    private int level = 1;
    private int animTime;

    private void init() {
        /**
         * 1.心放大
         * 2.开始波浪线
         * 3.小心从下面飘上来
         * 4.波浪线抬高
         * 5.心缩小回去并提高进度
         */
        LayoutInflater.from(getContext()).inflate(R.layout.view_simple_sweet_heart, this);
        borderView = findViewById(R.id.iv_border);
        waveView = findViewById(R.id.wave_view);
        fillView = findViewById(R.id.fill_view);

    }

    private void resetRes() {
        ViewGroup.LayoutParams lp = getLayoutParams();
        switch (level) {
            case 0: {
                borderView.setImageResource(R.mipmap.ic_lv0_boder);
                waveView.setHeartColor(Color.parseColor("#F5515F"), Color.parseColor("#F140AA"));
                fillView.setHeartColor(Color.parseColor("#F5515F"), Color.parseColor("#F140AA"));
                lp.width = L.dp2px(getContext(), 130);
                lp.height = L.dp2px(getContext(), 130);
            }
            break;
            case 1: {
                borderView.setImageResource(R.mipmap.ic_lv1_boder);
                waveView.setHeartColor(Color.parseColor("#B696E2"), Color.parseColor("#7DE1F9"));
                fillView.setHeartColor(Color.parseColor("#B696E2"), Color.parseColor("#7DE1F9"));
                lp.width = L.dp2px(getContext(), 130);
                lp.height = L.dp2px(getContext(), 130);
            }
            break;
            case 2: {
                borderView.setImageResource(R.mipmap.ic_lv2_boder);
                waveView.setHeartColor(Color.parseColor("#FFE200"), Color.parseColor("#FFAB00"));
                fillView.setHeartColor(Color.parseColor("#FFE200"), Color.parseColor("#FFAB00"));
                lp.width = L.dp2px(getContext(), 130);
                lp.height = L.dp2px(getContext(), 130);
            }
            break;
            case 3: {
                borderView.setImageResource(R.mipmap.ic_lv3_boder);
                waveView.setHeartColor(Color.parseColor("#D631E1"), Color.parseColor("#814FFF"));
                fillView.setHeartColor(Color.parseColor("#D631E1"), Color.parseColor("#814FFF"));
                lp.width = L.dp2px(getContext(), 182);
                lp.height = L.dp2px(getContext(), 130);
            }
            break;
        }
        resetSize();
        waveView.requestLayout();
        waveView.invalidate();
        requestLayout();
        invalidate();
    }

    private void resetSize(){
        lv1Width = new int[]{1,
                getMeasuredWidth() * 28 / 130,//0.01
                getMeasuredWidth() * 42 / 130,//0.11
                getMeasuredWidth() * 58 / 130,//0.21
                getMeasuredWidth() * 70 / 130,//0.31
                getMeasuredWidth() * 85 / 130,//0.41
                getMeasuredWidth() * 90 / 130,//0.51
                getMeasuredWidth() * 94 / 130,//0.61
                getMeasuredWidth() * 95 / 130,//0.71
                getMeasuredWidth() * 95 / 130,//0.81
                getMeasuredWidth() * 86 / 130//0.91
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
        ViewGroup.LayoutParams lp = fillView.getLayoutParams();
        if(level == 3){
            lp.width = getMeasuredWidth() * 65 / 130;
            lp.height = getMeasuredHeight() * 68 / 130;
        }else if(level==1||level==2){
            lp.width = getMeasuredWidth() * 95 / 130;
            lp.height = getMeasuredHeight() * 75 / 130;
        }
        else{
            lp.width = getMeasuredWidth() * 95 / 130;
            lp.height = getMeasuredHeight() * 80 / 130;
        }

//        LayoutParams waveViewLp = (LayoutParams) waveView.getLayoutParams();
//        waveViewLp.bottomMargin = getMeasuredHeight() * 26 / 130;
//
//        LayoutParams fillViewLp = (LayoutParams) fillView.getLayoutParams();
//        fillViewLp.bottomMargin = getMeasuredHeight() * 26 / 130;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetSize();
    }

    int[] lv1Width = new int[11];
    int[] lv1Height = new int[11];

    private void resetWave() {
        int current = countCurrent();
        resetCurrent(current);
        ViewGroup.LayoutParams lp = waveView.getLayoutParams();
        lp.width = getheartWidthAndOffset(current);
        lp.height = getheartHeightAndOffset(current);
        waveView.requestLayout();
        waveView.invalidate();
        requestLayout();
        invalidate();
    }

    private void resetCurrent(int current) {
        if (current == 10
//                ||current == 9
        ) {
            fillView.setVisibility(View.VISIBLE);
        } else {
            fillView.setVisibility(View.GONE);
        }
        waveView.setCurrent(current);
    }

    private void resetWaveByAnim(int w, int h, int current) {
        resetCurrent(current);
        ViewGroup.LayoutParams lp = waveView.getLayoutParams();
        lp.width = w;
        lp.height = h;
        waveView.requestLayout();
        waveView.invalidate();
        requestLayout();
        invalidate();
    }

    private int countCurrent() {
        float percent = (float) progress / max;
        if (percent == 0f) {
            return 0;
        } else {
            return (int) ((percent + 0.1f) * 10);
        }
    }

    public void anim(final int start, final int end) {
        if (start >= end) {
            return;
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(0f, 1.0f);
        valueAnimator.setDuration(animTime);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                anim(start + 1, end);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int target = start + 1;
                int offsetWidth = getheartWidthAndOffset(target) - getheartWidthAndOffset(start);
                int offsetHeight = getheartHeightAndOffset(target) - getheartHeightAndOffset(start);
                float value = (float) animation.getAnimatedValue();
                int currentWidth = (int) (getheartWidthAndOffset(start) + (offsetWidth * value));
                int currentHeight = (int) (getheartHeightAndOffset(start) + (offsetHeight * value));
                if(start==9){
//                    resetWaveByAnim(currentWidth, currentHeight, start);
                    resetWaveByAnim(getheartWidthAndOffset(start+1), currentHeight, target);
                }else{
                    resetWaveByAnim(currentWidth, currentHeight, target);
                }
            }
        });
        valueAnimator.start();
    }

    private int getheartHeightAndOffset(int index){
        int heightOffset = 0;
        if(level == 1||level == 2){
            heightOffset = getMeasuredHeight() * 4 / 130;
        }else if(level == 3){
            if(countCurrent()==1){
                heightOffset = getMeasuredHeight() * 4 / 130;
            }
//            else if(countCurrent() == 10){
//                heightOffset = getMeasuredHeight() * 12 / 130;
//            }
            else{
                heightOffset = getMeasuredHeight() * 8 / 130;
            }
        }
        return lv1Height[index] - heightOffset;
    }
    private int getheartWidthAndOffset(int index){
        int widthOffset = 0;
         if(level == 3){
             if(countCurrent()==1){
                 widthOffset = getMeasuredHeight() * 26 / 130;
             }else{
                widthOffset = getMeasuredHeight() * 30 / 130;
             }
        }
        return lv1Width[index] - widthOffset;
    }


    public void setMax(int max) {
        this.max = max;
        resetWave();
    }

    public void setProgress(int progress,boolean anim) {
        int current = countCurrent();
        this.progress = progress;
        int newCurrent = countCurrent();
        if(anim&&newCurrent>current){
            animTime = 320/(newCurrent-current);
            anim(current,newCurrent);
        }else{
            resetWave();
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
