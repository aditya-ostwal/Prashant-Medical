package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public class ActivityRecognition {
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final ActivityRecognitionApi ActivityRecognitionApi;
    public static final String CLIENT_NAME = "activity_recognition";
    private static final Api.ClientKey<com.google.android.gms.internal.location.zzay> zza;
    private static final Api.AbstractClientBuilder<com.google.android.gms.internal.location.zzay, Api.ApiOptions.NoOptions> zzb;

    private ActivityRecognition() {
    }

    /* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
    /* loaded from: classes.dex */
    public static abstract class zza<R extends Result> extends BaseImplementation.ApiMethodImpl<R, com.google.android.gms.internal.location.zzay> {
        public zza(GoogleApiClient googleApiClient) {
            super(ActivityRecognition.API, googleApiClient);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
        public /* bridge */ /* synthetic */ void setResult(Object obj) {
            super.setResult((zza<R>) ((Result) obj));
        }
    }

    public static ActivityRecognitionClient getClient(Activity activity) {
        return new ActivityRecognitionClient(activity);
    }

    public static ActivityRecognitionClient getClient(Context context) {
        return new ActivityRecognitionClient(context);
    }

    static {
        Api.ClientKey<com.google.android.gms.internal.location.zzay> clientKey = new Api.ClientKey<>();
        zza = clientKey;
        com.google.android.gms.location.zza zzaVar = new com.google.android.gms.location.zza();
        zzb = zzaVar;
        API = new Api<>("ActivityRecognition.API", zzaVar, clientKey);
        ActivityRecognitionApi = new com.google.android.gms.internal.location.zze();
    }
}