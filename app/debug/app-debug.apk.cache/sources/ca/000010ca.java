package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;

/* loaded from: classes.dex */
final class zzx extends com.google.android.gms.maps.internal.zzo {
    private /* synthetic */ GoogleMap.OnCameraIdleListener zzipm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzx(GoogleMap googleMap, GoogleMap.OnCameraIdleListener onCameraIdleListener) {
        this.zzipm = onCameraIdleListener;
    }

    @Override // com.google.android.gms.maps.internal.zzn
    public final void onCameraIdle() {
        this.zzipm.onCameraIdle();
    }
}