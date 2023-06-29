package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR = new zzn();
    private boolean zza;
    private long zzb;
    private float zzc;
    private long zzd;
    private int zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(boolean z, long j, float f, long j2, int i) {
        this.zza = z;
        this.zzb = j;
        this.zzc = f;
        this.zzd = j2;
        this.zze = i;
    }

    public zzo() {
        this(true, 50L, 0.0f, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzc);
        SafeParcelWriter.writeLong(parcel, 4, this.zzd);
        SafeParcelWriter.writeInt(parcel, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceOrientationRequest[mShouldUseMag=").append(this.zza);
        sb.append(" mMinimumSamplingPeriodMs=").append(this.zzb);
        sb.append(" mSmallestAngleChangeRadians=").append(this.zzc);
        long j = this.zzd;
        if (j != Long.MAX_VALUE) {
            sb.append(" expireIn=");
            sb.append(j - SystemClock.elapsedRealtime()).append("ms");
        }
        if (this.zze != Integer.MAX_VALUE) {
            sb.append(" num=").append(this.zze);
        }
        sb.append(']');
        return sb.toString();
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zza), Long.valueOf(this.zzb), Float.valueOf(this.zzc), Long.valueOf(this.zzd), Integer.valueOf(this.zze));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzo) {
            zzo zzoVar = (zzo) obj;
            return this.zza == zzoVar.zza && this.zzb == zzoVar.zzb && Float.compare(this.zzc, zzoVar.zzc) == 0 && this.zzd == zzoVar.zzd && this.zze == zzoVar.zze;
        }
        return false;
    }
}