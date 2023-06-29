package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zal;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.internal.base.zaq;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
/* loaded from: classes.dex */
public final class ImageManager {
    private static final Object zaa = new Object();
    private static HashSet<Uri> zab = new HashSet<>();
    private static ImageManager zac;
    private final Context zad;
    private final Handler zae = new zap(Looper.getMainLooper());
    private final ExecutorService zaf = zal.zaa().zaa(4, zaq.zab);
    private final zaa zag = null;
    private final zak zah = new zak();
    private final Map<com.google.android.gms.common.images.zaa, ImageReceiver> zai = new HashMap();
    private final Map<Uri, ImageReceiver> zaj = new HashMap();
    private final Map<Uri, Long> zak = new HashMap();

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    /* loaded from: classes.dex */
    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    public static ImageManager create(Context context) {
        if (zac == null) {
            zac = new ImageManager(context, false);
        }
        return zac;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    /* loaded from: classes.dex */
    public static final class zaa extends LruCache<com.google.android.gms.common.images.zac, Bitmap> {
        @Override // androidx.collection.LruCache
        protected final /* synthetic */ int sizeOf(com.google.android.gms.common.images.zac zacVar, Bitmap bitmap) {
            Bitmap bitmap2 = bitmap;
            return bitmap2.getHeight() * bitmap2.getRowBytes();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.collection.LruCache
        public final /* synthetic */ void entryRemoved(boolean z, com.google.android.gms.common.images.zac zacVar, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zacVar, bitmap, bitmap2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    /* loaded from: classes.dex */
    public final class zad implements Runnable {
        private final com.google.android.gms.common.images.zaa zaa;

        public zad(com.google.android.gms.common.images.zaa zaaVar) {
            this.zaa = zaaVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zai.get(this.zaa);
            if (imageReceiver != null) {
                ImageManager.this.zai.remove(this.zaa);
                imageReceiver.zab(this.zaa);
            }
            com.google.android.gms.common.images.zac zacVar = this.zaa.zaa;
            if (zacVar.zaa == null) {
                this.zaa.zaa(ImageManager.this.zad, ImageManager.this.zah, true);
                return;
            }
            Bitmap zaa = ImageManager.this.zaa(zacVar);
            if (zaa != null) {
                this.zaa.zaa(ImageManager.this.zad, zaa, true);
                return;
            }
            Long l = (Long) ImageManager.this.zak.get(zacVar.zaa);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zaa.zaa(ImageManager.this.zad, ImageManager.this.zah, true);
                    return;
                }
                ImageManager.this.zak.remove(zacVar.zaa);
            }
            this.zaa.zaa(ImageManager.this.zad, ImageManager.this.zah);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.zaj.get(zacVar.zaa);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(zacVar.zaa);
                ImageManager.this.zaj.put(zacVar.zaa, imageReceiver2);
            }
            imageReceiver2.zaa(this.zaa);
            if (!(this.zaa instanceof com.google.android.gms.common.images.zad)) {
                ImageManager.this.zai.put(this.zaa, imageReceiver2);
            }
            synchronized (ImageManager.zaa) {
                if (!ImageManager.zab.contains(zacVar.zaa)) {
                    ImageManager.zab.add(zacVar.zaa);
                    imageReceiver2.zaa();
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    /* loaded from: classes.dex */
    private final class zab implements Runnable {
        private final Uri zaa;
        private final ParcelFileDescriptor zab;

        public zab(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.zaa = uri;
            this.zab = parcelFileDescriptor;
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean z;
            Bitmap bitmap;
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            ParcelFileDescriptor parcelFileDescriptor = this.zab;
            boolean z2 = false;
            Bitmap bitmap2 = null;
            if (parcelFileDescriptor == null) {
                z = false;
                bitmap = null;
            } else {
                try {
                    bitmap2 = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String valueOf = String.valueOf(this.zaa);
                    Log.e("ImageManager", new StringBuilder(String.valueOf(valueOf).length() + 34).append("OOM while loading bitmap for uri: ").append(valueOf).toString(), e);
                    z2 = true;
                }
                try {
                    this.zab.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
                z = z2;
                bitmap = bitmap2;
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.zae.post(new zac(this.zaa, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                String valueOf2 = String.valueOf(this.zaa);
                Log.w("ImageManager", new StringBuilder(String.valueOf(valueOf2).length() + 32).append("Latch interrupted while posting ").append(valueOf2).toString());
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    /* loaded from: classes.dex */
    private final class ImageReceiver extends ResultReceiver {
        private final Uri zaa;
        private final ArrayList<com.google.android.gms.common.images.zaa> zab;

        ImageReceiver(Uri uri) {
            super(new zap(Looper.getMainLooper()));
            this.zaa = uri;
            this.zab = new ArrayList<>();
        }

        public final void zaa(com.google.android.gms.common.images.zaa zaaVar) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zab.add(zaaVar);
        }

        public final void zab(com.google.android.gms.common.images.zaa zaaVar) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zab.remove(zaaVar);
        }

        public final void zaa() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.setPackage("com.google.android.gms");
            intent.putExtra(Constants.EXTRA_URI, this.zaa);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, 3);
            ImageManager.this.zad.sendBroadcast(intent);
        }

        @Override // android.os.ResultReceiver
        public final void onReceiveResult(int i, Bundle bundle) {
            ImageManager.this.zaf.execute(new zab(this.zaa, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    /* loaded from: classes.dex */
    private final class zac implements Runnable {
        private final Uri zaa;
        private final Bitmap zab;
        private final CountDownLatch zac;
        private boolean zad;

        public zac(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.zaa = uri;
            this.zab = bitmap;
            this.zad = z;
            this.zac = countDownLatch;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.zab != null;
            if (ImageManager.this.zag != null) {
                if (!this.zad) {
                    if (this.zab != null) {
                        ImageManager.this.zag.put(new com.google.android.gms.common.images.zac(this.zaa), this.zab);
                    }
                } else {
                    ImageManager.this.zag.evictAll();
                    System.gc();
                    this.zad = false;
                    ImageManager.this.zae.post(this);
                    return;
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zaj.remove(this.zaa);
            if (imageReceiver != null) {
                ArrayList arrayList = imageReceiver.zab;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    com.google.android.gms.common.images.zaa zaaVar = (com.google.android.gms.common.images.zaa) arrayList.get(i);
                    if (this.zab != null && z) {
                        zaaVar.zaa(ImageManager.this.zad, this.zab, false);
                    } else {
                        ImageManager.this.zak.put(this.zaa, Long.valueOf(SystemClock.elapsedRealtime()));
                        zaaVar.zaa(ImageManager.this.zad, ImageManager.this.zah, false);
                    }
                    if (!(zaaVar instanceof com.google.android.gms.common.images.zad)) {
                        ImageManager.this.zai.remove(zaaVar);
                    }
                }
            }
            this.zac.countDown();
            synchronized (ImageManager.zaa) {
                ImageManager.zab.remove(this.zaa);
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        this.zad = context.getApplicationContext();
    }

    public final void loadImage(ImageView imageView, Uri uri) {
        zaa(new com.google.android.gms.common.images.zab(imageView, uri));
    }

    public final void loadImage(ImageView imageView, int i) {
        zaa(new com.google.android.gms.common.images.zab(imageView, i));
    }

    public final void loadImage(ImageView imageView, Uri uri, int i) {
        com.google.android.gms.common.images.zab zabVar = new com.google.android.gms.common.images.zab(imageView, uri);
        zabVar.zab = i;
        zaa(zabVar);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zaa(new com.google.android.gms.common.images.zad(onImageLoadedListener, uri));
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        com.google.android.gms.common.images.zad zadVar = new com.google.android.gms.common.images.zad(onImageLoadedListener, uri);
        zadVar.zab = i;
        zaa(zadVar);
    }

    private final void zaa(com.google.android.gms.common.images.zaa zaaVar) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zad(zaaVar).run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap zaa(com.google.android.gms.common.images.zac zacVar) {
        zaa zaaVar = this.zag;
        if (zaaVar == null) {
            return null;
        }
        return zaaVar.get(zacVar);
    }
}