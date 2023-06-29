package com.google.android.gms.common.internal;

import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* loaded from: classes.dex */
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzb = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzc = new ConcurrentHashMap<>();

    public static LibraryVersion getInstance() {
        return zzb;
    }

    protected LibraryVersion() {
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getVersion(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = "Failed to get app version for libraryName: "
            java.lang.String r1 = "LibraryVersion"
            java.lang.String r2 = "Please provide a valid libraryName"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10, r2)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r2 = r9.zzc
            boolean r2 = r2.containsKey(r10)
            if (r2 == 0) goto L1a
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r9.zzc
            java.lang.Object r10 = r0.get(r10)
            java.lang.String r10 = (java.lang.String) r10
            return r10
        L1a:
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            r3 = 0
            java.lang.String r4 = "/%s.properties"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            r6 = 0
            r5[r6] = r10     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r5 = com.google.android.gms.common.internal.LibraryVersion.class
            java.io.InputStream r4 = r5.getResourceAsStream(r4)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            if (r4 == 0) goto L70
            r2.load(r4)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            java.lang.String r5 = "version"
            java.lang.String r3 = r2.getProperty(r5, r3)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            java.lang.String r5 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            int r5 = r5.length()     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            int r5 = r5 + 12
            java.lang.String r6 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            int r6 = r6.length()     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            java.lang.StringBuilder r5 = r6.append(r10)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            java.lang.String r6 = " version is "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            java.lang.StringBuilder r5 = r5.append(r3)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            r2.v(r1, r5)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            goto L89
        L70:
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            java.lang.String r5 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            int r6 = r5.length()     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            if (r6 == 0) goto L81
            java.lang.String r5 = r0.concat(r5)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            goto L86
        L81:
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
        L86:
            r2.w(r1, r5)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
        L89:
            if (r4 == 0) goto Lbb
            com.google.android.gms.common.util.IOUtils.closeQuietly(r4)
            goto Lbb
        L8f:
            r10 = move-exception
            r3 = r4
            goto Lcd
        L92:
            r2 = move-exception
            r8 = r4
            r4 = r3
            r3 = r8
            goto L9b
        L97:
            r10 = move-exception
            goto Lcd
        L99:
            r2 = move-exception
            r4 = r3
        L9b:
            com.google.android.gms.common.internal.GmsLogger r5 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L97
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L97
            int r7 = r6.length()     // Catch: java.lang.Throwable -> L97
            if (r7 == 0) goto Lac
            java.lang.String r0 = r0.concat(r6)     // Catch: java.lang.Throwable -> L97
            goto Lb2
        Lac:
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Throwable -> L97
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L97
            r0 = r6
        Lb2:
            r5.e(r1, r0, r2)     // Catch: java.lang.Throwable -> L97
            if (r3 == 0) goto Lba
            com.google.android.gms.common.util.IOUtils.closeQuietly(r3)
        Lba:
            r3 = r4
        Lbb:
            if (r3 != 0) goto Lc7
        Lbe:
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.android.gms.common.internal.LibraryVersion.zza
            java.lang.String r2 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r0.d(r1, r2)
            java.lang.String r3 = "UNKNOWN"
        Lc7:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r9.zzc
            r0.put(r10, r3)
            return r3
        Lcd:
            if (r3 == 0) goto Ld2
            com.google.android.gms.common.util.IOUtils.closeQuietly(r3)
        Ld2:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}