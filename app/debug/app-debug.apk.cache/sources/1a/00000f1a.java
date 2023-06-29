package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import java.util.List;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzay extends zzh {
    private final zzap zze;

    public zzay(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str) {
        this(context, looper, connectionCallbacks, onConnectionFailedListener, str, ClientSettings.createDefault(context));
    }

    public zzay(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str, @Nullable ClientSettings clientSettings) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, str, clientSettings);
        this.zze = new zzap(context, this.zzd);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        synchronized (this.zze) {
            if (isConnected()) {
                try {
                    this.zze.zzc();
                    this.zze.zzd();
                } catch (Exception e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
            }
            super.disconnect();
        }
    }

    public final void zza(long j, PendingIntent pendingIntent) throws RemoteException {
        checkConnected();
        Preconditions.checkNotNull(pendingIntent);
        Preconditions.checkArgument(j >= 0, "detectionIntervalMillis must be >= 0");
        ((zzal) getService()).zza(j, true, pendingIntent);
    }

    public final void zza(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        checkConnected();
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzal) getService()).zza(activityTransitionRequest, pendingIntent, new StatusCallback(resultHolder));
    }

    public final void zza(PendingIntent pendingIntent, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        checkConnected();
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzal) getService()).zza(pendingIntent, new StatusCallback(resultHolder));
    }

    public final void zza(PendingIntent pendingIntent) throws RemoteException {
        checkConnected();
        Preconditions.checkNotNull(pendingIntent);
        ((zzal) getService()).zza(pendingIntent);
    }

    public final void zza(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        checkConnected();
        Preconditions.checkNotNull(geofencingRequest, "geofencingRequest can't be null.");
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzal) getService()).zza(geofencingRequest, pendingIntent, new zzax(resultHolder));
    }

    public final void zza(com.google.android.gms.location.zzbe zzbeVar, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        checkConnected();
        Preconditions.checkNotNull(zzbeVar, "removeGeofencingRequest can't be null.");
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzal) getService()).zza(zzbeVar, new zzba(resultHolder));
    }

    public final void zzb(PendingIntent pendingIntent, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        checkConnected();
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzal) getService()).zza(pendingIntent, new zzba(resultHolder), getContext().getPackageName());
    }

    public final void zza(List<String> list, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        checkConnected();
        Preconditions.checkArgument(list != null && list.size() > 0, "geofenceRequestIds can't be null nor empty.");
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzal) getService()).zza((String[]) list.toArray(new String[0]), new zzba(resultHolder), getContext().getPackageName());
    }

    public final Location zza(String str) throws RemoteException {
        if (ArrayUtils.contains(getAvailableFeatures(), com.google.android.gms.location.zzp.zza)) {
            return this.zze.zza(str);
        }
        return this.zze.zza();
    }

    public final LocationAvailability zza() throws RemoteException {
        return this.zze.zzb();
    }

    public final void zza(zzbc zzbcVar, ListenerHolder<LocationCallback> listenerHolder, zzai zzaiVar) throws RemoteException {
        synchronized (this.zze) {
            this.zze.zza(zzbcVar, listenerHolder, zzaiVar);
        }
    }

    public final void zza(LocationRequest locationRequest, ListenerHolder<LocationListener> listenerHolder, zzai zzaiVar) throws RemoteException {
        synchronized (this.zze) {
            this.zze.zza(locationRequest, listenerHolder, zzaiVar);
        }
    }

    public final void zza(zzbc zzbcVar, PendingIntent pendingIntent, zzai zzaiVar) throws RemoteException {
        this.zze.zza(zzbcVar, pendingIntent, zzaiVar);
    }

    public final void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzai zzaiVar) throws RemoteException {
        this.zze.zza(locationRequest, pendingIntent, zzaiVar);
    }

    public final void zza(ListenerHolder.ListenerKey<LocationListener> listenerKey, zzai zzaiVar) throws RemoteException {
        this.zze.zza(listenerKey, zzaiVar);
    }

    public final void zza(PendingIntent pendingIntent, zzai zzaiVar) throws RemoteException {
        this.zze.zza(pendingIntent, zzaiVar);
    }

    public final void zzb(ListenerHolder.ListenerKey<LocationCallback> listenerKey, zzai zzaiVar) throws RemoteException {
        this.zze.zzb(listenerKey, zzaiVar);
    }

    public final void zza(boolean z) throws RemoteException {
        this.zze.zza(z);
    }

    public final void zza(Location location) throws RemoteException {
        this.zze.zza(location);
    }

    public final void zza(zzai zzaiVar) throws RemoteException {
        this.zze.zza(zzaiVar);
    }

    public final void zza(LocationSettingsRequest locationSettingsRequest, BaseImplementation.ResultHolder<LocationSettingsResult> resultHolder, @Nullable String str) throws RemoteException {
        checkConnected();
        Preconditions.checkArgument(locationSettingsRequest != null, "locationSettingsRequest can't be null nor empty.");
        Preconditions.checkArgument(resultHolder != null, "listener can't be null.");
        ((zzal) getService()).zza(locationSettingsRequest, new zzaz(resultHolder), str);
    }
}