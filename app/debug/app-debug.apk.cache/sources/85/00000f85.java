package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzas extends com.google.android.gms.internal.location.zzb implements zzaq {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzas(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationListener");
    }

    @Override // com.google.android.gms.location.zzaq
    public final void zza(Location location) throws RemoteException {
        Parcel b_ = b_();
        com.google.android.gms.internal.location.zzd.zza(b_, location);
        zzc(1, b_);
    }
}