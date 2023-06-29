package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbek;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zzk implements Parcelable.Creator<PolygonOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PolygonOptions createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = null;
        float f = 0.0f;
        ArrayList arrayList3 = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = 0;
        float f2 = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    arrayList2 = zzbek.zzc(parcel, readInt, LatLng.CREATOR);
                    break;
                case 3:
                    zzbek.zza(parcel, readInt, arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    f2 = zzbek.zzl(parcel, readInt);
                    break;
                case 5:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 6:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                case 7:
                    f = zzbek.zzl(parcel, readInt);
                    break;
                case 8:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 9:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 10:
                    z3 = zzbek.zzc(parcel, readInt);
                    break;
                case 11:
                    i3 = zzbek.zzg(parcel, readInt);
                    break;
                case 12:
                    arrayList3 = zzbek.zzc(parcel, readInt, PatternItem.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new PolygonOptions(arrayList2, arrayList, f2, i, i2, f, z, z2, z3, i3, arrayList3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PolygonOptions[] newArray(int i) {
        return new PolygonOptions[i];
    }
}