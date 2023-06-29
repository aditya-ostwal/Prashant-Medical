package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzbc extends AbstractSafeParcelable {
    private LocationRequest zzb;
    private List<ClientIdentity> zzc;
    private String zzd;
    private boolean zze;
    private boolean zzf;
    private boolean zzg;
    private String zzh;
    private boolean zzi;
    private boolean zzj;
    private String zzk;
    private long zzl;
    private boolean zzm = true;
    static final List<ClientIdentity> zza = Collections.emptyList();
    public static final Parcelable.Creator<zzbc> CREATOR = new zzbb();

    @Deprecated
    public static zzbc zza(LocationRequest locationRequest) {
        return zza(null, locationRequest);
    }

    public static zzbc zza(String str, LocationRequest locationRequest) {
        return new zzbc(locationRequest, zza, null, false, false, false, null, false, false, null, Long.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbc(LocationRequest locationRequest, List<ClientIdentity> list, String str, boolean z, boolean z2, boolean z3, String str2, boolean z4, boolean z5, String str3, long j) {
        this.zzb = locationRequest;
        this.zzc = list;
        this.zzd = str;
        this.zze = z;
        this.zzf = z2;
        this.zzg = z3;
        this.zzh = str2;
        this.zzi = z4;
        this.zzj = z5;
        this.zzk = str3;
        this.zzl = j;
    }

    public final zzbc zza(long j) {
        if (this.zzb.getMaxWaitTime() > this.zzb.getInterval()) {
            long interval = this.zzb.getInterval();
            throw new IllegalArgumentException(new StringBuilder(120).append("could not set max age when location batching is requested, interval=").append(interval).append("maxWaitTime=").append(this.zzb.getMaxWaitTime()).toString());
        }
        this.zzl = 10000L;
        return this;
    }

    public final zzbc zza(String str) {
        this.zzk = str;
        return this;
    }

    public final zzbc zza(boolean z) {
        this.zzj = true;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzb, i, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zze);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzf);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzg);
        SafeParcelWriter.writeString(parcel, 10, this.zzh, false);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzj);
        SafeParcelWriter.writeString(parcel, 13, this.zzk, false);
        SafeParcelWriter.writeLong(parcel, 14, this.zzl);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzb);
        if (this.zzd != null) {
            sb.append(" tag=").append(this.zzd);
        }
        if (this.zzh != null) {
            sb.append(" moduleId=").append(this.zzh);
        }
        if (this.zzk != null) {
            sb.append(" contextAttributionTag=").append(this.zzk);
        }
        sb.append(" hideAppOps=").append(this.zze);
        sb.append(" clients=").append(this.zzc);
        sb.append(" forceCoarseLocation=").append(this.zzf);
        if (this.zzg) {
            sb.append(" exemptFromBackgroundThrottle");
        }
        if (this.zzi) {
            sb.append(" locationSettingsIgnored");
        }
        if (this.zzj) {
            sb.append(" inaccurateLocationsDelayed");
        }
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzbc) {
            zzbc zzbcVar = (zzbc) obj;
            return Objects.equal(this.zzb, zzbcVar.zzb) && Objects.equal(this.zzc, zzbcVar.zzc) && Objects.equal(this.zzd, zzbcVar.zzd) && this.zze == zzbcVar.zze && this.zzf == zzbcVar.zzf && this.zzg == zzbcVar.zzg && Objects.equal(this.zzh, zzbcVar.zzh) && this.zzi == zzbcVar.zzi && this.zzj == zzbcVar.zzj && Objects.equal(this.zzk, zzbcVar.zzk);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode();
    }
}