package com.daimajia.androidanimations.library.attention;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class WaveAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        float x = (((target.getWidth() - target.getPaddingLeft()) - target.getPaddingRight()) / 2) + target.getPaddingLeft();
        float y = target.getHeight() - target.getPaddingBottom();
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, Key.ROTATION, 12.0f, -12.0f, 3.0f, -3.0f, 0.0f), ObjectAnimator.ofFloat(target, "pivotX", x, x, x, x, x), ObjectAnimator.ofFloat(target, "pivotY", y, y, y, y, y));
    }
}