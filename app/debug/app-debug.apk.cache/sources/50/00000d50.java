package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
final class zabd implements Runnable {
    private final /* synthetic */ GoogleApiManager.zaa zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabd(GoogleApiManager.zaa zaaVar) {
        this.zaa = zaaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaa.zam();
    }
}