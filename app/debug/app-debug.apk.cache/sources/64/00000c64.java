package com.daimajia.androidanimations.library.bouncing_entrances;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class BounceInUpAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "translationY", target.getMeasuredHeight(), -30.0f, 10.0f, 0.0f), ObjectAnimator.ofFloat(target, "alpha", 0.0f, 1.0f, 1.0f, 1.0f));
    }
}