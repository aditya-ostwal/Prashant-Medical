package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

/* loaded from: classes.dex */
public final class PointOfInterest extends zzbej {
    public static final Parcelable.Creator<PointOfInterest> CREATOR = new zzj();
    public final LatLng latLng;
    public final String name;
    public final String placeId;

    public PointOfInterest(LatLng latLng, String str, String str2) {
        this.latLng = latLng;
        this.placeId = str;
        this.name = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.latLng, i, false);
        zzbem.zza(parcel, 3, this.placeId, false);
        zzbem.zza(parcel, 4, this.name, false);
        zzbem.zzai(parcel, zze);
    }
}