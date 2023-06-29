package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

/* loaded from: classes.dex */
public final class MarkerOptions extends zzbej {
    public static final Parcelable.Creator<MarkerOptions> CREATOR = new zzh();
    private float mAlpha;
    private float mRotation;
    private String zzekd;
    private LatLng zzire;
    private float zzisb;
    private boolean zzisc;
    private float zzisl;
    private float zzism;
    private String zzisv;
    private BitmapDescriptor zzisw;
    private boolean zzisx;
    private boolean zzisy;
    private float zzisz;
    private float zzita;

    public MarkerOptions() {
        this.zzisl = 0.5f;
        this.zzism = 1.0f;
        this.zzisc = true;
        this.zzisy = false;
        this.mRotation = 0.0f;
        this.zzisz = 0.5f;
        this.zzita = 0.0f;
        this.mAlpha = 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MarkerOptions(LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2, boolean z3, float f3, float f4, float f5, float f6, float f7) {
        this.zzisl = 0.5f;
        this.zzism = 1.0f;
        this.zzisc = true;
        this.zzisy = false;
        this.mRotation = 0.0f;
        this.zzisz = 0.5f;
        this.zzita = 0.0f;
        this.mAlpha = 1.0f;
        this.zzire = latLng;
        this.zzekd = str;
        this.zzisv = str2;
        this.zzisw = iBinder == null ? null : new BitmapDescriptor(IObjectWrapper.zza.zzap(iBinder));
        this.zzisl = f;
        this.zzism = f2;
        this.zzisx = z;
        this.zzisc = z2;
        this.zzisy = z3;
        this.mRotation = f3;
        this.zzisz = f4;
        this.zzita = f5;
        this.mAlpha = f6;
        this.zzisb = f7;
    }

    public final MarkerOptions alpha(float f) {
        this.mAlpha = f;
        return this;
    }

    public final MarkerOptions anchor(float f, float f2) {
        this.zzisl = f;
        this.zzism = f2;
        return this;
    }

    public final MarkerOptions draggable(boolean z) {
        this.zzisx = z;
        return this;
    }

    public final MarkerOptions flat(boolean z) {
        this.zzisy = z;
        return this;
    }

    public final float getAlpha() {
        return this.mAlpha;
    }

    public final float getAnchorU() {
        return this.zzisl;
    }

    public final float getAnchorV() {
        return this.zzism;
    }

    public final BitmapDescriptor getIcon() {
        return this.zzisw;
    }

    public final float getInfoWindowAnchorU() {
        return this.zzisz;
    }

    public final float getInfoWindowAnchorV() {
        return this.zzita;
    }

    public final LatLng getPosition() {
        return this.zzire;
    }

    public final float getRotation() {
        return this.mRotation;
    }

    public final String getSnippet() {
        return this.zzisv;
    }

    public final String getTitle() {
        return this.zzekd;
    }

    public final float getZIndex() {
        return this.zzisb;
    }

    public final MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        this.zzisw = bitmapDescriptor;
        return this;
    }

    public final MarkerOptions infoWindowAnchor(float f, float f2) {
        this.zzisz = f;
        this.zzita = f2;
        return this;
    }

    public final boolean isDraggable() {
        return this.zzisx;
    }

    public final boolean isFlat() {
        return this.zzisy;
    }

    public final boolean isVisible() {
        return this.zzisc;
    }

    public final MarkerOptions position(LatLng latLng) {
        if (latLng != null) {
            this.zzire = latLng;
            return this;
        }
        throw new IllegalArgumentException("latlng cannot be null - a position is required.");
    }

    public final MarkerOptions rotation(float f) {
        this.mRotation = f;
        return this;
    }

    public final MarkerOptions snippet(String str) {
        this.zzisv = str;
        return this;
    }

    public final MarkerOptions title(String str) {
        this.zzekd = str;
        return this;
    }

    public final MarkerOptions visible(boolean z) {
        this.zzisc = z;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, getPosition(), i, false);
        zzbem.zza(parcel, 3, getTitle(), false);
        zzbem.zza(parcel, 4, getSnippet(), false);
        BitmapDescriptor bitmapDescriptor = this.zzisw;
        zzbem.zza(parcel, 5, bitmapDescriptor == null ? null : bitmapDescriptor.zzavo().asBinder(), false);
        zzbem.zza(parcel, 6, getAnchorU());
        zzbem.zza(parcel, 7, getAnchorV());
        zzbem.zza(parcel, 8, isDraggable());
        zzbem.zza(parcel, 9, isVisible());
        zzbem.zza(parcel, 10, isFlat());
        zzbem.zza(parcel, 11, getRotation());
        zzbem.zza(parcel, 12, getInfoWindowAnchorU());
        zzbem.zza(parcel, 13, getInfoWindowAnchorV());
        zzbem.zza(parcel, 14, getAlpha());
        zzbem.zza(parcel, 15, getZIndex());
        zzbem.zzai(parcel, zze);
    }

    public final MarkerOptions zIndex(float f) {
        this.zzisb = f;
        return this;
    }
}