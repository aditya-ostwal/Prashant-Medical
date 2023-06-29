package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
final class zaak extends com.google.android.gms.signin.internal.zad {
    private final WeakReference<zaaf> zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaak(zaaf zaafVar) {
        this.zaa = new WeakReference<>(zaafVar);
    }

    @Override // com.google.android.gms.signin.internal.zad, com.google.android.gms.signin.internal.zac
    public final void zaa(com.google.android.gms.signin.internal.zam zamVar) {
        zaaz zaazVar;
        zaaf zaafVar = this.zaa.get();
        if (zaafVar == null) {
            return;
        }
        zaazVar = zaafVar.zaa;
        zaazVar.zaa(new zaan(this, zaafVar, zaafVar, zamVar));
    }
}