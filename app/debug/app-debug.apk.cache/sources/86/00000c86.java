package com.daimajia.androidanimations.library.specials;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class RollInAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0.0f, 1.0f), ObjectAnimator.ofFloat(target, "translationX", -((target.getWidth() - target.getPaddingLeft()) - target.getPaddingRight()), 0.0f), ObjectAnimator.ofFloat(target, Key.ROTATION, -120.0f, 0.0f));
    }
}