package com.google.android.gms.common.util;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* loaded from: classes.dex */
public class NumberUtils {
    public static long parseHexLong(String str) {
        if (str.length() > 16) {
            throw new NumberFormatException(new StringBuilder(String.valueOf(str).length() + 37).append("Invalid input: ").append(str).append(" exceeds 16 characters").toString());
        }
        if (str.length() == 16) {
            return (Long.parseLong(str.substring(0, 8), 16) << 32) | Long.parseLong(str.substring(8), 16);
        }
        return Long.parseLong(str, 16);
    }

    private NumberUtils() {
    }
}