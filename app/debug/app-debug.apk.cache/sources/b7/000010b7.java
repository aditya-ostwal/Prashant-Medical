package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/* loaded from: classes.dex */
final class zze extends com.google.android.gms.maps.internal.zzag {
    private /* synthetic */ GoogleMap.OnInfoWindowLongClickListener zziot;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(GoogleMap googleMap, GoogleMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.zziot = onInfoWindowLongClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzaf
    public final void zzf(com.google.android.gms.maps.model.internal.zzp zzpVar) {
        this.zziot.onInfoWindowLongClick(new Marker(zzpVar));
    }
}