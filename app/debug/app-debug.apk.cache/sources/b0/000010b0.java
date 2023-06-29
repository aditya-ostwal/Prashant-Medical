package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbek;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* loaded from: classes.dex */
public final class zzai implements Parcelable.Creator<StreetViewPanoramaOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StreetViewPanoramaOptions createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        String str = null;
        LatLng latLng = null;
        Integer num = null;
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        byte b5 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) zzbek.zza(parcel, readInt, StreetViewPanoramaCamera.CREATOR);
                    break;
                case 3:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 4:
                    latLng = (LatLng) zzbek.zza(parcel, readInt, LatLng.CREATOR);
                    break;
                case 5:
                    num = zzbek.zzh(parcel, readInt);
                    break;
                case 6:
                    b = zzbek.zze(parcel, readInt);
                    break;
                case 7:
                    b2 = zzbek.zze(parcel, readInt);
                    break;
                case 8:
                    b3 = zzbek.zze(parcel, readInt);
                    break;
                case 9:
                    b4 = zzbek.zze(parcel, readInt);
                    break;
                case 10:
                    b5 = zzbek.zze(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new StreetViewPanoramaOptions(streetViewPanoramaCamera, str, latLng, num, b, b2, b3, b4, b5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StreetViewPanoramaOptions[] newArray(int i) {
        return new StreetViewPanoramaOptions[i];
    }
}