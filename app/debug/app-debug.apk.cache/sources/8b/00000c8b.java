package com.daimajia.androidanimations.library.zooming_entrances;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class ZoomInAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "scaleX", 0.45f, 1.0f), ObjectAnimator.ofFloat(target, "scaleY", 0.45f, 1.0f), ObjectAnimator.ofFloat(target, "alpha", 0.0f, 1.0f));
    }
}