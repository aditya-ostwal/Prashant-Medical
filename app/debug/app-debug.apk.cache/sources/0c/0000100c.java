package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

/* loaded from: classes.dex */
public abstract class zzas extends zzee implements zzar {
    public zzas() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerClickListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            boolean zza = zza(com.google.android.gms.maps.model.internal.zzq.zzbj(parcel.readStrongBinder()));
            parcel2.writeNoException();
            zzef.zza(parcel2, zza);
            return true;
        }
        return false;
    }
}