package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public class LocationServices {
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi;
    @Deprecated
    public static final GeofencingApi GeofencingApi;
    @Deprecated
    public static final SettingsApi SettingsApi;
    private static final Api.ClientKey<com.google.android.gms.internal.location.zzay> zza;
    private static final Api.AbstractClientBuilder<com.google.android.gms.internal.location.zzay, Api.ApiOptions.NoOptions> zzb;

    private LocationServices() {
    }

    /* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
    /* loaded from: classes.dex */
    public static abstract class zza<R extends Result> extends BaseImplementation.ApiMethodImpl<R, com.google.android.gms.internal.location.zzay> {
        public zza(GoogleApiClient googleApiClient) {
            super(LocationServices.API, googleApiClient);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
        public /* bridge */ /* synthetic */ void setResult(Object obj) {
            super.setResult((zza<R>) ((Result) obj));
        }
    }

    public static com.google.android.gms.internal.location.zzay zza(GoogleApiClient googleApiClient) {
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        com.google.android.gms.internal.location.zzay zzayVar = (com.google.android.gms.internal.location.zzay) googleApiClient.getClient(zza);
        Preconditions.checkState(zzayVar != null, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzayVar;
    }

    public static GeofencingClient getGeofencingClient(Activity activity) {
        return new GeofencingClient(activity);
    }

    public static GeofencingClient getGeofencingClient(Context context) {
        return new GeofencingClient(context);
    }

    public static SettingsClient getSettingsClient(Activity activity) {
        return new SettingsClient(activity);
    }

    public static SettingsClient getSettingsClient(Context context) {
        return new SettingsClient(context);
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Activity activity) {
        return new FusedLocationProviderClient(activity);
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Context context) {
        return new FusedLocationProviderClient(context);
    }

    static {
        Api.ClientKey<com.google.android.gms.internal.location.zzay> clientKey = new Api.ClientKey<>();
        zza = clientKey;
        zzax zzaxVar = new zzax();
        zzb = zzaxVar;
        API = new Api<>("LocationServices.API", zzaxVar, clientKey);
        FusedLocationApi = new com.google.android.gms.internal.location.zzn();
        GeofencingApi = new com.google.android.gms.internal.location.zzae();
        SettingsApi = new com.google.android.gms.internal.location.zzbh();
    }
}