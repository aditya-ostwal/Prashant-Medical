package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

/* loaded from: classes.dex */
public abstract class zzac extends zzee implements zzab {
    public zzac() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zze(com.google.android.gms.maps.model.internal.zzq.zzbj(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}