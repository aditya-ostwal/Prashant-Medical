package com.daimajia.androidanimations.library.flippers;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class FlipOutYAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "rotationY", 0.0f, 90.0f), ObjectAnimator.ofFloat(target, "alpha", 1.0f, 0.0f));
    }
}