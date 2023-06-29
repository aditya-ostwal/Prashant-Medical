package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public final class zaba {
    private static final ExecutorService zaa = com.google.android.gms.internal.base.zal.zaa().zaa(2, new NumberedThreadFactory("GAC_Executor"), com.google.android.gms.internal.base.zaq.zab);

    public static ExecutorService zaa() {
        return zaa;
    }
}