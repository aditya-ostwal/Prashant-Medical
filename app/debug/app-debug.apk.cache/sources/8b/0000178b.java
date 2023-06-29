package girisanimation;

import android.graphics.RectF;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import java.util.Random;

/* loaded from: classes10.dex */
public class RandomTransitionGenerator implements TransitionGenerator {
    public static final int DEFAULT_TRANSITION_DURATION = 10000;
    private static final float MIN_RECT_FACTOR = 0.75f;
    private RectF mLastDrawableBounds;
    private Transition mLastGenTrans;
    private final Random mRandom;
    private long mTransitionDuration;
    private Interpolator mTransitionInterpolator;

    public RandomTransitionGenerator() {
        this(10000L, new AccelerateDecelerateInterpolator());
    }

    public RandomTransitionGenerator(long transitionDuration, Interpolator transitionInterpolator) {
        this.mRandom = new Random(System.currentTimeMillis());
        setTransitionDuration(transitionDuration);
        setTransitionInterpolator(transitionInterpolator);
    }

    @Override // girisanimation.TransitionGenerator
    public Transition generateNextTransition(RectF drawableBounds, RectF viewport) {
        RectF srcRect;
        Transition transition = this.mLastGenTrans;
        boolean firstTransition = transition == null;
        boolean drawableBoundsChanged = true;
        boolean viewportRatioChanged = true;
        RectF dstRect = null;
        if (!firstTransition) {
            dstRect = transition.getDestinyRect();
            drawableBoundsChanged = !drawableBounds.equals(this.mLastDrawableBounds);
            viewportRatioChanged = !MathUtils.haveSameAspectRatio(dstRect, viewport);
        }
        if (dstRect == null || drawableBoundsChanged || viewportRatioChanged) {
            srcRect = generateRandomRect(drawableBounds, viewport);
        } else {
            srcRect = dstRect;
        }
        RectF dstRect2 = generateRandomRect(drawableBounds, viewport);
        Transition transition2 = new Transition(srcRect, dstRect2, this.mTransitionDuration, this.mTransitionInterpolator);
        this.mLastGenTrans = transition2;
        this.mLastDrawableBounds = drawableBounds;
        return transition2;
    }

    private RectF generateRandomRect(RectF drawableBounds, RectF viewportRect) {
        RectF maxCrop;
        int left;
        float drawableRatio = MathUtils.getRectRatio(drawableBounds);
        float viewportRectRatio = MathUtils.getRectRatio(viewportRect);
        if (drawableRatio > viewportRectRatio) {
            float r = (drawableBounds.height() / viewportRect.height()) * viewportRect.width();
            float b = drawableBounds.height();
            maxCrop = new RectF(0.0f, 0.0f, r, b);
        } else {
            float r2 = drawableBounds.width();
            float b2 = (drawableBounds.width() / viewportRect.width()) * viewportRect.height();
            maxCrop = new RectF(0.0f, 0.0f, r2, b2);
        }
        float randomFloat = MathUtils.truncate(this.mRandom.nextFloat(), 2);
        float factor = (0.25f * randomFloat) + 0.75f;
        float width = maxCrop.width() * factor;
        float height = maxCrop.height() * factor;
        int widthDiff = (int) (drawableBounds.width() - width);
        int heightDiff = (int) (drawableBounds.height() - height);
        int top = 0;
        if (widthDiff > 0) {
            left = this.mRandom.nextInt(widthDiff);
        } else {
            left = 0;
        }
        if (heightDiff > 0) {
            top = this.mRandom.nextInt(heightDiff);
        }
        return new RectF(left, top, left + width, top + height);
    }

    public void setTransitionDuration(long transitionDuration) {
        this.mTransitionDuration = transitionDuration;
    }

    public void setTransitionInterpolator(Interpolator interpolator) {
        this.mTransitionInterpolator = interpolator;
    }
}