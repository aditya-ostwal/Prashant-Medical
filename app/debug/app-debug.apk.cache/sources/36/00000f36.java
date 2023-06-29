package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
final class zzq extends zzaa {
    private final /* synthetic */ LocationRequest zza;
    private final /* synthetic */ LocationListener zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzq(zzn zznVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
        super(googleApiClient);
        this.zza = locationRequest;
        this.zzb = locationListener;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(zzay zzayVar) throws RemoteException {
        zzayVar.zza(this.zza, ListenerHolders.createListenerHolder(this.zzb, zzbj.zza(), LocationListener.class.getSimpleName()), new zzz(this));
    }
}