package com.google.android.gms.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzar extends com.google.android.gms.internal.location.zzb implements zzap {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzar(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationCallback");
    }

    @Override // com.google.android.gms.location.zzap
    public final void zza(LocationResult locationResult) throws RemoteException {
        Parcel b_ = b_();
        com.google.android.gms.internal.location.zzd.zza(b_, locationResult);
        zzc(1, b_);
    }

    @Override // com.google.android.gms.location.zzap
    public final void zza(LocationAvailability locationAvailability) throws RemoteException {
        Parcel b_ = b_();
        com.google.android.gms.internal.location.zzd.zza(b_, locationAvailability);
        zzc(2, b_);
    }
}