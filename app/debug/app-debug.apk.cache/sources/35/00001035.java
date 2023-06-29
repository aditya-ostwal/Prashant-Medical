package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

/* loaded from: classes.dex */
public abstract class zzi extends zzee implements zzh {
    public zzi() {
        attachInterface(this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IObjectWrapper zzh;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zzh = zzh(com.google.android.gms.maps.model.internal.zzq.zzbj(parcel.readStrongBinder()));
                break;
            case 2:
                zzh = zzi(com.google.android.gms.maps.model.internal.zzq.zzbj(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        zzef.zza(parcel2, zzh);
        return true;
    }
}