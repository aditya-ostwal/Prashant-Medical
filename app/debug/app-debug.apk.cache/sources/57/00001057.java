package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;

/* loaded from: classes.dex */
public final class IndoorLevel {
    private final com.google.android.gms.maps.model.internal.zzm zziso;

    public IndoorLevel(com.google.android.gms.maps.model.internal.zzm zzmVar) {
        this.zziso = (com.google.android.gms.maps.model.internal.zzm) zzbq.checkNotNull(zzmVar);
    }

    public final void activate() {
        try {
            this.zziso.activate();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof IndoorLevel) {
            try {
                return this.zziso.zza(((IndoorLevel) obj).zziso);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        return false;
    }

    public final String getName() {
        try {
            return this.zziso.getName();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final String getShortName() {
        try {
            return this.zziso.getShortName();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int hashCode() {
        try {
            return this.zziso.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}