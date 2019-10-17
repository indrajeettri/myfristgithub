package com.example.hp.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class MyAnimator extends View {
    final int AnimationDur=4000;
    final  int Animationay=1000;
    final int ColorAdjust=5;
    float x,y,rad;
    Paint paint;
    AnimatorSet animatorSet;
    public MyAnimator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        animatorSet=new AnimatorSet();
    }
    void setRadious(float r)
    {
        this.rad=r;
        paint.setColor(Color.GREEN+(int)rad/ColorAdjust);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction())
        { case MotionEvent.ACTION_DOWN:
            x = event.getX();
            y = event.getY();
            if (animatorSet != null && animatorSet.isRunning()) {
                animatorSet.cancel();

            }
            animatorSet.start();
            break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ObjectAnimator growAnimator=ObjectAnimator.ofFloat(this,"Radious",0,getWidth());

        growAnimator.setDuration(AnimationDur);
        growAnimator.setInterpolator(new LinearInterpolator());

        ObjectAnimator shrinkAnmator=ObjectAnimator.ofFloat(this,"Radious",getWidth(),0);
        shrinkAnmator.setDuration(AnimationDur);
        shrinkAnmator.setInterpolator(new DecelerateInterpolator());
        shrinkAnmator.setStartDelay(Animationay);
        animatorSet.play(growAnimator).before(shrinkAnmator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x,y,rad,paint);
    }
}

