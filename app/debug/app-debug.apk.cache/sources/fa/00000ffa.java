package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

/* loaded from: classes.dex */
public abstract class zzaa extends zzee implements zzz {
    public zzaa() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                onIndoorBuildingFocused();
                break;
            case 2:
                zza(com.google.android.gms.maps.model.internal.zzk.zzbh(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}