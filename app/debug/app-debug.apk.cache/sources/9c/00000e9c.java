package com.google.android.gms.common.util;

import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* loaded from: classes.dex */
public final class zzb {
    private static Pattern zza = null;

    public static int zza(int i) {
        if (i == -1) {
            return -1;
        }
        return i / 1000;
    }
}