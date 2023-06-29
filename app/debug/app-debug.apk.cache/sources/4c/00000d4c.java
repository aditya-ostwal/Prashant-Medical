package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public abstract class zab {
    public final int zaa;

    public zab(int i) {
        this.zaa = i;
    }

    public abstract void zaa(Status status);

    public abstract void zaa(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException;

    public abstract void zaa(zav zavVar, boolean z);

    public abstract void zaa(Exception exc);

    /* JADX INFO: Access modifiers changed from: private */
    public static Status zab(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        sb.append(remoteException.getClass().getSimpleName()).append(": ").append(remoteException.getLocalizedMessage());
        return new Status(19, sb.toString());
    }
}