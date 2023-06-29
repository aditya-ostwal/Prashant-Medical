package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

/* loaded from: classes.dex */
public abstract class zzba extends zzee implements zzaz {
    public zzba() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnMyLocationClickListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onMyLocationClick((Location) zzef.zza(parcel, Location.CREATOR));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}