package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;

/* loaded from: classes.dex */
final class zzu extends com.google.android.gms.maps.internal.zzu {
    private /* synthetic */ GoogleMap.OnCameraMoveStartedListener zzipj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(GoogleMap googleMap, GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.zzipj = onCameraMoveStartedListener;
    }

    @Override // com.google.android.gms.maps.internal.zzt
    public final void onCameraMoveStarted(int i) {
        this.zzipj.onCameraMoveStarted(i);
    }
}