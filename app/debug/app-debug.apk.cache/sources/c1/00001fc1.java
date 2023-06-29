package slider.library.Transformers;

import android.view.View;

/* loaded from: classes11.dex */
public class DefaultTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
    }

    @Override // slider.library.Transformers.BaseTransformer
    public boolean isPagingEnabled() {
        return true;
    }
}