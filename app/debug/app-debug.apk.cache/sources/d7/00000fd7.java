package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

/* loaded from: classes.dex */
public final class Projection {
    private final IProjectionDelegate zziqs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Projection(IProjectionDelegate iProjectionDelegate) {
        this.zziqs = iProjectionDelegate;
    }

    public final LatLng fromScreenLocation(Point point) {
        try {
            return this.zziqs.fromScreenLocation(com.google.android.gms.dynamic.zzn.zzy(point));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final VisibleRegion getVisibleRegion() {
        try {
            return this.zziqs.getVisibleRegion();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Point toScreenLocation(LatLng latLng) {
        try {
            return (Point) com.google.android.gms.dynamic.zzn.zzx(this.zziqs.toScreenLocation(latLng));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}