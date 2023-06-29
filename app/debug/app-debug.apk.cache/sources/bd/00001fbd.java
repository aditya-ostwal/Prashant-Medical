package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class AccordionTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        ViewHelper.setPivotX(view, position < 0.0f ? 0.0f : view.getWidth());
        ViewHelper.setScaleX(view, position < 0.0f ? 1.0f + position : 1.0f - position);
    }
}