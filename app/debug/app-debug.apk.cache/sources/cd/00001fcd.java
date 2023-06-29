package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class ZoomOutTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        float scale = Math.abs(position) + 1.0f;
        ViewHelper.setScaleX(view, scale);
        ViewHelper.setScaleY(view, scale);
        ViewHelper.setPivotX(view, view.getWidth() * 0.5f);
        ViewHelper.setPivotY(view, view.getWidth() * 0.5f);
        ViewHelper.setAlpha(view, (position < -1.0f || position > 1.0f) ? 0.0f : 1.0f - (scale - 1.0f));
        if (position < -0.9d) {
            ViewHelper.setTranslationX(view, view.getWidth() * position);
        }
    }
}