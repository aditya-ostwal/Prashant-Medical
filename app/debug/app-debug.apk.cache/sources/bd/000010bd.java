package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzam;

/* loaded from: classes.dex */
final class zzk extends zzam {
    private /* synthetic */ GoogleMap.OnMapLoadedCallback zzioz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.zzioz = onMapLoadedCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzal
    public final void onMapLoaded() throws RemoteException {
        this.zzioz.onMapLoaded();
    }
}