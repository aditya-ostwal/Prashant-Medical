package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbek;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes.dex */
public final class zzaa implements Parcelable.Creator<GoogleMapOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleMapOptions createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        CameraPosition cameraPosition = null;
        Float f = null;
        Float f2 = null;
        LatLngBounds latLngBounds = null;
        byte b = -1;
        byte b2 = -1;
        byte b3 = -1;
        byte b4 = -1;
        byte b5 = -1;
        byte b6 = -1;
        byte b7 = -1;
        byte b8 = -1;
        byte b9 = -1;
        byte b10 = -1;
        byte b11 = -1;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    b = zzbek.zze(parcel, readInt);
                    break;
                case 3:
                    b2 = zzbek.zze(parcel, readInt);
                    break;
                case 4:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 5:
                    cameraPosition = (CameraPosition) zzbek.zza(parcel, readInt, CameraPosition.CREATOR);
                    break;
                case 6:
                    b3 = zzbek.zze(parcel, readInt);
                    break;
                case 7:
                    b4 = zzbek.zze(parcel, readInt);
                    break;
                case 8:
                    b5 = zzbek.zze(parcel, readInt);
                    break;
                case 9:
                    b6 = zzbek.zze(parcel, readInt);
                    break;
                case 10:
                    b7 = zzbek.zze(parcel, readInt);
                    break;
                case 11:
                    b8 = zzbek.zze(parcel, readInt);
                    break;
                case 12:
                    b9 = zzbek.zze(parcel, readInt);
                    break;
                case 13:
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
                case 14:
                    b10 = zzbek.zze(parcel, readInt);
                    break;
                case 15:
                    b11 = zzbek.zze(parcel, readInt);
                    break;
                case 16:
                    f = zzbek.zzm(parcel, readInt);
                    break;
                case 17:
                    f2 = zzbek.zzm(parcel, readInt);
                    break;
                case 18:
                    latLngBounds = (LatLngBounds) zzbek.zza(parcel, readInt, LatLngBounds.CREATOR);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new GoogleMapOptions(b, b2, i, cameraPosition, b3, b4, b5, b6, b7, b8, b9, b10, b11, f, f2, latLngBounds);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleMapOptions[] newArray(int i) {
        return new GoogleMapOptions[i];
    }
}