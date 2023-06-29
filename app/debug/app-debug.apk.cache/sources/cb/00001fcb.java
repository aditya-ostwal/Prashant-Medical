package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class ZoomInTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        float f = 0.0f;
        float scale = position < 0.0f ? position + 1.0f : Math.abs(1.0f - position);
        ViewHelper.setScaleX(view, scale);
        ViewHelper.setScaleY(view, scale);
        ViewHelper.setPivotX(view, view.getWidth() * 0.5f);
        ViewHelper.setPivotY(view, view.getHeight() * 0.5f);
        if (position >= -1.0f && position <= 1.0f) {
            f = 1.0f - (scale - 1.0f);
        }
        ViewHelper.setAlpha(view, f);
    }
}