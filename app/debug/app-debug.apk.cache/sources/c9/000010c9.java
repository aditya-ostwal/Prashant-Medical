package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;

/* loaded from: classes.dex */
final class zzw extends com.google.android.gms.maps.internal.zzq {
    private /* synthetic */ GoogleMap.OnCameraMoveCanceledListener zzipl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(GoogleMap googleMap, GoogleMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        this.zzipl = onCameraMoveCanceledListener;
    }

    @Override // com.google.android.gms.maps.internal.zzp
    public final void onCameraMoveCanceled() {
        this.zzipl.onCameraMoveCanceled();
    }
}