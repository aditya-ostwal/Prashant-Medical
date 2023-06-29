package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.Arrays;

/* loaded from: classes.dex */
public class StreetViewPanoramaLocation extends zzbej {
    public static final Parcelable.Creator<StreetViewPanoramaLocation> CREATOR = new zzo();
    public final StreetViewPanoramaLink[] links;
    public final String panoId;
    public final LatLng position;

    public StreetViewPanoramaLocation(StreetViewPanoramaLink[] streetViewPanoramaLinkArr, LatLng latLng, String str) {
        this.links = streetViewPanoramaLinkArr;
        this.position = latLng;
        this.panoId = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StreetViewPanoramaLocation) {
            StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) obj;
            return this.panoId.equals(streetViewPanoramaLocation.panoId) && this.position.equals(streetViewPanoramaLocation.position);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.position, this.panoId});
    }

    public String toString() {
        return zzbg.zzw(this).zzg("panoId", this.panoId).zzg("position", this.position.toString()).toString();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.os.Parcelable[], com.google.android.gms.maps.model.StreetViewPanoramaLink[]] */
    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, (Parcelable[]) this.links, i, false);
        zzbem.zza(parcel, 3, this.position, i, false);
        zzbem.zza(parcel, 4, this.panoId, false);
        zzbem.zzai(parcel, zze);
    }
}