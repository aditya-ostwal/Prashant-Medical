package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/* loaded from: classes.dex */
final class zzf extends com.google.android.gms.maps.internal.zzae {
    private /* synthetic */ GoogleMap.OnInfoWindowCloseListener zziou;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(GoogleMap googleMap, GoogleMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.zziou = onInfoWindowCloseListener;
    }

    @Override // com.google.android.gms.maps.internal.zzad
    public final void zzg(com.google.android.gms.maps.model.internal.zzp zzpVar) {
        this.zziou.onInfoWindowClose(new Marker(zzpVar));
    }
}