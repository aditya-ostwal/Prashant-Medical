package com.daimajia.androidanimations.library.fading_exits;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class FadeOutAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1.0f, 0.0f));
    }
}