package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;

/* loaded from: classes.dex */
final class zzv extends com.google.android.gms.maps.internal.zzs {
    private /* synthetic */ GoogleMap.OnCameraMoveListener zzipk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(GoogleMap googleMap, GoogleMap.OnCameraMoveListener onCameraMoveListener) {
        this.zzipk = onCameraMoveListener;
    }

    @Override // com.google.android.gms.maps.internal.zzr
    public final void onCameraMove() {
        this.zzipk.onCameraMove();
    }
}