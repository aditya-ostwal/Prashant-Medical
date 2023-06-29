package com.shoeARstore;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/* loaded from: classes9.dex */
public class SmallBang extends View {
    private long ANIMATE_DURATION;
    private float DOT_BIG_RADIUS;
    private int DOT_NUMBER;
    private float DOT_SMALL_RADIUS;
    private float MAX_CIRCLE_RADIUS;
    private float MAX_RADIUS;
    private float P1;
    private float P2;
    private float P3;
    private float RING_WIDTH;
    private int centerX;
    private int centerY;
    private Paint circlePaint;
    int[] colors;
    List<Dot> dotList;
    private int[] mExpandInset;
    private SmallBangListener mListener;
    private float progress;

    public SmallBang(Context context) {
        super(context);
        this.colors = new int[]{-2145656, -3306504, -13918734, -5968204, -2058294, -3494714, -3824132, -672746, -860216, -1982834, -3618915};
        this.dotList = new ArrayList();
        this.ANIMATE_DURATION = 1000L;
        this.MAX_RADIUS = 150.0f;
        this.MAX_CIRCLE_RADIUS = 100.0f;
        this.RING_WIDTH = 10.0f;
        this.P1 = 0.15f;
        this.P2 = 0.28f;
        this.P3 = 0.3f;
        this.DOT_NUMBER = 16;
        this.DOT_BIG_RADIUS = 8.0f;
        this.DOT_SMALL_RADIUS = 5.0f;
        this.mExpandInset = new int[2];
        init(null, 0);
    }

    public SmallBang(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.colors = new int[]{-2145656, -3306504, -13918734, -5968204, -2058294, -3494714, -3824132, -672746, -860216, -1982834, -3618915};
        this.dotList = new ArrayList();
        this.ANIMATE_DURATION = 1000L;
        this.MAX_RADIUS = 150.0f;
        this.MAX_CIRCLE_RADIUS = 100.0f;
        this.RING_WIDTH = 10.0f;
        this.P1 = 0.15f;
        this.P2 = 0.28f;
        this.P3 = 0.3f;
        this.DOT_NUMBER = 16;
        this.DOT_BIG_RADIUS = 8.0f;
        this.DOT_SMALL_RADIUS = 5.0f;
        this.mExpandInset = new int[2];
        init(attrs, 0);
    }

    public SmallBang(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.colors = new int[]{-2145656, -3306504, -13918734, -5968204, -2058294, -3494714, -3824132, -672746, -860216, -1982834, -3618915};
        this.dotList = new ArrayList();
        this.ANIMATE_DURATION = 1000L;
        this.MAX_RADIUS = 150.0f;
        this.MAX_CIRCLE_RADIUS = 100.0f;
        this.RING_WIDTH = 10.0f;
        this.P1 = 0.15f;
        this.P2 = 0.28f;
        this.P3 = 0.3f;
        this.DOT_NUMBER = 16;
        this.DOT_BIG_RADIUS = 8.0f;
        this.DOT_SMALL_RADIUS = 5.0f;
        this.mExpandInset = new int[2];
        init(attrs, defStyleAttr);
    }

    public SmallBang(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.colors = new int[]{-2145656, -3306504, -13918734, -5968204, -2058294, -3494714, -3824132, -672746, -860216, -1982834, -3618915};
        this.dotList = new ArrayList();
        this.ANIMATE_DURATION = 1000L;
        this.MAX_RADIUS = 150.0f;
        this.MAX_CIRCLE_RADIUS = 100.0f;
        this.RING_WIDTH = 10.0f;
        this.P1 = 0.15f;
        this.P2 = 0.28f;
        this.P3 = 0.3f;
        this.DOT_NUMBER = 16;
        this.DOT_BIG_RADIUS = 8.0f;
        this.DOT_SMALL_RADIUS = 5.0f;
        this.mExpandInset = new int[2];
        init(attrs, defStyleAttr);
    }

