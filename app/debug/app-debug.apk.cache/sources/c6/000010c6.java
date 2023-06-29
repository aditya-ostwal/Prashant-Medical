package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;

/* loaded from: classes.dex */
final class zzt extends com.google.android.gms.maps.internal.zzm {
    private /* synthetic */ GoogleMap.OnCameraChangeListener zzipi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(GoogleMap googleMap, GoogleMap.OnCameraChangeListener onCameraChangeListener) {
        this.zzipi = onCameraChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzl
    public final void onCameraChange(CameraPosition cameraPosition) {
        this.zzipi.onCameraChange(cameraPosition);
    }
}