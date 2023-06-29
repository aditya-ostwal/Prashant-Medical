package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.ILocationSourceDelegate;

/* loaded from: classes.dex */
final class zzl extends ILocationSourceDelegate.zza {
    private /* synthetic */ LocationSource zzipa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(GoogleMap googleMap, LocationSource locationSource) {
        this.zzipa = locationSource;
    }

    @Override // com.google.android.gms.maps.internal.ILocationSourceDelegate
    public final void activate(com.google.android.gms.maps.internal.zzah zzahVar) {
        this.zzipa.activate(new zzm(this, zzahVar));
    }

    @Override // com.google.android.gms.maps.internal.ILocationSourceDelegate
    public final void deactivate() {
        this.zzipa.deactivate();
    }
}