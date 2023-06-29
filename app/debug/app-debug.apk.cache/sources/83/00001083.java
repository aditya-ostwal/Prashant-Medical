package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class zzl extends zzed implements zzj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
    }

    @Override // com.google.android.gms.maps.model.internal.zzj
    public final int getActiveLevelIndex() throws RemoteException {
        Parcel zza = zza(1, zzaz());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.maps.model.internal.zzj
    public final List<IBinder> getLevels() throws RemoteException {
        Parcel zza = zza(3, zzaz());
        ArrayList<IBinder> createBinderArrayList = zza.createBinderArrayList();
        zza.recycle();
        return createBinderArrayList;
    }

    @Override // com.google.android.gms.maps.model.internal.zzj
    public final int hashCodeRemote() throws RemoteException {
        Parcel zza = zza(6, zzaz());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.maps.model.internal.zzj
    public final boolean isUnderground() throws RemoteException {
        Parcel zza = zza(4, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.model.internal.zzj
    public final boolean zzb(zzj zzjVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzjVar);
        Parcel zza = zza(5, zzaz);
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }
}