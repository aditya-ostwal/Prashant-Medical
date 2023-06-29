package com.daimajia.androidanimations.library.fading_entrances;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class FadeInRightAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0.0f, 1.0f), ObjectAnimator.ofFloat(target, "translationX", target.getWidth() / 4, 0.0f));
    }
}