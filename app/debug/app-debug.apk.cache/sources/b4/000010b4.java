package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;

/* loaded from: classes.dex */
final class zzb extends zzas {
    private /* synthetic */ GoogleMap.OnMarkerClickListener zzioq;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(GoogleMap googleMap, GoogleMap.OnMarkerClickListener onMarkerClickListener) {
        this.zzioq = onMarkerClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzar
    public final boolean zza(com.google.android.gms.maps.model.internal.zzp zzpVar) {
        return this.zzioq.onMarkerClick(new Marker(zzpVar));
    }
}