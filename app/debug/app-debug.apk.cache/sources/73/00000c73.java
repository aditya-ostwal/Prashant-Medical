package com.daimajia.androidanimations.library.rotating_entrances;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class RotateInAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, Key.ROTATION, -200.0f, 0.0f), ObjectAnimator.ofFloat(target, "alpha", 0.0f, 1.0f));
    }
}