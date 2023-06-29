package com.daimajia.androidanimations.library.flippers;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class FlipInYAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "rotationY", 90.0f, -15.0f, 15.0f, 0.0f), ObjectAnimator.ofFloat(target, "alpha", 0.25f, 0.5f, 0.75f, 1.0f));
    }
}