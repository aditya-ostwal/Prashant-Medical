package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class RotateDownTransformer extends BaseTransformer {
    private static final float ROT_MOD = -15.0f;

    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        float width = view.getWidth();
        float height = view.getHeight();
        float rotation = ROT_MOD * position * (-1.25f);
        ViewHelper.setPivotX(view, 0.5f * width);
        ViewHelper.setPivotY(view, height);
        ViewHelper.setRotation(view, rotation);
    }

    @Override // slider.library.Transformers.BaseTransformer
    protected boolean isPagingEnabled() {
        return true;
    }
}