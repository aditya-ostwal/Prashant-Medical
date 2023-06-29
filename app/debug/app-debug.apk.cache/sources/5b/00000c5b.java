package com.daimajia.androidanimations.library.attention;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class StandUpAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        float x = (((target.getWidth() - target.getPaddingLeft()) - target.getPaddingRight()) / 2) + target.getPaddingLeft();
        float y = target.getHeight() - target.getPaddingBottom();
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "pivotX", x, x, x, x, x), ObjectAnimator.ofFloat(target, "pivotY", y, y, y, y, y), ObjectAnimator.ofFloat(target, "rotationX", 55.0f, -30.0f, 15.0f, -15.0f, 0.0f));
    }
}