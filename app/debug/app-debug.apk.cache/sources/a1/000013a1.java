package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;
import com.google.android.material.internal.MultiViewUpdateListener;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class MultiViewUpdateListener$$ExternalSyntheticLambda0 implements MultiViewUpdateListener.Listener {
    public static final /* synthetic */ MultiViewUpdateListener$$ExternalSyntheticLambda0 INSTANCE = new MultiViewUpdateListener$$ExternalSyntheticLambda0();

    private /* synthetic */ MultiViewUpdateListener$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.material.internal.MultiViewUpdateListener.Listener
    public final void onAnimationUpdate(ValueAnimator valueAnimator, View view) {
        MultiViewUpdateListener.setTranslationY(valueAnimator, view);
    }
}