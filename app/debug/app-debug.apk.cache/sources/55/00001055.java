package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

/* loaded from: classes.dex */
public final class GroundOverlayOptions extends zzbej {
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
    public static final float NO_DIMENSION = -1.0f;
    private LatLngBounds zziog;
    private float zzirw;
    private float zzisb;
    private boolean zzisc;
    private boolean zzisd;
    private BitmapDescriptor zzisg;
    private LatLng zzish;
    private float zzisi;
    private float zzisj;
    private float zzisk;
    private float zzisl;
    private float zzism;

    public GroundOverlayOptions() {
        this.zzisc = true;
        this.zzisk = 0.0f;
        this.zzisl = 0.5f;
        this.zzism = 0.5f;
        this.zzisd = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GroundOverlayOptions(IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7, boolean z2) {
        this.zzisc = true;
        this.zzisk = 0.0f;
        this.zzisl = 0.5f;
        this.zzism = 0.5f;
        this.zzisd = false;
        this.zzisg = new BitmapDescriptor(IObjectWrapper.zza.zzap(iBinder));
        this.zzish = latLng;
        this.zzisi = f;
        this.zzisj = f2;
        this.zziog = latLngBounds;
        this.zzirw = f3;
        this.zzisb = f4;
        this.zzisc = z;
        this.zzisk = f5;
        this.zzisl = f6;
        this.zzism = f7;
        this.zzisd = z2;
    }

    private final GroundOverlayOptions zza(LatLng latLng, float f, float f2) {
        this.zzish = latLng;
        this.zzisi = f;
        this.zzisj = f2;
        return this;
    }

    public final GroundOverlayOptions anchor(float f, float f2) {
        this.zzisl = f;
        this.zzism = f2;
        return this;
    }

    public final GroundOverlayOptions bearing(float f) {
        this.zzirw = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public final GroundOverlayOptions clickable(boolean z) {
        this.zzisd = z;
        return this;
    }

    public final float getAnchorU() {
        return this.zzisl;
    }

    public final float getAnchorV() {
        return this.zzism;
    }

    public final float getBearing() {
        return this.zzirw;
    }

    public final LatLngBounds getBounds() {
        return this.zziog;
    }

    public final float getHeight() {
        return this.zzisj;
    }

    public final BitmapDescriptor getImage() {
        return this.zzisg;
    }

    public final LatLng getLocation() {
        return this.zzish;
    }

    public final float getTransparency() {
        return this.zzisk;
    }

    public final float getWidth() {
        return this.zzisi;
    }

    public final float getZIndex() {
        return this.zzisb;
    }

    public final GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        zzbq.checkNotNull(bitmapDescriptor, "imageDescriptor must not be null");
        this.zzisg = bitmapDescriptor;
        return this;
    }

    public final boolean isClickable() {
        return this.zzisd;
    }

    public final boolean isVisible() {
        return this.zzisc;
    }

    public final GroundOverlayOptions position(LatLng latLng, float f) {
        zzbq.zza(this.zziog == null, "Position has already been set using positionFromBounds");
        zzbq.checkArgument(latLng != null, "Location must be specified");
        zzbq.checkArgument(f >= 0.0f, "Width must be non-negative");
        return zza(latLng, f, -1.0f);
    }

    public final GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        zzbq.zza(this.zziog == null, "Position has already been set using positionFromBounds");
        zzbq.checkArgument(latLng != null, "Location must be specified");
        zzbq.checkArgument(f >= 0.0f, "Width must be non-negative");
        zzbq.checkArgument(f2 >= 0.0f, "Height must be non-negative");
        return zza(latLng, f, f2);
    }

    public final GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        LatLng latLng = this.zzish;
        boolean z = latLng == null;
        String valueOf = String.valueOf(latLng);
        zzbq.zza(z, new StringBuilder(String.valueOf(valueOf).length() + 46).append("Position has already been set using position: ").append(valueOf).toString());
        this.zziog = latLngBounds;
        return this;
    }

    public final GroundOverlayOptions transparency(float f) {
        zzbq.checkArgument(f >= 0.0f && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzisk = f;
        return this;
    }

    public final GroundOverlayOptions visible(boolean z) {
        this.zzisc = z;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzisg.zzavo().asBinder(), false);
        zzbem.zza(parcel, 3, getLocation(), i, false);
        zzbem.zza(parcel, 4, getWidth());
        zzbem.zza(parcel, 5, getHeight());
        zzbem.zza(parcel, 6, getBounds(), i, false);
        zzbem.zza(parcel, 7, getBearing());
        zzbem.zza(parcel, 8, getZIndex());
        zzbem.zza(parcel, 9, isVisible());
        zzbem.zza(parcel, 10, getTransparency());
        zzbem.zza(parcel, 11, getAnchorU());
        zzbem.zza(parcel, 12, getAnchorV());
        zzbem.zza(parcel, 13, isClickable());
        zzbem.zzai(parcel, zze);
    }

    public final GroundOverlayOptions zIndex(float f) {
        this.zzisb = f;
        return this;
    }
}