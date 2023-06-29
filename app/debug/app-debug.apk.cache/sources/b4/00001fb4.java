package slider.library.SliderTypes;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.mtel.shoe.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.File;

/* loaded from: classes9.dex */
public abstract class BaseSliderView {
    private Bundle mBundle;
    protected Context mContext;
    private String mDescription;
    private int mEmptyPlaceHolderRes;
    private boolean mErrorDisappear;
    private int mErrorPlaceHolderRes;
    private File mFile;
    private ImageLoadListener mLoadListener;
    protected OnSliderClickListener mOnSliderClickListener;
    private Picasso mPicasso;
    private int mRes;
    private ScaleType mScaleType = ScaleType.Fit;
    private String mUrl;

    /* loaded from: classes9.dex */
    public interface ImageLoadListener {
        void onEnd(boolean z, BaseSliderView baseSliderView);

        void onStart(BaseSliderView baseSliderView);
    }

    /* loaded from: classes9.dex */
    public interface OnSliderClickListener {
        void onSliderClick(BaseSliderView baseSliderView);
    }

    /* loaded from: classes9.dex */
    public enum ScaleType {
        CenterCrop,
        CenterInside,
        Fit,
        FitCenterCrop
    }

    public abstract View getView();

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseSliderView(Context context) {
        this.mContext = context;
    }

    public BaseSliderView empty(int resId) {
        this.mEmptyPlaceHolderRes = resId;
        return this;
    }

    public BaseSliderView errorDisappear(boolean disappear) {
        this.mErrorDisappear = disappear;
        return this;
    }

    public BaseSliderView error(int resId) {
        this.mErrorPlaceHolderRes = resId;
        return this;
    }

    public BaseSliderView description(String description) {
        this.mDescription = description;
        return this;
    }

    public BaseSliderView image(String url) {
        if (this.mFile != null || this.mRes != 0) {
            throw new IllegalStateException("Call multi image function,you only have permission to call it once");
        }
        this.mUrl = url;
        return this;
    }

    public BaseSliderView image(File file) {
        if (this.mUrl != null || this.mRes != 0) {
            throw new IllegalStateException("Call multi image function,you only have permission to call it once");
        }
        this.mFile = file;
        return this;
    }

    public BaseSliderView image(int res) {
        if (this.mUrl != null || this.mFile != null) {
            throw new IllegalStateException("Call multi image function,you only have permission to call it once");
        }
        this.mRes = res;
        return this;
    }

    public BaseSliderView bundle(Bundle bundle) {
        this.mBundle = bundle;
        return this;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean isErrorDisappear() {
        return this.mErrorDisappear;
    }

    public int getEmpty() {
        return this.mEmptyPlaceHolderRes;
    }

    public int getError() {
        return this.mErrorPlaceHolderRes;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public Context getContext() {
        return this.mContext;
    }

    public BaseSliderView setOnSliderClickListener(OnSliderClickListener l) {
        this.mOnSliderClickListener = l;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void bindEventAndShow(final View v, ImageView targetImageView) {
        RequestCreator rq;
        v.setOnClickListener(new View.OnClickListener() { // from class: slider.library.SliderTypes.BaseSliderView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v2) {
                if (BaseSliderView.this.mOnSliderClickListener != null) {
                    BaseSliderView.this.mOnSliderClickListener.onSliderClick(me);
                }
            }
        });
        if (targetImageView == null) {
            return;
        }
        ImageLoadListener imageLoadListener = this.mLoadListener;
        if (imageLoadListener != null) {
            imageLoadListener.onStart(this);
        }
        Picasso p = this.mPicasso;
        if (p == null) {
            p = Picasso.get();
        }
        String str = this.mUrl;
        if (str != null) {
            rq = p.load(str);
        } else {
            File file = this.mFile;
            if (file != null) {
                rq = p.load(file);
            } else {
                int i = this.mRes;
                if (i != 0) {
                    rq = p.load(i);
                } else {
                    return;
                }
            }
        }
        if (rq == null) {
            return;
        }
        if (getEmpty() != 0) {
            rq.placeholder(getEmpty());
        }
        if (getError() != 0) {
            rq.error(getError());
        }
        switch (AnonymousClass3.$SwitchMap$slider$library$SliderTypes$BaseSliderView$ScaleType[this.mScaleType.ordinal()]) {
            case 1:
                rq.fit();
                break;
            case 2:
                rq.fit().centerCrop();
                break;
            case 3:
                rq.fit().centerInside();
                break;
        }
        rq.into(targetImageView, new Callback() { // from class: slider.library.SliderTypes.BaseSliderView.2
            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                if (v.findViewById(R.id.loading_bar) != null) {
                    v.findViewById(R.id.loading_bar).setVisibility(4);
                }
            }

            @Override // com.squareup.picasso.Callback
            public void onError(Exception e) {
                if (BaseSliderView.this.mLoadListener != null) {
                    BaseSliderView.this.mLoadListener.onEnd(false, me);
                }
                if (v.findViewById(R.id.loading_bar) != null) {
                    v.findViewById(R.id.loading_bar).setVisibility(4);
                }
            }
        });
    }

    /* renamed from: slider.library.SliderTypes.BaseSliderView$3  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$slider$library$SliderTypes$BaseSliderView$ScaleType;

        static {
            int[] iArr = new int[ScaleType.values().length];
            $SwitchMap$slider$library$SliderTypes$BaseSliderView$ScaleType = iArr;
            try {
                iArr[ScaleType.Fit.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$slider$library$SliderTypes$BaseSliderView$ScaleType[ScaleType.CenterCrop.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$slider$library$SliderTypes$BaseSliderView$ScaleType[ScaleType.CenterInside.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public BaseSliderView setScaleType(ScaleType type) {
        this.mScaleType = type;
        return this;
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setOnImageLoadListener(ImageLoadListener l) {
        this.mLoadListener = l;
    }

    public Bundle getBundle() {
        return this.mBundle;
    }

    public Picasso getPicasso() {
        return this.mPicasso;
    }

    public void setPicasso(Picasso picasso) {
        this.mPicasso = picasso;
    }
}