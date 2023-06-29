package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public abstract class zzat extends com.google.android.gms.internal.location.zza implements zzaq {
    public zzat() {
        super("com.google.android.gms.location.ILocationListener");
    }

    public static zzaq zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        if (queryLocalInterface instanceof zzaq) {
            return (zzaq) queryLocalInterface;
        }
        return new zzas(iBinder);
    }

    @Override // com.google.android.gms.internal.location.zza
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza((Location) com.google.android.gms.internal.location.zzd.zza(parcel, Location.CREATOR));
            return true;
        }
        return false;
    }
}