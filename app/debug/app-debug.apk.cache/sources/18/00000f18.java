package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
final class zzaw extends com.google.android.gms.location.zzat {
    private final ListenerHolder<LocationListener> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaw(ListenerHolder<LocationListener> listenerHolder) {
        this.zza = listenerHolder;
    }

    @Override // com.google.android.gms.location.zzaq
    public final synchronized void zza(Location location) {
        this.zza.notifyListener(new zzav(this, location));
    }

    public final synchronized void zza() {
        this.zza.clear();
    }
}