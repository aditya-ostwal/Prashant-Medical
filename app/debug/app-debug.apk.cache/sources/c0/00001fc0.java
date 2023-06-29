package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class CubeInTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        ViewHelper.setPivotX(view, position > 0.0f ? 0.0f : view.getWidth());
        ViewHelper.setPivotY(view, 0.0f);
        ViewHelper.setRotation(view, (-90.0f) * position);
    }

    @Override // slider.library.Transformers.BaseTransformer
    public boolean isPagingEnabled() {
        return true;
    }
}