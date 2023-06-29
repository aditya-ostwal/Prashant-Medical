package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzl> CREATOR = new zzo();
    private int zza;
    private zzj zzb;
    private com.google.android.gms.location.zzak zzc;
    private zzai zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(int i, zzj zzjVar, IBinder iBinder, IBinder iBinder2) {
        com.google.android.gms.location.zzak zza;
        this.zza = i;
        this.zzb = zzjVar;
        zzai zzaiVar = null;
        if (iBinder == null) {
            zza = null;
        } else {
            zza = com.google.android.gms.location.zzan.zza(iBinder);
        }
        this.zzc = zza;
        if (iBinder2 != null && iBinder2 != null) {
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzaiVar = queryLocalInterface instanceof zzai ? (zzai) queryLocalInterface : new zzak(iBinder2);
        }
        this.zzd = zzaiVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        com.google.android.gms.location.zzak zzakVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzakVar == null ? null : zzakVar.asBinder(), false);
        zzai zzaiVar = this.zzd;
        SafeParcelWriter.writeIBinder(parcel, 4, zzaiVar != null ? zzaiVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}