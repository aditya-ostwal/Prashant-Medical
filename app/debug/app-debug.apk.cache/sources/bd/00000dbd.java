package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zak;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public abstract class zaa {
    final zac zaa;
    protected int zab;
    private int zac = 0;
    private boolean zad = false;
    private boolean zae = true;
    private boolean zaf = false;
    private boolean zag = true;

    public zaa(Uri uri, int i) {
        this.zab = 0;
        this.zaa = new zac(uri);
        this.zab = i;
    }

    protected abstract void zaa(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zaa(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zaa(Context context, zak zakVar) {
        if (this.zag) {
            zaa(null, false, true, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zaa(Context context, zak zakVar, boolean z) {
        Drawable drawable;
        int i = this.zab;
        if (i == 0) {
            drawable = null;
        } else {
            drawable = context.getResources().getDrawable(i);
        }
        zaa(drawable, z, false, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zaa(boolean z, boolean z2) {
        return (!this.zae || z2 || z) ? false : true;
    }
}