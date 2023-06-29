package slider.library.Transformers;

import android.os.Build;
import android.view.View;
import com.nineoldandroids.view.ViewHelper;
import slider.library.Tricks.ViewPagerEx;

/* loaded from: classes11.dex */
public class FlipPageViewTransformer extends BaseTransformer {
    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        float percentage = 1.0f - Math.abs(position);
        if (Build.VERSION.SDK_INT >= 13) {
            view.setCameraDistance(12000.0f);
        }
        setVisibility(view, position);
        setTranslation(view);
        setSize(view, position, percentage);
        setRotation(view, position, percentage);
    }

    private void setVisibility(View page, float position) {
        if (position < 0.5d && position > -0.5d) {
            page.setVisibility(0);
        } else {
            page.setVisibility(4);
        }
    }

    private void setTranslation(View view) {
        ViewPagerEx viewPager = (ViewPagerEx) view.getParent();
        int scroll = viewPager.getScrollX() - view.getLeft();
        ViewHelper.setTranslationX(view, scroll);
    }

    private void setSize(View view, float position, float percentage) {
        float f = 1.0f;
        ViewHelper.setScaleX(view, (position == 0.0f || position == 1.0f) ? 1.0f : percentage);
        if (position != 0.0f && position != 1.0f) {
            f = percentage;
        }
        ViewHelper.setScaleY(view, f);
    }

    private void setRotation(View view, float position, float percentage) {
        if (position > 0.0f) {
            ViewHelper.setRotationY(view, (1.0f + percentage) * (-180.0f));
        } else {
            ViewHelper.setRotationY(view, (1.0f + percentage) * 180.0f);
        }
    }
}