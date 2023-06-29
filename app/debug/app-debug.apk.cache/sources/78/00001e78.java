package loadinganimation;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

/* loaded from: classes10.dex */
public class LoadingDrawable extends Drawable implements Animatable {
    private final Drawable.Callback mCallback;
    private final LoadingRenderer mLoadingRender;

    public LoadingDrawable(LoadingRenderer loadingRender) {
        Drawable.Callback callback = new Drawable.Callback() { // from class: loadinganimation.LoadingDrawable.1
            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable d) {
                LoadingDrawable.this.invalidateSelf();
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable d, Runnable what, long when) {
                LoadingDrawable.this.scheduleSelf(what, when);
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable d, Runnable what) {
                LoadingDrawable.this.unscheduleSelf(what);
            }
        };
        this.mCallback = callback;
        this.mLoadingRender = loadingRender;
        loadingRender.setCallback(callback);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.mLoadingRender.setBounds(bounds);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!getBounds().isEmpty()) {
            this.mLoadingRender.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.mLoadingRender.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
        this.mLoadingRender.setColorFilter(cf);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.mLoadingRender.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.mLoadingRender.stop();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mLoadingRender.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.mLoadingRender.mHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) this.mLoadingRender.mWidth;
    }
}