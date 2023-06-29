package com.google.android.gms.location;

import com.google.android.gms.common.Feature;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzp {
    public static final Feature zza;
    public static final Feature zzb;
    public static final Feature[] zzc;
    private static final Feature zzd;
    private static final Feature zze;

    static {
        Feature feature = new Feature("name_ulr_private", 1L);
        zzd = feature;
        Feature feature2 = new Feature("name_sleep_segment_request", 1L);
        zze = feature2;
        Feature feature3 = new Feature("support_context_feature_id", 1L);
        zza = feature3;
        Feature feature4 = new Feature("get_current_location", 1L);
        zzb = feature4;
        zzc = new Feature[]{feature, feature2, feature3, feature4};
    }
}