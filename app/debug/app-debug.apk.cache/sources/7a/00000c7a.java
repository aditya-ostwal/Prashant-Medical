package com.daimajia.androidanimations.library.rotating_exits;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class RotateOutDownRightAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        float x = target.getWidth() - target.getPaddingRight();
        float y = target.getHeight() - target.getPaddingBottom();
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1.0f, 0.0f), ObjectAnimator.ofFloat(target, Key.ROTATION, 0.0f, -90.0f), ObjectAnimator.ofFloat(target, "pivotX", x, x), ObjectAnimator.ofFloat(target, "pivotY", y, y));
    }
}