package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public final class zay extends RemoteCreator<zak> {
    private static final zay zaa = new zay();

    private zay() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zaa(Context context, int i, int i2) throws RemoteCreator.RemoteCreatorException {
        return zaa.zab(context, i, i2);
    }

    private final View zab(Context context, int i, int i2) throws RemoteCreator.RemoteCreatorException {
        try {
            zaw zawVar = new zaw(i, i2, null);
            return (View) ObjectWrapper.unwrap(getRemoteCreatorInstance(context).zaa(ObjectWrapper.wrap(context), zawVar));
        } catch (Exception e) {
            throw new RemoteCreator.RemoteCreatorException(new StringBuilder(64).append("Could not get button with size ").append(i).append(" and color ").append(i2).toString(), e);
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    public final /* synthetic */ zak getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        if (queryLocalInterface instanceof zak) {
            return (zak) queryLocalInterface;
        }
        return new zam(iBinder);
    }
}