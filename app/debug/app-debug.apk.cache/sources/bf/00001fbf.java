package slider.library.Transformers;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import java.util.HashMap;
import slider.library.Animations.BaseAnimationInterface;
import slider.library.Tricks.ViewPagerEx;

/* loaded from: classes11.dex */
public abstract class BaseTransformer implements ViewPagerEx.PageTransformer {
    private HashMap<View, ArrayList<Float>> h = new HashMap<>();
    boolean isApp;
    boolean isDis;
    private BaseAnimationInterface mCustomAnimationInterface;

    protected abstract void onTransform(View view, float f);

    @Override // slider.library.Tricks.ViewPagerEx.PageTransformer
    public void transformPage(View view, float position) {
        onPreTransform(view, position);
        onTransform(view, position);
        onPostTransform(view, position);
    }

    protected boolean hideOffscreenPages() {
        return true;
    }

    protected boolean isPagingEnabled() {
        return false;
    }

    protected void onPreTransform(View view, float position) {
        float f;
        float width = view.getWidth();
        ViewHelper.setRotationX(view, 0.0f);
        ViewHelper.setRotationY(view, 0.0f);
        ViewHelper.setRotation(view, 0.0f);
        ViewHelper.setScaleX(view, 1.0f);
        ViewHelper.setScaleY(view, 1.0f);
        ViewHelper.setPivotX(view, 0.0f);
        ViewHelper.setPivotY(view, 0.0f);
        ViewHelper.setTranslationY(view, 0.0f);
        ViewHelper.setTranslationX(view, isPagingEnabled() ? 0.0f : (-width) * position);
        if (hideOffscreenPages()) {
            if (position <= -1.0f || position >= 1.0f) {
                f = 0.0f;
            } else {
                f = 1.0f;
            }
            ViewHelper.setAlpha(view, f);
        } else {
            ViewHelper.setAlpha(view, 1.0f);
        }
        if (this.mCustomAnimationInterface != null) {
            if ((!this.h.containsKey(view) || this.h.get(view).size() == 1) && position > -1.0f && position < 1.0f) {
                if (this.h.get(view) == null) {
                    this.h.put(view, new ArrayList<>());
                }
                this.h.get(view).add(Float.valueOf(position));
                if (this.h.get(view).size() == 2) {
                    float zero = this.h.get(view).get(0).floatValue();
                    float cha = this.h.get(view).get(1).floatValue() - this.h.get(view).get(0).floatValue();
                    if (zero > 0.0f) {
                        if (cha > -1.0f && cha < 0.0f) {
                            this.mCustomAnimationInterface.onPrepareNextItemShowInScreen(view);
                        } else {
                            this.mCustomAnimationInterface.onPrepareCurrentItemLeaveScreen(view);
                        }
                    } else if (cha > -1.0f && cha < 0.0f) {
                        this.mCustomAnimationInterface.onPrepareCurrentItemLeaveScreen(view);
                    } else {
                        this.mCustomAnimationInterface.onPrepareNextItemShowInScreen(view);
                    }
                }
            }
        }
    }

    protected void onPostTransform(View view, float position) {
        BaseAnimationInterface baseAnimationInterface = this.mCustomAnimationInterface;
        if (baseAnimationInterface != null) {
            if (position == -1.0f || position == 1.0f) {
                baseAnimationInterface.onCurrentItemDisappear(view);
                this.isApp = true;
            } else if (position == 0.0f) {
                baseAnimationInterface.onNextItemAppear(view);
                this.isDis = true;
            }
            if (this.isApp && this.isDis) {
                this.h.clear();
                this.isApp = false;
                this.isDis = false;
            }
        }
    }

    public void setCustomAnimationInterface(BaseAnimationInterface animationInterface) {
        this.mCustomAnimationInterface = animationInterface;
    }
}