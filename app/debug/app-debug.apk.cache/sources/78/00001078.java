package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.Tile;

/* loaded from: classes.dex */
public final class zzab extends zzed implements zzz {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    @Override // com.google.android.gms.maps.model.internal.zzz
    public final Tile getTile(int i, int i2, int i3) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeInt(i);
        zzaz.writeInt(i2);
        zzaz.writeInt(i3);
        Parcel zza = zza(1, zzaz);
        Tile tile = (Tile) zzef.zza(zza, Tile.CREATOR);
        zza.recycle();
        return tile;
    }
}