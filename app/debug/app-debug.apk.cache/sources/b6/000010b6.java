package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/* loaded from: classes.dex */
final class zzd extends com.google.android.gms.maps.internal.zzac {
    private /* synthetic */ GoogleMap.OnInfoWindowClickListener zzios;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzd(GoogleMap googleMap, GoogleMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.zzios = onInfoWindowClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzab
    public final void zze(com.google.android.gms.maps.model.internal.zzp zzpVar) {
        this.zzios.onInfoWindowClick(new Marker(zzpVar));
    }
}