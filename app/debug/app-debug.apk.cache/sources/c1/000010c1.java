package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;

/* loaded from: classes.dex */
final class zzo extends com.google.android.gms.maps.internal.zzw {
    private /* synthetic */ GoogleMap.OnCircleClickListener zzipd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(GoogleMap googleMap, GoogleMap.OnCircleClickListener onCircleClickListener) {
        this.zzipd = onCircleClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzv
    public final void zza(com.google.android.gms.maps.model.internal.zzd zzdVar) {
        this.zzipd.onCircleClick(new Circle(zzdVar));
    }
}