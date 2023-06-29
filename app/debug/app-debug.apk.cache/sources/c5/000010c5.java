package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.PointOfInterest;

/* loaded from: classes.dex */
final class zzs extends zzbc {
    private /* synthetic */ GoogleMap.OnPoiClickListener zziph;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(GoogleMap googleMap, GoogleMap.OnPoiClickListener onPoiClickListener) {
        this.zziph = onPoiClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbb
    public final void zza(PointOfInterest pointOfInterest) throws RemoteException {
        this.zziph.onPoiClick(pointOfInterest);
    }
}