package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbi;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* loaded from: classes.dex */
final class zzae extends zzbi {
    private /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener zziqv;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzae(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener) {
        this.zziqv = onStreetViewPanoramaCameraChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbh
    public final void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.zziqv.onStreetViewPanoramaCameraChange(streetViewPanoramaCamera);
    }
}