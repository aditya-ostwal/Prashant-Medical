package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ImageViewAction extends Action<ImageView> {
    Callback callback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImageViewAction(Picasso picasso, ImageView imageView, Request data, int memoryPolicy, int networkPolicy, int errorResId, Drawable errorDrawable, String key, Object tag, Callback callback, boolean noFade) {
        super(picasso, imageView, data, memoryPolicy, networkPolicy, errorResId, errorDrawable, key, tag, noFade);
        this.callback = callback;
    }

    @Override // com.squareup.picasso.Action
    public void complete(Bitmap result, Picasso.LoadedFrom from) {
        if (result == null) {
            throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", this));
        }
        ImageView target = (ImageView) this.target.get();
        if (target == null) {
            return;
        }
        Context context = this.picasso.context;
        boolean indicatorsEnabled = this.picasso.indicatorsEnabled;
        PicassoDrawable.setBitmap(target, context, result, from, this.noFade, indicatorsEnabled);
        Callback callback = this.callback;
        if (callback != null) {
            callback.onSuccess();
        }
    }

    @Override // com.squareup.picasso.Action
    public void error(Exception e) {
        ImageView target = (ImageView) this.target.get();
        if (target == null) {
            return;
        }
        Drawable placeholder = target.getDrawable();
        if (placeholder instanceof Animatable) {
            ((Animatable) placeholder).stop();
        }
        if (this.errorResId != 0) {
            target.setImageResource(this.errorResId);
        } else if (this.errorDrawable != null) {
            target.setImageDrawable(this.errorDrawable);
        }
        Callback callback = this.callback;
        if (callback != null) {
            callback.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.squareup.picasso.Action
    public void cancel() {
        super.cancel();
        if (this.callback != null) {
            this.callback = null;
        }
    }
}