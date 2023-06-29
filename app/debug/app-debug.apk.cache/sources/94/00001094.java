package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbek;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zzc implements Parcelable.Creator<CircleOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CircleOptions createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        float f = 0.0f;
        float f2 = 0.0f;
        LatLng latLng = null;
        ArrayList arrayList = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        double d = 0.0d;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    latLng = (LatLng) zzbek.zza(parcel, readInt, LatLng.CREATOR);
                    break;
                case 3:
                    d = zzbek.zzn(parcel, readInt);
                    break;
                case 4:
                    f = zzbek.zzl(parcel, readInt);
                    break;
                case 5:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 6:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                case 7:
                    f2 = zzbek.zzl(parcel, readInt);
                    break;
                case 8:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 9:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 10:
                    arrayList = zzbek.zzc(parcel, readInt, PatternItem.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new CircleOptions(latLng, d, f, i, i2, f2, z, z2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CircleOptions[] newArray(int i) {
        return new CircleOptions[i];
    }
}