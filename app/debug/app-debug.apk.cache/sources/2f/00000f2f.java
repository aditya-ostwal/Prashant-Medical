package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzj extends AbstractSafeParcelable {
    private com.google.android.gms.location.zzo zzc;
    private List<ClientIdentity> zzd;
    private String zze;
    static final List<ClientIdentity> zza = Collections.emptyList();
    static final com.google.android.gms.location.zzo zzb = new com.google.android.gms.location.zzo();
    public static final Parcelable.Creator<zzj> CREATOR = new zzm();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(com.google.android.gms.location.zzo zzoVar, List<ClientIdentity> list, String str) {
        this.zzc = zzoVar;
        this.zzd = list;
        this.zze = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzc, i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 3, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzj) {
            zzj zzjVar = (zzj) obj;
            return Objects.equal(this.zzc, zzjVar.zzc) && Objects.equal(this.zzd, zzjVar.zzd) && Objects.equal(this.zze, zzjVar.zze);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzc.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        String valueOf2 = String.valueOf(this.zzd);
        String str = this.zze;
        return new StringBuilder(String.valueOf(valueOf).length() + 77 + String.valueOf(valueOf2).length() + String.valueOf(str).length()).append("DeviceOrientationRequestInternal{deviceOrientationRequest=").append(valueOf).append(", clients=").append(valueOf2).append(", tag='").append(str).append("'}").toString();
    }
}