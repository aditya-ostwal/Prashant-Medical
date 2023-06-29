package com.shoeARstore;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class StickyScrollView extends ScrollView {
    private static final int DEFAULT_SHADOW_HEIGHT = 10;
    public static final String FLAG_HASTRANSPARANCY = "-hastransparancy";
    public static final String FLAG_NONCONSTANT = "-nonconstant";
    public static final String STICKY_TAG = "sticky";
    private boolean clipToPaddingHasBeenSet;
    private boolean clippingToPadding;
    private View currentlyStickingView;
    private boolean hasNotDoneActionDown;
    private final Runnable invalidateRunnable;
    private Drawable mShadowDrawable;
    private int mShadowHeight;
    private boolean redirectTouchesToStickyView;
    private int stickyViewLeftOffset;
    private float stickyViewTopOffset;
    private ArrayList<View> stickyViews;

    public StickyScrollView(Context context) {
        this(context, null);
    }

    public StickyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 16842880);
    }

    public StickyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.invalidateRunnable = new Runnable() { // from class: com.shoeARstore.StickyScrollView.1
            @Override // java.lang.Runnable
            public void run() {
                if (StickyScrollView.this.currentlyStickingView != null) {
                    StickyScrollView stickyScrollView = StickyScrollView.this;
                    int l = stickyScrollView.getLeftForViewRelativeOnlyChild(stickyScrollView.currentlyStickingView);
                    StickyScrollView stickyScrollView2 = StickyScrollView.this;
                    int t = stickyScrollView2.getBottomForViewRelativeOnlyChild(stickyScrollView2.currentlyStickingView);
                    StickyScrollView stickyScrollView3 = StickyScrollView.this;
                    int r = stickyScrollView3.getRightForViewRelativeOnlyChild(stickyScrollView3.currentlyStickingView);
                    int b = (int) (StickyScrollView.this.getScrollY() + StickyScrollView.this.currentlyStickingView.getHeight() + StickyScrollView.this.stickyViewTopOffset);
                    StickyScrollView.this.invalidate(l, t, r, b);
                }
                StickyScrollView.this.postDelayed(this, 16L);
            }
        };
        this.hasNotDoneActionDown = true;
        setup();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StickyScrollView, defStyle, 0);
        float density = context.getResources().getDisplayMetrics().density;
        int defaultShadowHeightInPix = (int) ((10.0f * density) + 0.5f);
        this.mShadowHeight = a.getDimensionPixelSize(1, defaultShadowHeightInPix);
        int shadowDrawableRes = a.getResourceId(0, -1);
        if (shadowDrawableRes != -1) {
            this.mShadowDrawable = context.getResources().getDrawable(shadowDrawableRes);
        }
        a.recycle();
    }

    public void setShadowHeight(int height) {
        this.mShadowHeight = height;
    }

    public void setup() {
        this.stickyViews = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftForViewRelativeOnlyChild(View v) {
        int left = v.getLeft();
        while (v.getParent() != getChildAt(0)) {
            v = (View) v.getParent();
            left += v.getLeft();
        }
        return left;
    }

    private int getTopForViewRelativeOnlyChild(View v) {
        int top = v.getTop();
        while (v.getParent() != getChildAt(0)) {
            v = (View) v.getParent();
            top += v.getTop();
        }
        return top;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightForViewRelativeOnlyChild(View v) {
        int right = v.getRight();
        while (v.getParent() != getChildAt(0)) {
            v = (View) v.getParent();
            right += v.getRight();
        }
        return right;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomForViewRelativeOnlyChild(View v) {
        int bottom = v.getBottom();
        while (v.getParent() != getChildAt(0)) {
            v = (View) v.getParent();
            bottom += v.getBottom();
        }
        return bottom;
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!this.clipToPaddingHasBeenSet) {
            this.clippingToPadding = true;
        }
        notifyHierarchyChanged();
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean clipToPadding) {
        super.setClipToPadding(clipToPadding);
        this.clippingToPadding = clipToPadding;
        this.clipToPaddingHasBeenSet = true;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public void addView(View child) {
        super.addView(child);
        findStickyViews(child);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public void addView(View child, int index) {
        super.addView(child, index);
        findStickyViews(child);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        findStickyViews(child);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int width, int height) {
        super.addView(child, width, height);
        findStickyViews(child);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        findStickyViews(child);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.currentlyStickingView != null) {
            canvas.save();
            canvas.translate(getPaddingLeft() + this.stickyViewLeftOffset, getScrollY() + this.stickyViewTopOffset + (this.clippingToPadding ? getPaddingTop() : 0));
            canvas.clipRect(0.0f, this.clippingToPadding ? -this.stickyViewTopOffset : 0.0f, getWidth() - this.stickyViewLeftOffset, this.currentlyStickingView.getHeight() + this.mShadowHeight + 1);
            if (this.mShadowDrawable != null) {
                int right = this.currentlyStickingView.getWidth();
                int top = this.currentlyStickingView.getHeight();
                int bottom = this.currentlyStickingView.getHeight() + this.mShadowHeight;
                this.mShadowDrawable.setBounds(0, top, right, bottom);
                this.mShadowDrawable.draw(canvas);
            }
            canvas.clipRect(0.0f, this.clippingToPadding ? -this.stickyViewTopOffset : 0.0f, getWidth(), this.currentlyStickingView.getHeight());
            if (getStringTagForView(this.currentlyStickingView).contains(FLAG_HASTRANSPARANCY)) {
                showView(this.currentlyStickingView);
                this.currentlyStickingView.draw(canvas);
                hideView(this.currentlyStickingView);
            } else {
                this.currentlyStickingView.draw(canvas);
            }
            canvas.restore();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean z = true;
        if (ev.getAction() == 0) {
            this.redirectTouchesToStickyView = true;
        }
        if (this.redirectTouchesToStickyView) {
            boolean z2 = this.currentlyStickingView != null;
            this.redirectTouchesToStickyView = z2;
            if (z2) {
                if (ev.getY() > this.currentlyStickingView.getHeight() + this.stickyViewTopOffset || ev.getX() < getLeftForViewRelativeOnlyChild(this.currentlyStickingView) || ev.getX() > getRightForViewRelativeOnlyChild(this.currentlyStickingView)) {
                    z = false;
                }
                this.redirectTouchesToStickyView = z;
            }
        } else if (this.currentlyStickingView == null) {
            this.redirectTouchesToStickyView = false;
        }
        if (this.redirectTouchesToStickyView) {
            ev.offsetLocation(0.0f, ((getScrollY() + this.stickyViewTopOffset) - getTopForViewRelativeOnlyChild(this.currentlyStickingView)) * (-1.0f));
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.redirectTouchesToStickyView) {
            ev.offsetLocation(0.0f, (getScrollY() + this.stickyViewTopOffset) - getTopForViewRelativeOnlyChild(this.currentlyStickingView));
        }
        if (ev.getAction() == 0) {
            this.hasNotDoneActionDown = false;
        }
        if (this.hasNotDoneActionDown) {
            MotionEvent down = MotionEvent.obtain(ev);
            down.setAction(0);
            super.onTouchEvent(down);
            this.hasNotDoneActionDown = false;
        }
        if (ev.getAction() == 1 || ev.getAction() == 3) {
            this.hasNotDoneActionDown = true;
        }
        return super.onTouchEvent(ev);
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        doTheStickyThing();
    }

    private void doTheStickyThing() {
        float min;
        View viewThatShouldStick = null;
        View approachingView = null;
        Iterator<View> it = this.stickyViews.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            View v = it.next();
            int viewTop = (getTopForViewRelativeOnlyChild(v) - getScrollY()) + (this.clippingToPadding ? 0 : getPaddingTop());
            if (viewTop <= 0) {
                if (viewThatShouldStick != null) {
                    if (viewTop > (getTopForViewRelativeOnlyChild(viewThatShouldStick) - getScrollY()) + (this.clippingToPadding ? 0 : getPaddingTop())) {
                    }
                }
                viewThatShouldStick = v;
            } else {
                if (approachingView != null) {
                    if (viewTop < (getTopForViewRelativeOnlyChild(approachingView) - getScrollY()) + (this.clippingToPadding ? 0 : getPaddingTop())) {
                    }
                }
                approachingView = v;
            }
        }
        if (viewThatShouldStick != null) {
            if (approachingView == null) {
                min = 0.0f;
            } else {
                min = Math.min(0, ((getTopForViewRelativeOnlyChild(approachingView) - getScrollY()) + (this.clippingToPadding ? 0 : getPaddingTop())) - viewThatShouldStick.getHeight());
            }
            this.stickyViewTopOffset = min;
            View view = this.currentlyStickingView;
            if (viewThatShouldStick != view) {
                if (view != null) {
                    stopStickingCurrentlyStickingView();
                }
                this.stickyViewLeftOffset = getLeftForViewRelativeOnlyChild(viewThatShouldStick);
                startStickingView(viewThatShouldStick);
            }
        } else if (this.currentlyStickingView != null) {
            stopStickingCurrentlyStickingView();
        }
    }

    private void startStickingView(View viewThatShouldStick) {
        this.currentlyStickingView = viewThatShouldStick;
        if (getStringTagForView(viewThatShouldStick).contains(FLAG_HASTRANSPARANCY)) {
            hideView(this.currentlyStickingView);
        }
        if (((String) this.currentlyStickingView.getTag()).contains(FLAG_NONCONSTANT)) {
            post(this.invalidateRunnable);
        }
    }

    private void stopStickingCurrentlyStickingView() {
        if (getStringTagForView(this.currentlyStickingView).contains(FLAG_HASTRANSPARANCY)) {
            showView(this.currentlyStickingView);
        }
        this.currentlyStickingView = null;
        removeCallbacks(this.invalidateRunnable);
    }

    public void notifyStickyAttributeChanged() {
        notifyHierarchyChanged();
    }

    private void notifyHierarchyChanged() {
        if (this.currentlyStickingView != null) {
            stopStickingCurrentlyStickingView();
        }
        this.stickyViews.clear();
        findStickyViews(getChildAt(0));
        doTheStickyThing();
        invalidate();
    }

    private void findStickyViews(View v) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                String tag = getStringTagForView(vg.getChildAt(i));
                if (tag != null && tag.contains(STICKY_TAG)) {
                    this.stickyViews.add(vg.getChildAt(i));
                } else if (vg.getChildAt(i) instanceof ViewGroup) {
                    findStickyViews(vg.getChildAt(i));
                }
            }
            return;
        }
        String tag2 = (String) v.getTag();
        if (tag2 != null && tag2.contains(STICKY_TAG)) {
            this.stickyViews.add(v);
        }
    }

    private String getStringTagForView(View v) {
        Object tagObject = v.getTag();
        return String.valueOf(tagObject);
    }

    private void hideView(View v) {
        if (Build.VERSION.SDK_INT >= 11) {
            v.setAlpha(0.0f);
            return;
        }
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(0L);
        anim.setFillAfter(true);
        v.startAnimation(anim);
    }

    private void showView(View v) {
        if (Build.VERSION.SDK_INT >= 11) {
            v.setAlpha(1.0f);
            return;
        }
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(0L);
        anim.setFillAfter(true);
        v.startAnimation(anim);
    }

    public void setShadowDrawable(Drawable drawable) {
    }
}