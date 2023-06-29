package com.daimajia.androidanimations.library.sliders;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class SlideInUpAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        ViewGroup parent = (ViewGroup) target.getParent();
        int distance = parent.getHeight() - target.getTop();
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0.0f, 1.0f), ObjectAnimator.ofFloat(target, "translationY", distance, 0.0f));
    }
}