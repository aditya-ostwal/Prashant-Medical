package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public class zabo extends zal {
    private TaskCompletionSource<Void> zad;

    public static zabo zaa(Activity activity) {
        LifecycleFragment fragment = getFragment(activity);
        zabo zaboVar = (zabo) fragment.getCallbackOrNull("GmsAvailabilityHelper", zabo.class);
        if (zaboVar != null) {
            if (zaboVar.zad.getTask().isComplete()) {
                zaboVar.zad = new TaskCompletionSource<>();
            }
            return zaboVar;
        }
        return new zabo(fragment);
    }

    private zabo(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zad = new TaskCompletionSource<>();
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.zal
    public final void zaa(ConnectionResult connectionResult, int i) {
        this.zad.setException(ApiExceptionUtil.fromStatus(new Status(connectionResult.getErrorCode(), connectionResult.getErrorMessage(), connectionResult.getResolution())));
    }

    @Override // com.google.android.gms.common.api.internal.zal
    protected final void zaa() {
        Activity lifecycleActivity = this.mLifecycleFragment.getLifecycleActivity();
        if (lifecycleActivity == null) {
            this.zad.trySetException(new ApiException(new Status(8)));
            return;
        }
        int isGooglePlayServicesAvailable = this.zac.isGooglePlayServicesAvailable(lifecycleActivity);
        if (isGooglePlayServicesAvailable == 0) {
            this.zad.trySetResult(null);
        } else if (!this.zad.getTask().isComplete()) {
            zab(new ConnectionResult(isGooglePlayServicesAvailable, null), 0);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onDestroy() {
        super.onDestroy();
        this.zad.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    public final Task<Void> zac() {
        return this.zad.getTask();
    }
}