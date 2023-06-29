package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

/* loaded from: classes.dex */
public final class zzbr extends zzed implements IProjectionDelegate {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final LatLng fromScreenLocation(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, iObjectWrapper);
        Parcel zza = zza(1, zzaz);
        LatLng latLng = (LatLng) zzef.zza(zza, LatLng.CREATOR);
        zza.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final VisibleRegion getVisibleRegion() throws RemoteException {
        Parcel zza = zza(3, zzaz());
        VisibleRegion visibleRegion = (VisibleRegion) zzef.zza(zza, VisibleRegion.CREATOR);
        zza.recycle();
        return visibleRegion;
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final IObjectWrapper toScreenLocation(LatLng latLng) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, latLng);
        Parcel zza = zza(2, zzaz);
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }
}