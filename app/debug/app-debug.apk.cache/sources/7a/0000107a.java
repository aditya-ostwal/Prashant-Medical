package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

/* loaded from: classes.dex */
public final class zzc extends zzed implements zza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
    }

    @Override // com.google.android.gms.maps.model.internal.zza
    public final IObjectWrapper zzavv() throws RemoteException {
        Parcel zza = zza(4, zzaz());
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }

    @Override // com.google.android.gms.maps.model.internal.zza
    public final IObjectWrapper zzd(Bitmap bitmap) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, bitmap);
        Parcel zza = zza(6, zzaz);
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }

    @Override // com.google.android.gms.maps.model.internal.zza
    public final IObjectWrapper zze(float f) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        Parcel zza = zza(5, zzaz);
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }

    @Override // com.google.android.gms.maps.model.internal.zza
    public final IObjectWrapper zzeb(int i) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeInt(i);
        Parcel zza = zza(1, zzaz);
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }

    @Override // com.google.android.gms.maps.model.internal.zza
    public final IObjectWrapper zzii(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        Parcel zza = zza(2, zzaz);
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }

    @Override // com.google.android.gms.maps.model.internal.zza
    public final IObjectWrapper zzij(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        Parcel zza = zza(3, zzaz);
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }

    @Override // com.google.android.gms.maps.model.internal.zza
    public final IObjectWrapper zzik(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        Parcel zza = zza(7, zzaz);
        IObjectWrapper zzap = IObjectWrapper.zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }
}