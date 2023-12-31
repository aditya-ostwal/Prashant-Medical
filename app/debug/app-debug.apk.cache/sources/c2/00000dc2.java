package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.IAccountAccessor;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* loaded from: classes.dex */
public class AccountAccessor extends IAccountAccessor.Stub {
    public static Account getAccountBinderSafe(IAccountAccessor iAccountAccessor) {
        Account account;
        if (iAccountAccessor != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = iAccountAccessor.zza();
            } catch (RemoteException e) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
            return (Account) Preconditions.checkNotNull(account);
        }
        account = null;
        return (Account) Preconditions.checkNotNull(account);
    }

    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public final Account zza() {
        throw new NoSuchMethodError();
    }

    public boolean equals(Object obj) {
        throw new NoSuchMethodError();
    }
}