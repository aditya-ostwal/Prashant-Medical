package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

/* loaded from: classes.dex */
public abstract class zzq extends zzee implements zzp {
    public zzq() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnCameraMoveCanceledListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onCameraMoveCanceled();
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}