package com.daimajia.androidanimations.library.zooming_exits;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class ZoomOutLeftAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1.0f, 1.0f, 0.0f), ObjectAnimator.ofFloat(target, "scaleX", 1.0f, 0.475f, 0.1f), ObjectAnimator.ofFloat(target, "scaleY", 1.0f, 0.475f, 0.1f), ObjectAnimator.ofFloat(target, "translationX", 0.0f, 42.0f, -target.getRight()));
    }
}