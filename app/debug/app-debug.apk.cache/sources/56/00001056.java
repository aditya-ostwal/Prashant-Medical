package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class IndoorBuilding {
    private final com.google.android.gms.maps.model.internal.zzj zzisn;

    public IndoorBuilding(com.google.android.gms.maps.model.internal.zzj zzjVar) {
        this.zzisn = (com.google.android.gms.maps.model.internal.zzj) zzbq.checkNotNull(zzjVar);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof IndoorBuilding) {
            try {
                return this.zzisn.zzb(((IndoorBuilding) obj).zzisn);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        return false;
    }

    public final int getActiveLevelIndex() {
        try {
            return this.zzisn.getActiveLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getDefaultLevelIndex() {
        try {
            return this.zzisn.getActiveLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final List<IndoorLevel> getLevels() {
        try {
            List<IBinder> levels = this.zzisn.getLevels();
            ArrayList arrayList = new ArrayList(levels.size());
            for (IBinder iBinder : levels) {
                arrayList.add(new IndoorLevel(com.google.android.gms.maps.model.internal.zzn.zzbi(iBinder)));
            }
            return arrayList;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int hashCode() {
        try {
            return this.zzisn.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isUnderground() {
        try {
            return this.zzisn.isUnderground();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}