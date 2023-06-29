package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
final class zace implements Runnable {
    private final /* synthetic */ com.google.android.gms.signin.internal.zam zaa;
    private final /* synthetic */ zacc zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zace(zacc zaccVar, com.google.android.gms.signin.internal.zam zamVar) {
        this.zab = zaccVar;
        this.zaa = zamVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zab.zab(this.zaa);
    }
}