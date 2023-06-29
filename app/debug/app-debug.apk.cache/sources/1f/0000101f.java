package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

/* loaded from: classes.dex */
public abstract class zzbk extends zzee implements zzbj {
    public zzbk() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onStreetViewPanoramaChange((StreetViewPanoramaLocation) zzef.zza(parcel, StreetViewPanoramaLocation.CREATOR));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}