package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

/* loaded from: classes.dex */
public abstract class zzam extends zzee implements zzal {
    public zzam() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnMapLoadedCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onMapLoaded();
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}