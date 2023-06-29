package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0006zaa> zaa;

    public zaa(Activity activity) {
        this(C0006zaa.zab(activity));
    }

    private zaa(C0006zaa c0006zaa) {
        this.zaa = new WeakReference<>(c0006zaa);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0006zaa c0006zaa = this.zaa.get();
        if (c0006zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0006zaa.zaa(runnable);
        return this;
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    /* renamed from: com.google.android.gms.common.api.internal.zaa$zaa  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0006zaa extends LifecycleCallback {
        private List<Runnable> zaa;

        /* JADX INFO: Access modifiers changed from: private */
        public static C0006zaa zab(Activity activity) {
            C0006zaa c0006zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                c0006zaa = (C0006zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0006zaa.class);
                if (c0006zaa == null) {
                    c0006zaa = new C0006zaa(fragment);
                }
            }
            return c0006zaa;
        }

        private C0006zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zaa = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zaa.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zaa;
                this.zaa = new ArrayList();
            }
            for (Runnable runnable : list) {
                runnable.run();
            }
        }
    }
}