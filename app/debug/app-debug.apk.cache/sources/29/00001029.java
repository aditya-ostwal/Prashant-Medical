package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

/* loaded from: classes.dex */
public final class zzbu extends zzed implements IStreetViewPanoramaDelegate {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, streetViewPanoramaCamera);
        zzaz.writeLong(j);
        zzb(9, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enablePanning(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(2, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableStreetNames(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(4, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableUserNavigation(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(3, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableZoom(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(1, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException {
        Parcel zza = zza(10, zzaz());
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) zzef.zza(zza, StreetViewPanoramaCamera.CREATOR);
        zza.recycle();
        return streetViewPanoramaCamera;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException {
        Parcel zza = zza(14, zzaz());
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) zzef.zza(zza, StreetViewPanoramaLocation.CREATOR);
        zza.recycle();
        return streetViewPanoramaLocation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isPanningGesturesEnabled() throws RemoteException {
        Parcel zza = zza(6, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isStreetNamesEnabled() throws RemoteException {
        Parcel zza = zza(8, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isUserNavigationEnabled() throws RemoteException {
        Parcel zza = zza(7, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isZoomGesturesEnabled() throws RemoteException {
        Parcel zza = zza(5, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, streetViewPanoramaOrientation);
        Parcel zza = zza(19, zzaz);
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, iObjectWrapper);
        Parcel zza = zza(18, zzaz);
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) zzef.zza(zza, StreetViewPanoramaOrientation.CREATOR);
        zza.recycle();
        return streetViewPanoramaOrientation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaCameraChangeListener(zzbh zzbhVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzbhVar);
        zzb(16, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaChangeListener(zzbj zzbjVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzbjVar);
        zzb(15, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaClickListener(zzbl zzblVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzblVar);
        zzb(17, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaLongClickListener(zzbn zzbnVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzbnVar);
        zzb(20, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPosition(LatLng latLng) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, latLng);
        zzb(12, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithID(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzb(11, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadius(LatLng latLng, int i) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, latLng);
        zzaz.writeInt(i);
        zzb(13, zzaz);
    }
}