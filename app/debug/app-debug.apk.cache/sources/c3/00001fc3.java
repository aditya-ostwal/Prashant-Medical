package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class FadeTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        if (position < -1.0f || position > 1.0f) {
            ViewHelper.setAlpha(view, 0.6f);
        } else if (position <= 0.0f || position <= 1.0f) {
            float alpha = position <= 0.0f ? 1.0f + position : 1.0f - position;
            ViewHelper.setAlpha(view, alpha);
        } else if (position == 0.0f) {
            ViewHelper.setAlpha(view, 1.0f);
        }
    }
}