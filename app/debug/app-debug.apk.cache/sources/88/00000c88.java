package com.daimajia.androidanimations.library.specials.in;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

/* loaded from: classes.dex */
public class DropOutAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    protected void prepare(View target) {
        int distance = target.getTop() + target.getHeight();
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0.0f, 1.0f), Glider.glide(Skill.BounceEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(target, "translationY", -distance, 0.0f)));
    }
}