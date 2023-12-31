package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

/* loaded from: classes.dex */
public abstract class zzau extends zzee implements zzat {
    public zzau() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zzb(com.google.android.gms.maps.model.internal.zzq.zzbj(parcel.readStrongBinder()));
                break;
            case 2:
                zzd(com.google.android.gms.maps.model.internal.zzq.zzbj(parcel.readStrongBinder()));
                break;
            case 3:
                zzc(com.google.android.gms.maps.model.internal.zzq.zzbj(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}