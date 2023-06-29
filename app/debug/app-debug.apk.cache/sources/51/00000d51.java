package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.GoogleApiManager;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
final class zabe implements Runnable {
    private final /* synthetic */ ConnectionResult zaa;
    private final /* synthetic */ GoogleApiManager.zaa zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabe(GoogleApiManager.zaa zaaVar, ConnectionResult connectionResult) {
        this.zab = zaaVar;
        this.zaa = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zab.onConnectionFailed(this.zaa);
    }
}