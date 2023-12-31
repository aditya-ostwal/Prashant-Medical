package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

/* loaded from: classes.dex */
public abstract class zzbt extends zzee implements zzbs {
    public zzbt() {
        attachInterface(this, "com.google.android.gms.maps.internal.ISnapshotReadyCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                onSnapshotReady((Bitmap) zzef.zza(parcel, Bitmap.CREATOR));
                break;
            case 2:
                zzz(IObjectWrapper.zza.zzap(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}