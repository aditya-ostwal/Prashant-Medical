package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class StackTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        ViewHelper.setTranslationX(view, position >= 0.0f ? (-view.getWidth()) * position : 0.0f);
    }
}