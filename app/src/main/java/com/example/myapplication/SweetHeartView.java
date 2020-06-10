package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class SweetHeartView extends FrameLayout {

    public SweetHeartView(@NonNull Context context) {
        super(context);
        init();
    }

    public SweetHeartView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    public SweetHeartView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    private int max = 100;
    private int progress = 50;
    private int level = 1;
    private FillView fillView;
    private ImageView borderView;

    private int mHeartHeight;
    private int mWaveHeight;

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_sweet_heart,this);
        borderView = findViewById(R.id.iv_border);
        fillView = findViewById(R.id.fill_view);
        fillView.setmWaveLength(L.dp2px(getContext(),100));
        fillView.setmWaveHeight(L.dp2px(getContext(),6));
        FrameLayout.LayoutParams fillLp = (LayoutParams) fillView.getLayoutParams();
        fillLp.width = L.dp2px(getContext(),104);
        fillLp.height = L.dp2px(getContext(),88);
    }
    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SweetHeartView);
        mHeartHeight = (int) ta.getDimension(R.styleable.SweetHeartView_heartHeight, 140);
        float fillHeight = ta.getDimension(R.styleable.SweetHeartView_fillHeight, 140);
        float waveHeight = ta.getDimension(R.styleable.SweetHeartView_waveHeight, 140);
        float waveLength = ta.getDimension(R.styleable.SweetHeartView_waveLength, 140);
        float fillMarginBottom = ta.getDimension(R.styleable.SweetHeartView_fillMarginBottom, 140);
        ta.recycle();
    }

    private void resetRes() {
        Bitmap bt = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_lv0_boder);
        borderView.setImageResource(R.mipmap.ic_lv0_boder);
        ViewGroup.LayoutParams fillLp = getLayoutParams();
        fillLp.height = mHeartHeight;
        int btWidth = bt.getWidth();
        int btHeight = bt.getHeight();
        fillLp.width = (int) (fillLp.height * (float)btWidth/btHeight);
        //大心高度写死140dp 宽度等比算
        switch (level) {
            case 0: {
                fillView.setHeartColor(Color.parseColor("#F5515F"), Color.parseColor("#F140AA"),R.mipmap.ic_heart_fill0);
            }
            break;
            case 1: {
                fillView.setHeartColor(Color.parseColor("#B696E2"), Color.parseColor("#7DE1F9"),R.mipmap.ic_heart_fill0);
            }
            break;
            case 2: {
                fillView.setHeartColor(Color.parseColor("#FFE200"), Color.parseColor("#FFAB00"),R.mipmap.ic_heart_fill0);
            }
            break;
            case 3: {
                fillView.setHeartColor(Color.parseColor("#D631E1"), Color.parseColor("#814FFF"),R.mipmap.ic_heart_fill0);
            }
            break;
        }
        fillView.requestLayout();
        fillView.invalidate();
        requestLayout();
        invalidate();
    }

    private void anim(final int lastProgress,final int targetProgress,int time,boolean upgrade){
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(lastProgress,targetProgress);
        valueAnimator.setDuration(time);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                fillView.setProgress(value);
            }
        });
        if(upgrade){
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    fillView.setMax(max);
                    anim(0,progress,300,false);
                }
            });
        }
        valueAnimator.start();
    }

    public void setProgress(int max,int progress,boolean anim,boolean upgrade) {
        int currentProgress = this.progress;
        int currentMax = this.max;
        this.progress = progress;
        this.max = max;
        if(upgrade){
            //升级 先长满 在从0长到新经验值
            anim(currentProgress,currentMax,300,true);
        }else{
            //当前等级升经验，降经验，初始化都在这里
            fillView.setMax(max);
            if(anim&&this.progress>currentProgress){
                anim(currentProgress,this.progress,300,false);
            }else{
                fillView.setProgress(progress);
            }
        }
    }

    public void setHeartColor(int startColor, int endColor,int id) {
        fillView.setHeartColor(startColor,endColor,R.mipmap.ic_heart_fill0);
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
