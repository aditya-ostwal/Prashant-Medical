package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public class zai extends zal {
    private final SparseArray<zaa> zad;

    public static zai zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = getFragment(lifecycleActivity);
        zai zaiVar = (zai) fragment.getCallbackOrNull("AutoManageHelper", zai.class);
        if (zaiVar != null) {
            return zaiVar;
        }
        return new zai(fragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    /* loaded from: classes.dex */
    public class zaa implements GoogleApiClient.OnConnectionFailedListener {
        public final int zaa;
        public final GoogleApiClient zab;
        public final GoogleApiClient.OnConnectionFailedListener zac;

        public zaa(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zaa = i;
            this.zab = googleApiClient;
            this.zac = onConnectionFailedListener;
        }

        @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            zai.this.zab(connectionResult, this.zaa);
        }
    }

    private zai(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zad = new SparseArray<>();
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public final void zaa(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        Preconditions.checkState(this.zad.indexOfKey(i) < 0, new StringBuilder(54).append("Already managing a GoogleApiClient with id ").append(i).toString());
        zak zakVar = this.zab.get();
        boolean z = this.zaa;
        String valueOf = String.valueOf(zakVar);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 49).append("starting AutoManage for client ").append(i).append(" ").append(z).append(" ").append(valueOf).toString());
        zaa zaaVar = new zaa(i, googleApiClient, onConnectionFailedListener);
        googleApiClient.registerConnectionFailedListener(zaaVar);
        this.zad.put(i, zaaVar);
        if (this.zaa && zakVar == null) {
            String valueOf2 = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf2).length() + 11).append("connecting ").append(valueOf2).toString());
            googleApiClient.connect();
        }
    }

    public final void zaa(int i) {
        zaa zaaVar = this.zad.get(i);
        this.zad.remove(i);
        if (zaaVar != null) {
            zaaVar.zab.unregisterConnectionFailedListener(zaaVar);
            zaaVar.zab.disconnect();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zal, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        boolean z = this.zaa;
        String valueOf = String.valueOf(this.zad);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(" ").append(valueOf).toString());
        if (this.zab.get() == null) {
            for (int i = 0; i < this.zad.size(); i++) {
                zaa zab = zab(i);
                if (zab != null) {
                    zab.zab.connect();
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zal, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.zad.size(); i++) {
            zaa zab = zab(i);
            if (zab != null) {
                zab.zab.disconnect();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zad.size(); i++) {
            zaa zab = zab(i);
            if (zab != null) {
                printWriter.append((CharSequence) str).append("GoogleApiClient #").print(zab.zaa);
                printWriter.println(":");
                zab.zab.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.zal
    public final void zaa(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaa zaaVar = this.zad.get(i);
        if (zaaVar != null) {
            zaa(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zaaVar.zac;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zal
    protected final void zaa() {
        for (int i = 0; i < this.zad.size(); i++) {
            zaa zab = zab(i);
            if (zab != null) {
                zab.zab.connect();
            }
        }
    }

    private final zaa zab(int i) {
        if (this.zad.size() <= i) {
            return null;
        }
        SparseArray<zaa> sparseArray = this.zad;
        return sparseArray.get(sparseArray.keyAt(i));
    }
}