    public static SmallBang attach2Window(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.findViewById(16908290);
        SmallBang smallBang = new SmallBang(activity);
        rootView.addView(smallBang, new ViewGroup.LayoutParams(-1, -1));
        return smallBang;
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        Paint paint = new Paint(1);
        this.circlePaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.circlePaint.setColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public void setmListener(SmallBangListener listener) {
        this.mListener = listener;
    }

    public void setColors(int[] newColors) {
        this.colors = Arrays.copyOf(newColors, newColors.length);
    }

    public void setDotNumber(int dotNumber) {
        this.DOT_NUMBER = dotNumber;
    }

    public void bang(View view, SmallBangListener listener) {
        bang(view, -1.0f, listener);
    }

    public void bang(final View view, float radius, SmallBangListener listener) {
        if (listener != null) {
            setmListener(listener);
            this.mListener.onAnimationStart();
        }
        Rect r = new Rect();
        view.getGlobalVisibleRect(r);
        int[] location = new int[2];
        getLocationOnScreen(location);
        r.offset(-location[0], -location[1]);
        int[] iArr = this.mExpandInset;
        r.inset(-iArr[0], -iArr[1]);
        this.centerX = r.left + (r.width() / 2);
        this.centerY = r.top + (r.height() / 2);
        if (radius != -1.0f) {
            initRadius(radius);
        } else {
            initRadius(Math.max(r.width(), r.height()));
        }
        view.setScaleX(0.1f);
        view.setScaleY(0.1f);
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(((float) this.ANIMATE_DURATION) * 0.5f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.shoeARstore.SmallBang.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                view.setScaleX((animatedFraction * 0.9f) + 0.1f);
                view.setScaleY((0.9f * animatedFraction) + 0.1f);
            }
        });
        animator.setInterpolator(new OvershootInterpolator(2.0f));
        animator.setStartDelay(((float) this.ANIMATE_DURATION) * this.P3);
        animator.start();
        bang();
    }

    private void initRadius(float max_circle_radius) {
        this.MAX_CIRCLE_RADIUS = max_circle_radius;
        this.MAX_RADIUS = 1.1f * max_circle_radius;
        float f = 0.07f * max_circle_radius;
        this.DOT_BIG_RADIUS = f;
        this.DOT_SMALL_RADIUS = f * 0.5f;
    }

    public void bang(View view) {
        bang(view, null);
    }

    private void bang() {
        new ValueAnimator();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(this.ANIMATE_DURATION);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.shoeARstore.SmallBang.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                SmallBang.this.progress = ((Float) animation.getAnimatedValue()).floatValue();
                SmallBang.this.invalidate();
            }
        });
        valueAnimator.start();
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.shoeARstore.SmallBang.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (SmallBang.this.mListener != null) {
                    SmallBang.this.mListener.onAnimationEnd();
                }
            }
        });
        initDots();
    }

    private void initDots() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.DOT_NUMBER * 2; i++) {
            Dot dot = new Dot();
            dot.startColor = this.colors[random.nextInt(99999) % this.colors.length];
            dot.endColor = this.colors[random.nextInt(99999) % this.colors.length];
            this.dotList.add(dot);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f = this.progress;
        float f2 = 1.0f;
        if (f >= 0.0f) {
            float f3 = this.P1;
            if (f <= f3) {
                float progress1 = (1.0f / f3) * f;
                if (progress1 > 1.0f) {
                    progress1 = 1.0f;
                }
                int[] iArr = this.colors;
                int startColor = iArr[0];
                int endColor = iArr[1];
                this.circlePaint.setStyle(Paint.Style.FILL);
                this.circlePaint.setColor(evaluateColor(startColor, endColor, progress1));
                canvas.drawCircle(this.centerX, this.centerY, this.MAX_CIRCLE_RADIUS * progress1, this.circlePaint);
                return;
            }
        }
        float f4 = this.P1;
        if (f > f4) {
            if (f > f4) {
                float f5 = this.P3;
                if (f <= f5) {
                    float progress2 = (f - f4) / (f5 - f4);
                    if (progress2 < 0.0f) {
                        progress2 = 0.0f;
                    }
                    if (progress2 > 1.0f) {
                        progress2 = 1.0f;
                    }
                    this.circlePaint.setStyle(Paint.Style.STROKE);
                    float strokeWidth = this.MAX_CIRCLE_RADIUS * (1.0f - progress2);
                    this.circlePaint.setStrokeWidth(strokeWidth);
                    canvas.drawCircle(this.centerX, this.centerY, (this.MAX_CIRCLE_RADIUS * progress2) + (strokeWidth / 2.0f), this.circlePaint);
                }
            }
            if (this.progress >= this.P2) {
                this.circlePaint.setStyle(Paint.Style.FILL);
                float f6 = this.progress;
                float f7 = this.P2;
                float progress3 = (f6 - f7) / (1.0f - f7);
                float f8 = this.MAX_CIRCLE_RADIUS;
                float r = f8 + ((this.MAX_RADIUS - f8) * progress3);
                int i = 0;
                while (i < this.dotList.size()) {
                    Dot dot = this.dotList.get(i);
                    this.circlePaint.setColor(evaluateColor(dot.startColor, dot.endColor, progress3));
                    float x = ((float) (r * Math.cos(((i * 2) * 3.141592653589793d) / this.DOT_NUMBER))) + this.centerX;
                    float y = ((float) (r * Math.sin(((i * 2) * 3.141592653589793d) / this.DOT_NUMBER))) + this.centerY;
                    canvas.drawCircle(x, y, this.DOT_BIG_RADIUS * (f2 - progress3), this.circlePaint);
                    Dot dot2 = this.dotList.get(i + 1);
                    this.circlePaint.setColor(evaluateColor(dot2.startColor, dot2.endColor, progress3));
                    float x2 = ((float) (r * Math.cos((((i * 2) * 3.141592653589793d) / this.DOT_NUMBER) + 0.2d))) + this.centerX;
                    float y2 = ((float) (r * Math.sin((((i * 2) * 3.141592653589793d) / this.DOT_NUMBER) + 0.2d))) + this.centerY;
                    canvas.drawCircle(x2, y2, this.DOT_SMALL_RADIUS * (1.0f - progress3), this.circlePaint);
                    i += 2;
                    f2 = 1.0f;
                }
            }
        }
    }

    private int evaluateColor(int startValue, int endValue, float fraction) {
        if (fraction <= 0.0f) {
            return startValue;
        }
        if (fraction >= 1.0f) {
            return endValue;
        }
        int startA = (startValue >> 24) & 255;
        int startR = (startValue >> 16) & 255;
        int startG = (startValue >> 8) & 255;
        int startB = startValue & 255;
        int endA = (endValue >> 24) & 255;
        int endR = (endValue >> 16) & 255;
        int endG = (endValue >> 8) & 255;
        int endB = endValue & 255;
        return ((((int) ((endA - startA) * fraction)) + startA) << 24) | ((((int) ((endR - startR) * fraction)) + startR) << 16) | ((((int) ((endG - startG) * fraction)) + startG) << 8) | (((int) ((endB - startB) * fraction)) + startB);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class Dot {
        int endColor;
        int startColor;

        Dot() {
        }
    }
}