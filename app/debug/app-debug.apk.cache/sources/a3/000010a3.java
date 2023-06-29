package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.zzz;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzr implements TileProvider {
    private final zzz zzitr;
    private /* synthetic */ TileOverlayOptions zzits;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(TileOverlayOptions tileOverlayOptions) {
        zzz zzzVar;
        this.zzits = tileOverlayOptions;
        zzzVar = this.zzits.zzito;
        this.zzitr = zzzVar;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    public final Tile getTile(int i, int i2, int i3) {
        try {
            return this.zzitr.getTile(i, i2, i3);
        } catch (RemoteException e) {
            return null;
        }
    }
}