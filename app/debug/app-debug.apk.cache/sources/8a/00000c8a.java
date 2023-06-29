package com.daimajia.androidanimations.library.specials.out;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

/* loaded from: classes.dex */
public class TakingOffAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(Glider.glide(Skill.QuintEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(target, "scaleX", 1.0f, 1.5f)), Glider.glide(Skill.QuintEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(target, "scaleY", 1.0f, 1.5f)), Glider.glide(Skill.QuintEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(target, "alpha", 1.0f, 0.0f)));
    }
}