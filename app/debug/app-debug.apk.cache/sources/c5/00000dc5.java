package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* loaded from: classes.dex */
public final class Asserts {
    public static void checkNull(Object obj) {
        if (obj != null) {
            throw new IllegalArgumentException("non-null reference");
        }
    }

    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("null reference");
        }
    }

    public static void checkNotNull(Object obj, Object obj2) {
        if (obj == null) {
            throw new IllegalArgumentException(String.valueOf(obj2));
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkMainThread(String str) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            String valueOf = String.valueOf(Thread.currentThread());
            String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
            Log.e("Asserts", new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(valueOf2).length()).append("checkMainThread: current thread ").append(valueOf).append(" IS NOT the main thread ").append(valueOf2).append("!").toString());
            throw new IllegalStateException(str);
        }
    }

    public static void checkNotMainThread(String str) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            String valueOf = String.valueOf(Thread.currentThread());
            String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
            Log.e("Asserts", new StringBuilder(String.valueOf(valueOf).length() + 56 + String.valueOf(valueOf2).length()).append("checkNotMainThread: current thread ").append(valueOf).append(" IS the main thread ").append(valueOf2).append("!").toString());
            throw new IllegalStateException(str);
        }
    }

    private Asserts() {
        throw new AssertionError("Uninstantiable");
    }
}