package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class RotateUpTransformer extends BaseTransformer {
    private static final float ROT_MOD = -15.0f;

    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        float width = view.getWidth();
        float rotation = ROT_MOD * position;
        ViewHelper.setPivotX(view, 0.5f * width);
        ViewHelper.setPivotY(view, 0.0f);
        ViewHelper.setTranslationX(view, 0.0f);
        ViewHelper.setRotation(view, rotation);
    }

    @Override // slider.library.Transformers.BaseTransformer
    protected boolean isPagingEnabled() {
        return true;
    }
}