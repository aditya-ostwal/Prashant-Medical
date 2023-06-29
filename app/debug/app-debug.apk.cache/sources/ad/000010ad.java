package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbm;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

/* loaded from: classes.dex */
final class zzaf extends zzbm {
    private /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaClickListener zziqw;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaf(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener) {
        this.zziqw = onStreetViewPanoramaClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbl
    public final void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.zziqw.onStreetViewPanoramaClick(streetViewPanoramaOrientation);
    }
}