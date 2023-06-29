package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes.dex */
final class zzy extends com.google.android.gms.maps.internal.zzak {
    private /* synthetic */ GoogleMap.OnMapClickListener zzipn;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        this.zzipn = onMapClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzaj
    public final void onMapClick(LatLng latLng) {
        this.zzipn.onMapClick(latLng);
    }
}