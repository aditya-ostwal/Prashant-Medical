package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.IndoorBuilding;

/* loaded from: classes.dex */
final class zza extends com.google.android.gms.maps.internal.zzaa {
    private /* synthetic */ GoogleMap.OnIndoorStateChangeListener zziop;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(GoogleMap googleMap, GoogleMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        this.zziop = onIndoorStateChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzz
    public final void onIndoorBuildingFocused() {
        this.zziop.onIndoorBuildingFocused();
    }

    @Override // com.google.android.gms.maps.internal.zzz
    public final void zza(com.google.android.gms.maps.model.internal.zzj zzjVar) {
        this.zziop.onIndoorLevelActivated(new IndoorBuilding(zzjVar));
    }
}