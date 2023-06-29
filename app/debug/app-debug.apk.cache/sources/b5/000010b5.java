package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;

/* loaded from: classes.dex */
final class zzc extends zzau {
    private /* synthetic */ GoogleMap.OnMarkerDragListener zzior;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(GoogleMap googleMap, GoogleMap.OnMarkerDragListener onMarkerDragListener) {
        this.zzior = onMarkerDragListener;
    }

    @Override // com.google.android.gms.maps.internal.zzat
    public final void zzb(com.google.android.gms.maps.model.internal.zzp zzpVar) {
        this.zzior.onMarkerDragStart(new Marker(zzpVar));
    }

    @Override // com.google.android.gms.maps.internal.zzat
    public final void zzc(com.google.android.gms.maps.model.internal.zzp zzpVar) {
        this.zzior.onMarkerDragEnd(new Marker(zzpVar));
    }

    @Override // com.google.android.gms.maps.internal.zzat
    public final void zzd(com.google.android.gms.maps.model.internal.zzp zzpVar) {
        this.zzior.onMarkerDrag(new Marker(zzpVar));
    }
}