package com.daimajia.androidanimations.library.specials;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

/* loaded from: classes.dex */
public class HingeAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View target) {
        float x = target.getPaddingLeft();
        float y = target.getPaddingTop();
        getAnimatorAgent().playTogether(Glider.glide(Skill.SineEaseInOut, 1300.0f, ObjectAnimator.ofFloat(target, Key.ROTATION, 0.0f, 80.0f, 60.0f, 80.0f, 60.0f, 60.0f)), ObjectAnimator.ofFloat(target, "translationY", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 700.0f), ObjectAnimator.ofFloat(target, "alpha", 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f), ObjectAnimator.ofFloat(target, "pivotX", x, x, x, x, x, x), ObjectAnimator.ofFloat(target, "pivotY", y, y, y, y, y, y));
        setDuration(1300L);
    }
}