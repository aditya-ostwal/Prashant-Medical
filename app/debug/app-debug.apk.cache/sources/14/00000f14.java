package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
final class zzas extends com.google.android.gms.location.zzao {
    private final ListenerHolder<LocationCallback> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzas(ListenerHolder<LocationCallback> listenerHolder) {
        this.zza = listenerHolder;
    }

    @Override // com.google.android.gms.location.zzap
    public final void zza(LocationResult locationResult) {
        this.zza.notifyListener(new zzar(this, locationResult));
    }

    @Override // com.google.android.gms.location.zzap
    public final void zza(LocationAvailability locationAvailability) {
        this.zza.notifyListener(new zzau(this, locationAvailability));
    }

    public final synchronized void zza() {
        this.zza.clear();
    }
}