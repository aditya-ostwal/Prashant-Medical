package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;

/* loaded from: classes.dex */
public abstract class zzay extends zzee implements zzax {
    public zzay() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zzy(IObjectWrapper.zza.zzap(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}