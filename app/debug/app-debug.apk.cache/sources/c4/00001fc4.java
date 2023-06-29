package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class FlipHorizontalTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        float rotation = 180.0f * position;
        ViewHelper.setAlpha(view, (rotation > 90.0f || rotation < -90.0f) ? 0.0f : 1.0f);
        ViewHelper.setPivotY(view, view.getHeight() * 0.5f);
        ViewHelper.setPivotX(view, view.getWidth() * 0.5f);
        ViewHelper.setRotationY(view, rotation);
    }
}