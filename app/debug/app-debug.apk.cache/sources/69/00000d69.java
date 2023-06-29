package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public final class zacb implements Runnable {
    private final /* synthetic */ zacc zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacb(zacc zaccVar) {
        this.zaa = zaccVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zacd zacdVar;
        zacdVar = this.zaa.zah;
        zacdVar.zaa(new ConnectionResult(4));
    }
}