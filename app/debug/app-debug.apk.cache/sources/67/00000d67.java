package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
abstract class zac<T> extends zad {
    protected final TaskCompletionSource<T> zab;

    public zac(int i, TaskCompletionSource<T> taskCompletionSource) {
        super(i);
        this.zab = taskCompletionSource;
    }

    protected abstract void zab(GoogleApiManager.zaa<?> zaaVar) throws RemoteException;

    @Override // com.google.android.gms.common.api.internal.zab
    public void zaa(Status status) {
        this.zab.trySetException(new ApiException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public void zaa(Exception exc) {
        this.zab.trySetException(exc);
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public void zaa(zav zavVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException {
        Status zab;
        Status zab2;
        try {
            zab(zaaVar);
        } catch (DeadObjectException e) {
            zab2 = zab.zab(e);
            zaa(zab2);
            throw e;
        } catch (RemoteException e2) {
            zab = zab.zab(e2);
            zaa(zab);
        } catch (RuntimeException e3) {
            zaa(e3);
        }
    }
}