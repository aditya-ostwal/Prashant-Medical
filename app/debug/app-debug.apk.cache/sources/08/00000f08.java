package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzag extends zzaf {
    private final /* synthetic */ com.google.android.gms.location.zzbe zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzag(zzae zzaeVar, GoogleApiClient googleApiClient, com.google.android.gms.location.zzbe zzbeVar) {
        super(googleApiClient);
        this.zza = zzbeVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(zzay zzayVar) throws RemoteException {
        zzayVar.zza(this.zza, this);
    }
}