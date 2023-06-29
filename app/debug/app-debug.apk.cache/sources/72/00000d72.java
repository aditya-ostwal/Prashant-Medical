package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public final class zack extends com.google.android.gms.internal.base.zap {
    private final /* synthetic */ zaci zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zack(zaci zaciVar, Looper looper) {
        super(looper);
        this.zaa = zaciVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Object obj;
        zaci zaciVar;
        switch (message.what) {
            case 0:
                PendingResult<?> pendingResult = (PendingResult) message.obj;
                obj = this.zaa.zae;
                synchronized (obj) {
                    zaciVar = this.zaa.zab;
                    zaci zaciVar2 = (zaci) Preconditions.checkNotNull(zaciVar);
                    if (pendingResult != null) {
                        if (!(pendingResult instanceof zaca)) {
                            zaciVar2.zaa(pendingResult);
                        } else {
                            zaciVar2.zaa(((zaca) pendingResult).zaa());
                        }
                    } else {
                        zaciVar2.zaa(new Status(13, "Transform returned null"));
                    }
                }
                return;
            case 1:
                RuntimeException runtimeException = (RuntimeException) message.obj;
                String valueOf = String.valueOf(runtimeException.getMessage());
                Log.e("TransformedResultImpl", valueOf.length() != 0 ? "Runtime exception on the transformation worker thread: ".concat(valueOf) : new String("Runtime exception on the transformation worker thread: "));
                throw runtimeException;
            default:
                Log.e("TransformedResultImpl", new StringBuilder(70).append("TransformationResultHandler received unknown message type: ").append(message.what).toString());
                return;
        }
    }
}