package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
final /* synthetic */ class zzx implements RemoteCall {
    static final RemoteCall zza = new zzx();

    private zzx() {
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        ((TaskCompletionSource) obj2).setResult(((com.google.android.gms.internal.location.zzay) obj).zza());
    }
}