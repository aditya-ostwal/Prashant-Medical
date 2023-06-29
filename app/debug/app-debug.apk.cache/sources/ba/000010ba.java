package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzay;

/* loaded from: classes.dex */
final class zzh extends zzay {
    private /* synthetic */ GoogleMap.OnMyLocationChangeListener zziow;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(GoogleMap googleMap, GoogleMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        this.zziow = onMyLocationChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzax
    public final void zzy(IObjectWrapper iObjectWrapper) {
        this.zziow.onMyLocationChange((Location) com.google.android.gms.dynamic.zzn.zzx(iObjectWrapper));
    }
}