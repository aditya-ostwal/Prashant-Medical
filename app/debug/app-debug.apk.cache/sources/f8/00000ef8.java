package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* loaded from: classes.dex */
public class zzi extends Handler {
    private static zzk zza = null;

    public zzi() {
    }

    public zzi(Looper looper) {
        super(looper);
    }

    public zzi(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        super.dispatchMessage(message);
    }
}