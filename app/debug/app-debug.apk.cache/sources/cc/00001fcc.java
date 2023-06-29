package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class ZoomOutSlideTransformer extends BaseTransformer {
    private static final float MIN_ALPHA = 0.5f;
    private static final float MIN_SCALE = 0.85f;

    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        if (position >= -1.0f || position <= 1.0f) {
            float height = view.getHeight();
            float scaleFactor = Math.max((float) MIN_SCALE, 1.0f - Math.abs(position));
            float vertMargin = ((1.0f - scaleFactor) * height) / 2.0f;
            float horzMargin = (view.getWidth() * (1.0f - scaleFactor)) / 2.0f;
            ViewHelper.setPivotY(view, height * 0.5f);
            if (position < 0.0f) {
                ViewHelper.setTranslationX(view, horzMargin - (vertMargin / 2.0f));
            } else {
                ViewHelper.setTranslationX(view, (-horzMargin) + (vertMargin / 2.0f));
            }
            ViewHelper.setScaleX(view, scaleFactor);
            ViewHelper.setScaleY(view, scaleFactor);
            ViewHelper.setAlpha(view, (((scaleFactor - MIN_SCALE) / 0.14999998f) * 0.5f) + 0.5f);
        }
    }
}