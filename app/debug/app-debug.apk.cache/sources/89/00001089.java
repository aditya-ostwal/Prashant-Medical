package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes.dex */
public final class zzr extends zzed implements zzp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final float getAlpha() throws RemoteException {
        Parcel zza = zza(26, zzaz());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final String getId() throws RemoteException {
        Parcel zza = zza(2, zzaz());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final LatLng getPosition() throws RemoteException {
        Parcel zza = zza(4, zzaz());
        LatLng latLng = (LatLng) zzef.zza(zza, LatLng.CREATOR);
        zza.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final float getRotation() throws RemoteException {
        Parcel zza = zza(23, zzaz());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final String getSnippet() throws RemoteException {
        Parcel zza = zza(8, zzaz());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final IObjectWrapper getTag() throws RemoteException {
        Parcel zza = zza(30, zzaz());
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final String getTitle() throws RemoteException {
        Parcel zza = zza(6, zzaz());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final float getZIndex() throws RemoteException {
        Parcel zza = zza(28, zzaz());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final int hashCodeRemote() throws RemoteException {
        Parcel zza = zza(17, zzaz());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void hideInfoWindow() throws RemoteException {
        zzb(12, zzaz());
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final boolean isDraggable() throws RemoteException {
        Parcel zza = zza(10, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final boolean isFlat() throws RemoteException {
        Parcel zza = zza(21, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final boolean isInfoWindowShown() throws RemoteException {
        Parcel zza = zza(13, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final boolean isVisible() throws RemoteException {
        Parcel zza = zza(15, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void remove() throws RemoteException {
        zzb(1, zzaz());
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setAlpha(float f) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        zzb(25, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setAnchor(float f, float f2) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        zzaz.writeFloat(f2);
        zzb(19, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setDraggable(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(9, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setFlat(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(20, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setInfoWindowAnchor(float f, float f2) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        zzaz.writeFloat(f2);
        zzb(24, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setPosition(LatLng latLng) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, latLng);
        zzb(3, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setRotation(float f) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        zzb(22, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setSnippet(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzb(7, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setTag(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, iObjectWrapper);
        zzb(29, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setTitle(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzb(5, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setVisible(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(14, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void setZIndex(float f) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        zzb(27, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void showInfoWindow() throws RemoteException {
        zzb(11, zzaz());
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final void zzad(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, iObjectWrapper);
        zzb(18, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzp
    public final boolean zzj(zzp zzpVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzpVar);
        Parcel zza = zza(16, zzaz);
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }
}