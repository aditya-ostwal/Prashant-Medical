package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzab extends zzaq {
    private /* synthetic */ OnMapReadyCallback zziqi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(MapFragment.zza zzaVar, OnMapReadyCallback onMapReadyCallback) {
        this.zziqi = onMapReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzap
    public final void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        this.zziqi.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}