package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/* loaded from: classes.dex */
final class zzg extends com.google.android.gms.maps.internal.zzi {
    private /* synthetic */ GoogleMap.InfoWindowAdapter zziov;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(GoogleMap googleMap, GoogleMap.InfoWindowAdapter infoWindowAdapter) {
        this.zziov = infoWindowAdapter;
    }

    @Override // com.google.android.gms.maps.internal.zzh
    public final IObjectWrapper zzh(com.google.android.gms.maps.model.internal.zzp zzpVar) {
        return com.google.android.gms.dynamic.zzn.zzy(this.zziov.getInfoWindow(new Marker(zzpVar)));
    }

    @Override // com.google.android.gms.maps.internal.zzh
    public final IObjectWrapper zzi(com.google.android.gms.maps.model.internal.zzp zzpVar) {
        return com.google.android.gms.dynamic.zzn.zzy(this.zziov.getInfoContents(new Marker(zzpVar)));
    }
}