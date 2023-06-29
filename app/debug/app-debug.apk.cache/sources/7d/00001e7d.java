package loadinganimation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.shoeARstore.R;

/* loaded from: classes10.dex */
public class LoadingView extends ImageView {
    private LoadingDrawable mLoadingDrawable;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        try {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
            int loadingRendererId = ta.getInt(0, 0);
            LoadingRenderer loadingRenderer = LoadingRendererFactory.createLoadingRenderer(context, loadingRendererId);
            setLoadingRenderer(loadingRenderer);
            ta.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLoadingRenderer(LoadingRenderer loadingRenderer) {
        LoadingDrawable loadingDrawable = new LoadingDrawable(loadingRenderer);
        this.mLoadingDrawable = loadingDrawable;
        setImageDrawable(loadingDrawable);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        boolean visible = visibility == 0 && getVisibility() == 0;
        if (visible) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    private void startAnimation() {
        LoadingDrawable loadingDrawable = this.mLoadingDrawable;
        if (loadingDrawable != null) {
            loadingDrawable.start();
        }
    }

    private void stopAnimation() {
        LoadingDrawable loadingDrawable = this.mLoadingDrawable;
        if (loadingDrawable != null) {
            loadingDrawable.stop();
        }
    }
}