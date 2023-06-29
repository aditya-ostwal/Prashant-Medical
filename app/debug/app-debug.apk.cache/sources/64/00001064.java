package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class PolylineOptions extends zzbej {
    public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzl();
    private int mColor;
    private float zzisb;
    private boolean zzisc;
    private boolean zzisd;
    private float zzisi;
    private final List<LatLng> zzitd;
    private boolean zzitf;
    private Cap zziti;
    private Cap zzitj;
    private int zzitk;
    private List<PatternItem> zzitl;

    public PolylineOptions() {
        this.zzisi = 10.0f;
        this.mColor = ViewCompat.MEASURED_STATE_MASK;
        this.zzisb = 0.0f;
        this.zzisc = true;
        this.zzitf = false;
        this.zzisd = false;
        this.zziti = new ButtCap();
        this.zzitj = new ButtCap();
        this.zzitk = 0;
        this.zzitl = null;
        this.zzitd = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PolylineOptions(List list, float f, int i, float f2, boolean z, boolean z2, boolean z3, Cap cap, Cap cap2, int i2, List<PatternItem> list2) {
        this.zzisi = 10.0f;
        this.mColor = ViewCompat.MEASURED_STATE_MASK;
        this.zzisb = 0.0f;
        this.zzisc = true;
        this.zzitf = false;
        this.zzisd = false;
        this.zziti = new ButtCap();
        this.zzitj = new ButtCap();
        this.zzitk = 0;
        this.zzitl = null;
        this.zzitd = list;
        this.zzisi = f;
        this.mColor = i;
        this.zzisb = f2;
        this.zzisc = z;
        this.zzitf = z2;
        this.zzisd = z3;
        if (cap != null) {
            this.zziti = cap;
        }
        if (cap2 != null) {
            this.zzitj = cap2;
        }
        this.zzitk = i2;
        this.zzitl = list2;
    }

    public final PolylineOptions add(LatLng latLng) {
        this.zzitd.add(latLng);
        return this;
    }

    public final PolylineOptions add(LatLng... latLngArr) {
        this.zzitd.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public final PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng latLng : iterable) {
            this.zzitd.add(latLng);
        }
        return this;
    }

    public final PolylineOptions clickable(boolean z) {
        this.zzisd = z;
        return this;
    }

    public final PolylineOptions color(int i) {
        this.mColor = i;
        return this;
    }

    public final PolylineOptions endCap(Cap cap) {
        this.zzitj = (Cap) zzbq.checkNotNull(cap, "endCap must not be null");
        return this;
    }

    public final PolylineOptions geodesic(boolean z) {
        this.zzitf = z;
        return this;
    }

    public final int getColor() {
        return this.mColor;
    }

    public final Cap getEndCap() {
        return this.zzitj;
    }

    public final int getJointType() {
        return this.zzitk;
    }

    public final List<PatternItem> getPattern() {
        return this.zzitl;
    }

    public final List<LatLng> getPoints() {
        return this.zzitd;
    }

    public final Cap getStartCap() {
        return this.zziti;
    }

    public final float getWidth() {
        return this.zzisi;
    }

    public final float getZIndex() {
        return this.zzisb;
    }

    public final boolean isClickable() {
        return this.zzisd;
    }

    public final boolean isGeodesic() {
        return this.zzitf;
    }

    public final boolean isVisible() {
        return this.zzisc;
    }

    public final PolylineOptions jointType(int i) {
        this.zzitk = i;
        return this;
    }

    public final PolylineOptions pattern(List<PatternItem> list) {
        this.zzitl = list;
        return this;
    }

    public final PolylineOptions startCap(Cap cap) {
        this.zziti = (Cap) zzbq.checkNotNull(cap, "startCap must not be null");
        return this;
    }

    public final PolylineOptions visible(boolean z) {
        this.zzisc = z;
        return this;
    }

    public final PolylineOptions width(float f) {
        this.zzisi = f;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 2, getPoints(), false);
        zzbem.zza(parcel, 3, getWidth());
        zzbem.zzc(parcel, 4, getColor());
        zzbem.zza(parcel, 5, getZIndex());
        zzbem.zza(parcel, 6, isVisible());
        zzbem.zza(parcel, 7, isGeodesic());
        zzbem.zza(parcel, 8, isClickable());
        zzbem.zza(parcel, 9, getStartCap(), i, false);
        zzbem.zza(parcel, 10, getEndCap(), i, false);
        zzbem.zzc(parcel, 11, getJointType());
        zzbem.zzc(parcel, 12, getPattern(), false);
        zzbem.zzai(parcel, zze);
    }

    public final PolylineOptions zIndex(float f) {
        this.zzisb = f;
        return this;
    }
}