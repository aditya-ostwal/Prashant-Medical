package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* loaded from: classes.dex */
public class zzbz {
    private static final String TAG = zzbz.class.getSimpleName();
    private static Context zzirq = null;
    private static zze zzirr;

    private static <T> T zza(ClassLoader classLoader, String str) {
        try {
            return (T) zzd(((ClassLoader) com.google.android.gms.common.internal.zzbq.checkNotNull(classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? "Unable to find dynamic class ".concat(valueOf) : new String("Unable to find dynamic class "));
        }
    }

    private static <T> T zzd(Class<?> cls) {
        try {
            return (T) cls.newInstance();
        } catch (IllegalAccessException e) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? "Unable to call the default constructor of ".concat(valueOf) : new String("Unable to call the default constructor of "));
        } catch (InstantiationException e2) {
            String valueOf2 = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf2.length() != 0 ? "Unable to instantiate the dynamic class ".concat(valueOf2) : new String("Unable to instantiate the dynamic class "));
        }
    }

    public static zze zzdq(Context context) throws GooglePlayServicesNotAvailableException {
        zze zzfVar;
        com.google.android.gms.common.internal.zzbq.checkNotNull(context);
        zze zzeVar = zzirr;
        if (zzeVar != null) {
            return zzeVar;
        }
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        switch (isGooglePlayServicesAvailable) {
            case 0:
                Log.i(TAG, "Making Creator dynamically");
                IBinder iBinder = (IBinder) zza(zzdr(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
                if (iBinder == null) {
                    zzfVar = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
                    zzfVar = queryLocalInterface instanceof zze ? (zze) queryLocalInterface : new zzf(iBinder);
                }
                zzirr = zzfVar;
                try {
                    zzfVar.zzi(com.google.android.gms.dynamic.zzn.zzy(zzdr(context).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                    return zzirr;
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            default:
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
    }

    private static Context zzdr(Context context) {
        Context context2 = zzirq;
        if (context2 != null) {
            return context2;
        }
        Context zzds = zzds(context);
        zzirq = zzds;
        return zzds;
    }

    private static Context zzds(Context context) {
        try {
            return DynamiteModule.zza(context, DynamiteModule.zzgum, "com.google.android.gms.maps_dynamite").zzapq();
        } catch (Throwable th) {
            Log.e(TAG, "Failed to load maps module, use legacy", th);
            return GooglePlayServicesUtil.getRemoteContext(context);
        }
    }
}