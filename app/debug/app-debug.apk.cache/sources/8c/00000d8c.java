package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public final class zax implements PendingResult.StatusListener {
    private final /* synthetic */ BasePendingResult zaa;
    private final /* synthetic */ zav zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zax(zav zavVar, BasePendingResult basePendingResult) {
        this.zab = zavVar;
        this.zaa = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        Map map;
        map = this.zab.zaa;
        map.remove(this.zaa);
    }
}