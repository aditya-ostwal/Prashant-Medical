package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.maps.model.internal.zzaa;
import com.google.android.gms.maps.model.internal.zzz;

/* loaded from: classes.dex */
public final class TileOverlayOptions extends zzbej {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzt();
    private float zzisb;
    private boolean zzisc;
    private float zzisk;
    private zzz zzito;
    private TileProvider zzitp;
    private boolean zzitq;

    public TileOverlayOptions() {
        this.zzisc = true;
        this.zzitq = true;
        this.zzisk = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TileOverlayOptions(IBinder iBinder, boolean z, float f, boolean z2, float f2) {
        this.zzisc = true;
        this.zzitq = true;
        this.zzisk = 0.0f;
        zzz zzbn = zzaa.zzbn(iBinder);
        this.zzito = zzbn;
        this.zzitp = zzbn == null ? null : new zzr(this);
        this.zzisc = z;
        this.zzisb = f;
        this.zzitq = z2;
        this.zzisk = f2;
    }

    public final TileOverlayOptions fadeIn(boolean z) {
        this.zzitq = z;
        return this;
    }

    public final boolean getFadeIn() {
        return this.zzitq;
    }

    public final TileProvider getTileProvider() {
        return this.zzitp;
    }

    public final float getTransparency() {
        return this.zzisk;
    }

    public final float getZIndex() {
        return this.zzisb;
    }

    public final boolean isVisible() {
        return this.zzisc;
    }

    public final TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.zzitp = tileProvider;
        this.zzito = tileProvider == null ? null : new zzs(this, tileProvider);
        return this;
    }

    public final TileOverlayOptions transparency(float f) {
        zzbq.checkArgument(f >= 0.0f && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzisk = f;
        return this;
    }

    public final TileOverlayOptions visible(boolean z) {
        this.zzisc = z;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzito.asBinder(), false);
        zzbem.zza(parcel, 3, isVisible());
        zzbem.zza(parcel, 4, getZIndex());
        zzbem.zza(parcel, 5, getFadeIn());
        zzbem.zza(parcel, 6, getTransparency());
        zzbem.zzai(parcel, zze);
    }

    public final TileOverlayOptions zIndex(float f) {
        this.zzisb = f;
        return this;
    }
}