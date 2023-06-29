package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzag extends FusedLocationProviderClient.zzc {
    private final /* synthetic */ ListenerHolder zza;
    private final /* synthetic */ FusedLocationProviderClient zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzag(FusedLocationProviderClient fusedLocationProviderClient, ListenerHolder listenerHolder) {
        this.zzb = fusedLocationProviderClient;
        this.zza = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(com.google.android.gms.internal.location.zzay zzayVar, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        com.google.android.gms.internal.location.zzai zza;
        com.google.android.gms.internal.location.zzay zzayVar2 = zzayVar;
        TaskCompletionSource<Boolean> taskCompletionSource2 = taskCompletionSource;
        if (!zza()) {
            return;
        }
        zza = this.zzb.zza(taskCompletionSource2);
        try {
            zzayVar2.zzb(this.zza.getListenerKey(), zza);
        } catch (RuntimeException e) {
            taskCompletionSource2.trySetException(e);
        }
    }
}