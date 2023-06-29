package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
final class zacl implements Runnable {
    private final /* synthetic */ Result zaa;
    private final /* synthetic */ zaci zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacl(zaci zaciVar, Result result) {
        this.zab = zaciVar;
        this.zaa = result;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WeakReference weakReference;
        zack zackVar;
        zack zackVar2;
        WeakReference weakReference2;
        ResultTransform resultTransform;
        zack zackVar3;
        zack zackVar4;
        WeakReference weakReference3;
        try {
            try {
                BasePendingResult.zaa.set(true);
                resultTransform = this.zab.zaa;
                PendingResult onSuccess = ((ResultTransform) Preconditions.checkNotNull(resultTransform)).onSuccess(this.zaa);
                zackVar3 = this.zab.zah;
                zackVar4 = this.zab.zah;
                zackVar3.sendMessage(zackVar4.obtainMessage(0, onSuccess));
                BasePendingResult.zaa.set(false);
                zaci zaciVar = this.zab;
                zaci.zaa(this.zaa);
                weakReference3 = this.zab.zag;
                GoogleApiClient googleApiClient = (GoogleApiClient) weakReference3.get();
                if (googleApiClient != null) {
                    googleApiClient.zab(this.zab);
                }
            } catch (RuntimeException e) {
                zackVar = this.zab.zah;
                zackVar2 = this.zab.zah;
                zackVar.sendMessage(zackVar2.obtainMessage(1, e));
                BasePendingResult.zaa.set(false);
                zaci zaciVar2 = this.zab;
                zaci.zaa(this.zaa);
                weakReference2 = this.zab.zag;
                GoogleApiClient googleApiClient2 = (GoogleApiClient) weakReference2.get();
                if (googleApiClient2 != null) {
                    googleApiClient2.zab(this.zab);
                }
            }
        } catch (Throwable th) {
            BasePendingResult.zaa.set(false);
            zaci zaciVar3 = this.zab;
            zaci.zaa(this.zaa);
            weakReference = this.zab.zag;
            GoogleApiClient googleApiClient3 = (GoogleApiClient) weakReference.get();
            if (googleApiClient3 != null) {
                googleApiClient3.zab(this.zab);
            }
            throw th;
        }
    }
}