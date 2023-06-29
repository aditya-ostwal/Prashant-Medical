package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* loaded from: classes.dex */
public final class MapsInitializer {
    private static boolean initialized = false;

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        synchronized (MapsInitializer.class) {
            zzbq.checkNotNull(context, "Context is null");
            if (initialized) {
                return 0;
            }
            try {
                com.google.android.gms.maps.internal.zze zzdq = zzbz.zzdq(context);
                try {
                    CameraUpdateFactory.zza(zzdq.zzavr());
                    BitmapDescriptorFactory.zza(zzdq.zzavs());
                    initialized = true;
                    return 0;
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            } catch (GooglePlayServicesNotAvailableException e2) {
                return e2.errorCode;
            }
        }
    }
}