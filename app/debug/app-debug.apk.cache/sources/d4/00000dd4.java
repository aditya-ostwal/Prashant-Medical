package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public class ClientIdentity extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ClientIdentity> CREATOR = new zaa();
    private final int zaa;
    private final String zab;

    public ClientIdentity(int i, String str) {
        this.zaa = i;
        this.zab = str;
    }

    public int hashCode() {
        return this.zaa;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ClientIdentity) {
            ClientIdentity clientIdentity = (ClientIdentity) obj;
            return clientIdentity.zaa == this.zaa && Objects.equal(clientIdentity.zab, this.zab);
        }
        return false;
    }

    public String toString() {
        int i = this.zaa;
        String str = this.zab;
        return new StringBuilder(String.valueOf(str).length() + 12).append(i).append(":").append(str).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeString(parcel, 2, this.zab, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}