package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.Nullable;

public class AddSweetHeartView extends FrameLayout {

    private Interpolator line = new LinearInterpolator();//线性
    private Interpolator acc = new AccelerateInterpolator();//加速
    private Interpolator dce = new DecelerateInterpolator();//减速
    private Interpolator accdec = new AccelerateDecelerateInterpolator();//先加速后减速
    private Interpolator[] interpolators;
    private int[] resArr;
    private PointF endPointF;
    private int offset;

    public AddSweetHeartView(Context context) {
        super(context);
        init();
    }

    public AddSweetHeartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AddSweetHeartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        interpolators = new Interpolator[]{line, acc, dce, accdec};
        resArr = new int[]{R.mipmap.icon_heart_purple, R.mipmap.icon_heart_red, R.mipmap.icon_heart_yellow};
        endPointF = new PointF();
        offset = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_heart_purple).getWidth() / 2;
    }

    public void add() {
        ArrayList<ImageView> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            ImageView iv1 = new ImageView(getContext());
            iv1.setImageResource(resArr[new Random().nextInt(resArr.length)]);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            list.add(iv1);
            addView(iv1, lp);
        }

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        for (int i = 0; i < list.size(); ++i) {
            list.get(i).setX(i * (width / 5) + new Random().nextInt(width / 5));
            list.get(i).setY(new Random().nextInt(height));
        }

        for (int i = 0; i < list.size(); ++i) {
            Animator set = getAnimator(list.get(i));
            set.addListener(new AnimEndListener(list.get(i)));
            set.start();
        }
    }

    private Animator getAnimator(View target) {
        AnimatorSet set = getEnterAnimtor(target);

        ValueAnimator bezierValueAnimator = getBezierValueAnimator(target);

        AnimatorSet finalSet = new AnimatorSet();
        finalSet.playSequentially(set);
        finalSet.playSequentially(set, bezierValueAnimator);
        finalSet.setInterpolator(interpolators[new Random().nextInt(interpolators.length)]);
        finalSet.setTarget(target);
        return finalSet;
    }

    private ValueAnimator getBezierValueAnimator(final View target) {
        endPointF.y = 0;
        endPointF.x = getMeasuredWidth() - L.dp2px(getContext(), 48) - offset;

        PointF p1 = new PointF();
        PointF p2 = new PointF();
        p1.y = target.getY() / 3;
        p2.y = target.getY() / 3 * 2;
        if (target.getX() < endPointF.x) {
            //左边
            p1.x = (endPointF.x - target.getX()) / 3 + target.getX();
            p2.x = (endPointF.x - target.getX()) / 3 * 2 + target.getX();
        } else {
            //右边
            p1.x = target.getX() - ((target.getX() - endPointF.x) / 3);
            p2.x = target.getX() - ((target.getX() - endPointF.x) / 3 * 2);
        }

        BezierEvaluator evaluator = new BezierEvaluator(p1, p2);
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, new PointF(target.getX(), target.getY()), endPointF);
        animator.setTarget(target);
        animator.setDuration(1500);
        animator.addUpdateListener(new BezierListener(target));
        return animator;
    }

    private AnimatorSet getEnterAnimtor(final View target) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.2f, 1f);
        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(300);
        enter.setInterpolator(new LinearInterpolator());
        enter.playTogether(alpha, scaleX, scaleY);
        enter.setTarget(target);
        return enter;
    }

    private class BezierListener implements ValueAnimator.AnimatorUpdateListener {

        private View target;

        public BezierListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF pointF = (PointF) animation.getAnimatedValue();
            target.setX(pointF.x);
            target.setY(pointF.y);
//            target.setAlpha(1 - animation.getAnimatedFraction());
//            target.setScaleX((2 - animation.getAnimatedFraction()) / 1.5f);
//            target.setScaleY((2 - animation.getAnimatedFraction()) / 1.5f);
        }
    }

    private class AnimEndListener extends AnimatorListenerAdapter {
        private View target;

        public AnimEndListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeView(target);
        }

        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
        }
    }
}
