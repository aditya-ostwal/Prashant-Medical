package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class StreetViewPanoramaView extends FrameLayout {
    private final zzb zzirj;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class zza implements StreetViewLifecycleDelegate {
        private final ViewGroup zziqm;
        private final IStreetViewPanoramaViewDelegate zzirk;
        private View zzirl;

        public zza(ViewGroup viewGroup, IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate) {
            this.zzirk = (IStreetViewPanoramaViewDelegate) zzbq.checkNotNull(iStreetViewPanoramaViewDelegate);
            this.zziqm = (ViewGroup) zzbq.checkNotNull(viewGroup);
        }

        @Override // com.google.android.gms.maps.internal.StreetViewLifecycleDelegate
        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            try {
                this.zzirk.getStreetViewPanoramaAsync(new zzaj(this, onStreetViewPanoramaReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onCreate(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zzd(bundle, bundle2);
                this.zzirk.onCreate(bundle2);
                zzby.zzd(bundle2, bundle);
                this.zzirl = (View) com.google.android.gms.dynamic.zzn.zzx(this.zzirk.getView());
                this.zziqm.removeAllViews();
                this.zziqm.addView(this.zzirl);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroy() {
            try {
                this.zzirk.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onLowMemory() {
            try {
                this.zzirk.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onPause() {
            try {
                this.zzirk.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onResume() {
            try {
                this.zzirk.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onSaveInstanceState(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zzd(bundle, bundle2);
                this.zzirk.onSaveInstanceState(bundle2);
                zzby.zzd(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStart() {
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStop() {
        }
    }

    /* loaded from: classes.dex */
    static class zzb extends com.google.android.gms.dynamic.zza<zza> {
        private com.google.android.gms.dynamic.zzo<zza> zziqj;
        private final ViewGroup zziqp;
        private final Context zziqq;
        private final List<OnStreetViewPanoramaReadyCallback> zzirb = new ArrayList();
        private final StreetViewPanoramaOptions zzirm;

        zzb(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            this.zziqp = viewGroup;
            this.zziqq = context;
            this.zzirm = streetViewPanoramaOptions;
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            if (zzapl() != null) {
                ((zza) zzapl()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            } else {
                this.zzirb.add(onStreetViewPanoramaReadyCallback);
            }
        }

        protected final void zza(com.google.android.gms.dynamic.zzo<zza> zzoVar) {
            this.zziqj = zzoVar;
            if (zzoVar == null || zzapl() != null) {
                return;
            }
            try {
                MapsInitializer.initialize(this.zziqq);
                this.zziqj.zza(new zza(this.zziqp, zzbz.zzdq(this.zziqq).zza(com.google.android.gms.dynamic.zzn.zzy(this.zziqq), this.zzirm)));
                for (OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback : this.zzirb) {
                    ((zza) zzapl()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
                }
                this.zzirb.clear();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            } catch (GooglePlayServicesNotAvailableException e2) {
            }
        }
    }

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.zzirj = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzirj = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzirj = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.zzirj = new zzb(this, context, streetViewPanoramaOptions);
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        zzbq.zzga("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzirj.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }

    public final void onCreate(Bundle bundle) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            this.zzirj.onCreate(bundle);
            if (this.zzirj.zzapl() == null) {
                com.google.android.gms.dynamic.zza.zzb(this);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public final void onDestroy() {
        this.zzirj.onDestroy();
    }

    public final void onLowMemory() {
        this.zzirj.onLowMemory();
    }

    public final void onPause() {
        this.zzirj.onPause();
    }

    public final void onResume() {
        this.zzirj.onResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzirj.onSaveInstanceState(bundle);
    }
}