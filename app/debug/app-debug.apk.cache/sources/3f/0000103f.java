package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

/* loaded from: classes.dex */
public abstract class zzs extends zzee implements zzr {
    public zzs() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnCameraMoveListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onCameraMove();
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}