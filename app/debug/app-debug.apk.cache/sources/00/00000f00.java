package com.google.android.gms.internal.common;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* loaded from: classes.dex */
final class zzq<T> implements zzo<T>, Serializable {
    private final zzo<T> zza;
    private volatile transient boolean zzb;
    @NullableDecl
    private transient T zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(zzo<T> zzoVar) {
        this.zza = (zzo) zzl.zza(zzoVar);
    }

    @Override // com.google.android.gms.internal.common.zzo
    public final T zza() {
        if (!this.zzb) {
            synchronized (this) {
                if (!this.zzb) {
                    T zza = this.zza.zza();
                    this.zzc = zza;
                    this.zzb = true;
                    return zza;
                }
            }
        }
        return this.zzc;
    }

    public final String toString() {
        Object obj;
        if (this.zzb) {
            String valueOf = String.valueOf(this.zzc);
            obj = new StringBuilder(String.valueOf(valueOf).length() + 25).append("<supplier that returned ").append(valueOf).append(">").toString();
        } else {
            obj = this.zza;
        }
        String valueOf2 = String.valueOf(obj);
        return new StringBuilder(String.valueOf(valueOf2).length() + 19).append("Suppliers.memoize(").append(valueOf2).append(")").toString();
    }
}