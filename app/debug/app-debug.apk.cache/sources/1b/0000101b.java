package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

/* loaded from: classes.dex */
public abstract class zzbg extends zzee implements zzbf {
    public zzbg() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnPolylineClickListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza(IPolylineDelegate.zza.zzbl(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}