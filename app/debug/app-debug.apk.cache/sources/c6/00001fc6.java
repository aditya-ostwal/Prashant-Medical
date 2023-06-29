package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class ForegroundToBackgroundTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        float height = view.getHeight();
        float width = view.getWidth();
        float scale = min(position <= 0.0f ? Math.abs(1.0f + position) : 1.0f, 0.5f);
        ViewHelper.setScaleX(view, scale);
        ViewHelper.setScaleY(view, scale);
        ViewHelper.setPivotX(view, width * 0.5f);
        ViewHelper.setPivotY(view, 0.5f * height);
        ViewHelper.setTranslationX(view, position > 0.0f ? width * position : (-width) * position * 0.25f);
    }

    private static final float min(float val, float min) {
        return val < min ? min : val;
    }
}