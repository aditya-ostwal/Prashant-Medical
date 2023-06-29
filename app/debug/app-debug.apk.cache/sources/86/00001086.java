package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

/* loaded from: classes.dex */
public final class zzo extends zzed implements zzm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
    }

    @Override // com.google.android.gms.maps.model.internal.zzm
    public final void activate() throws RemoteException {
        zzb(3, zzaz());
    }

    @Override // com.google.android.gms.maps.model.internal.zzm
    public final String getName() throws RemoteException {
        Parcel zza = zza(1, zzaz());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.maps.model.internal.zzm
    public final String getShortName() throws RemoteException {
        Parcel zza = zza(2, zzaz());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.maps.model.internal.zzm
    public final int hashCodeRemote() throws RemoteException {
        Parcel zza = zza(5, zzaz());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.maps.model.internal.zzm
    public final boolean zza(zzm zzmVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzmVar);
        Parcel zza = zza(4, zzaz);
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }
}