package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public final class Common {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.ClientKey<zaj> CLIENT_KEY;
    public static final zad zaa;
    private static final Api.AbstractClientBuilder<zaj, Api.ApiOptions.NoOptions> zab;

    static {
        Api.ClientKey<zaj> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zab zabVar = new zab();
        zab = zabVar;
        API = new Api<>("Common.API", zabVar, clientKey);
        zaa = new zac();
    }
}