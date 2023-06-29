package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

/* loaded from: classes.dex */
public abstract class zzu extends zzee implements zzt {
    public zzu() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnCameraMoveStartedListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onCameraMoveStarted(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}