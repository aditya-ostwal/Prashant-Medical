package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

/* loaded from: classes.dex */
public final class zzy extends zzed implements zzw {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final void clearTileCache() throws RemoteException {
        zzb(2, zzaz());
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final boolean getFadeIn() throws RemoteException {
        Parcel zza = zza(11, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final String getId() throws RemoteException {
        Parcel zza = zza(3, zzaz());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final float getTransparency() throws RemoteException {
        Parcel zza = zza(13, zzaz());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final float getZIndex() throws RemoteException {
        Parcel zza = zza(5, zzaz());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final int hashCodeRemote() throws RemoteException {
        Parcel zza = zza(9, zzaz());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final boolean isVisible() throws RemoteException {
        Parcel zza = zza(7, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final void remove() throws RemoteException {
        zzb(1, zzaz());
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final void setFadeIn(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(10, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final void setTransparency(float f) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        zzb(12, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final void setVisible(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(6, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final void setZIndex(float f) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        zzb(4, zzaz);
    }

    @Override // com.google.android.gms.maps.model.internal.zzw
    public final boolean zza(zzw zzwVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzwVar);
        Parcel zza = zza(8, zzaz);
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }
}