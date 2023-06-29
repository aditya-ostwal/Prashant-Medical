package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
/* loaded from: classes.dex */
public class GeofencingRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzal();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    private final List<com.google.android.gms.internal.location.zzbg> zza;
    private final int zzb;
    private final String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeofencingRequest(List<com.google.android.gms.internal.location.zzbg> list, int i, String str) {
        this.zza = list;
        this.zzb = i;
        this.zzc = str;
    }

    /* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private final List<com.google.android.gms.internal.location.zzbg> zza = new ArrayList();
        private int zzb = 5;
        private String zzc = "";

        public final Builder addGeofence(Geofence geofence) {
            Preconditions.checkNotNull(geofence, "geofence can't be null.");
            Preconditions.checkArgument(geofence instanceof com.google.android.gms.internal.location.zzbg, "Geofence must be created using Geofence.Builder.");
            this.zza.add((com.google.android.gms.internal.location.zzbg) geofence);
            return this;
        }

        public final Builder addGeofences(List<Geofence> list) {
            if (list == null || list.isEmpty()) {
                return this;
            }
            for (Geofence geofence : list) {
                if (geofence != null) {
                    addGeofence(geofence);
                }
            }
            return this;
        }

        public final Builder setInitialTrigger(int i) {
            this.zzb = i & 7;
            return this;
        }

        public final GeofencingRequest build() {
            Preconditions.checkArgument(!this.zza.isEmpty(), "No geofence has been added to this request.");
            return new GeofencingRequest(this.zza, this.zzb, this.zzc);
        }
    }

    public List<Geofence> getGeofences() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zza);
        return arrayList;
    }

    public int getInitialTrigger() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeInt(parcel, 2, getInitialTrigger());
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GeofencingRequest[");
        sb.append("geofences=");
        sb.append(this.zza);
        sb.append(new StringBuilder(30).append(", initialTrigger=").append(this.zzb).append(", ").toString());
        String valueOf = String.valueOf(this.zzc);
        sb.append(valueOf.length() != 0 ? "tag=".concat(valueOf) : new String("tag="));
        sb.append("]");
        return sb.toString();
    }
}