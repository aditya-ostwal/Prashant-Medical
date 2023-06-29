package com.google.android.gms.common.util;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* loaded from: classes.dex */
public class MapUtils {
    public static void writeStringMapToJson(StringBuilder sb, HashMap<String, String> hashMap) {
        sb.append("{");
        boolean z = true;
        for (String str : hashMap.keySet()) {
            if (!z) {
                sb.append(",");
            } else {
                z = false;
            }
            String str2 = hashMap.get(str);
            sb.append("\"").append(str).append("\":");
            if (str2 != null) {
                sb.append("\"").append(str2).append("\"");
            } else {
                sb.append("null");
            }
        }
        sb.append("}");
    }
}