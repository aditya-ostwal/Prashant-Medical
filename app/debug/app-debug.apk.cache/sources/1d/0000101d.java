package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* loaded from: classes.dex */
public abstract class zzbi extends zzee implements zzbh {
    public zzbi() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onStreetViewPanoramaCameraChange((StreetViewPanoramaCamera) zzef.zza(parcel, StreetViewPanoramaCamera.CREATOR));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}