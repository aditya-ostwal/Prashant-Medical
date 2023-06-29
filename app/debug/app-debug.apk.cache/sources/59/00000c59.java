package com.daimajia.androidanimations.library.attention;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class RubberBandAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "scaleX", 1.0f, 1.25f, 0.75f, 1.15f, 1.0f), ObjectAnimator.ofFloat(target, "scaleY", 1.0f, 0.75f, 1.25f, 0.85f, 1.0f));
    }
}