package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

/* loaded from: classes.dex */
final class zzq extends zzbg {
    private /* synthetic */ GoogleMap.OnPolylineClickListener zzipf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(GoogleMap googleMap, GoogleMap.OnPolylineClickListener onPolylineClickListener) {
        this.zzipf = onPolylineClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbf
    public final void zza(IPolylineDelegate iPolylineDelegate) {
        this.zzipf.onPolylineClick(new Polyline(iPolylineDelegate));
    }
}