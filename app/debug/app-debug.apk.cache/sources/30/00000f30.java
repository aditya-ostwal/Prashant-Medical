package com.google.android.gms.internal.location;

import android.os.DeadObjectException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzk implements zzbi<zzal> {
    private final /* synthetic */ zzh zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(zzh zzhVar) {
        this.zza = zzhVar;
    }

    @Override // com.google.android.gms.internal.location.zzbi
    public final void zza() {
        this.zza.checkConnected();
    }

    @Override // com.google.android.gms.internal.location.zzbi
    public final /* synthetic */ zzal zzb() throws DeadObjectException {
        return (zzal) this.zza.getService();
    }
}