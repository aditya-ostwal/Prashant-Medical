package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzbe extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbe> CREATOR = new zzbd();
    private int zza;
    private zzbc zzb;
    private com.google.android.gms.location.zzaq zzc;
    private PendingIntent zzd;
    private com.google.android.gms.location.zzap zze;
    private zzai zzf;

    public static zzbe zza(com.google.android.gms.location.zzaq zzaqVar, zzai zzaiVar) {
        return new zzbe(2, null, zzaqVar.asBinder(), null, null, zzaiVar != null ? zzaiVar.asBinder() : null);
    }

    public static zzbe zza(zzbc zzbcVar, PendingIntent pendingIntent, zzai zzaiVar) {
        return new zzbe(1, zzbcVar, null, pendingIntent, null, zzaiVar != null ? zzaiVar.asBinder() : null);
    }

    public static zzbe zza(com.google.android.gms.location.zzap zzapVar, zzai zzaiVar) {
        return new zzbe(2, null, null, null, zzapVar.asBinder(), zzaiVar != null ? zzaiVar.asBinder() : null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbe(int i, zzbc zzbcVar, IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2, IBinder iBinder3) {
        com.google.android.gms.location.zzaq zza;
        com.google.android.gms.location.zzap zza2;
        this.zza = i;
        this.zzb = zzbcVar;
        zzai zzaiVar = null;
        if (iBinder == null) {
            zza = null;
        } else {
            zza = com.google.android.gms.location.zzat.zza(iBinder);
        }
        this.zzc = zza;
        this.zzd = pendingIntent;
        if (iBinder2 == null) {
            zza2 = null;
        } else {
            zza2 = com.google.android.gms.location.zzao.zza(iBinder2);
        }
        this.zze = zza2;
        if (iBinder3 != null && iBinder3 != null) {
            IInterface queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzaiVar = queryLocalInterface instanceof zzai ? (zzai) queryLocalInterface : new zzak(iBinder3);
        }
        this.zzf = zzaiVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        com.google.android.gms.location.zzaq zzaqVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzaqVar == null ? null : zzaqVar.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        com.google.android.gms.location.zzap zzapVar = this.zze;
        SafeParcelWriter.writeIBinder(parcel, 5, zzapVar == null ? null : zzapVar.asBinder(), false);
        zzai zzaiVar = this.zzf;
        SafeParcelWriter.writeIBinder(parcel, 6, zzaiVar != null ? zzaiVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}