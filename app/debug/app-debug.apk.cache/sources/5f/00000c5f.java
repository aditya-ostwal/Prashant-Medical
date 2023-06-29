package com.daimajia.androidanimations.library.attention;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class WobbleAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        float width = target.getWidth();
        float one = (float) (width / 100.0d);
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "translationX", one * 0.0f, (-25.0f) * one, 20.0f * one, (-15.0f) * one, 10.0f * one, (-5.0f) * one, one * 0.0f, 0.0f), ObjectAnimator.ofFloat(target, Key.ROTATION, 0.0f, -5.0f, 3.0f, -3.0f, 2.0f, -1.0f, 0.0f));
    }
}