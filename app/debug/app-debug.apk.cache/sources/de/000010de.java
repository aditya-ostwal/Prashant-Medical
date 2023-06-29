package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.zas;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zak> CREATOR = new zaj();
    private final int zaa;
    private final zas zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zak(int i, zas zasVar) {
        this.zaa = i;
        this.zab = zasVar;
    }

    public zak(zas zasVar) {
        this(1, zasVar);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zab, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}