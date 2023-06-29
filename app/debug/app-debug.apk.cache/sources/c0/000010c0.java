package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.GroundOverlay;

/* loaded from: classes.dex */
final class zzn extends com.google.android.gms.maps.internal.zzy {
    private /* synthetic */ GoogleMap.OnGroundOverlayClickListener zzipc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(GoogleMap googleMap, GoogleMap.OnGroundOverlayClickListener onGroundOverlayClickListener) {
        this.zzipc = onGroundOverlayClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzx
    public final void zza(com.google.android.gms.maps.model.internal.zzg zzgVar) {
        this.zzipc.onGroundOverlayClick(new GroundOverlay(zzgVar));
    }
}