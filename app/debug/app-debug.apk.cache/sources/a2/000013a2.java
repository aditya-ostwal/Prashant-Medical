package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;
import com.google.android.material.internal.MultiViewUpdateListener;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class MultiViewUpdateListener$$ExternalSyntheticLambda1 implements MultiViewUpdateListener.Listener {
    public static final /* synthetic */ MultiViewUpdateListener$$ExternalSyntheticLambda1 INSTANCE = new MultiViewUpdateListener$$ExternalSyntheticLambda1();

    private /* synthetic */ MultiViewUpdateListener$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.android.material.internal.MultiViewUpdateListener.Listener
    public final void onAnimationUpdate(ValueAnimator valueAnimator, View view) {
        MultiViewUpdateListener.setScale(valueAnimator, view);
    }
}