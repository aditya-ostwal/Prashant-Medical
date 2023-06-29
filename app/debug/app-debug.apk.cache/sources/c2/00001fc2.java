package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class DepthPageTransformer extends BaseTransformer {
    private static final float MIN_SCALE = 0.75f;

    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        if (position <= 0.0f) {
            ViewHelper.setTranslationX(view, 0.0f);
            ViewHelper.setScaleX(view, 1.0f);
            ViewHelper.setScaleY(view, 1.0f);
        } else if (position <= 1.0f) {
            float scaleFactor = ((1.0f - Math.abs(position)) * 0.25f) + 0.75f;
            ViewHelper.setAlpha(view, 1.0f - position);
            ViewHelper.setPivotY(view, view.getHeight() * 0.5f);
            ViewHelper.setTranslationX(view, view.getWidth() * (-position));
            ViewHelper.setScaleX(view, scaleFactor);
            ViewHelper.setScaleY(view, scaleFactor);
        }
    }

    @Override // slider.library.Transformers.BaseTransformer
    protected boolean isPagingEnabled() {
        return true;
    }
}