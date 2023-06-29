package girisanimation;

import android.graphics.RectF;
import android.view.animation.Interpolator;

/* loaded from: classes10.dex */
public class Transition {
    private float mCenterXDiff;
    private float mCenterYDiff;
    private final RectF mCurrentRect = new RectF();
    private RectF mDstRect;
    private long mDuration;
    private float mHeightDiff;
    private Interpolator mInterpolator;
    private RectF mSrcRect;
    private float mWidthDiff;

    public Transition(RectF srcRect, RectF dstRect, long duration, Interpolator interpolator) {
        if (!MathUtils.haveSameAspectRatio(srcRect, dstRect)) {
            throw new IncompatibleRatioException();
        }
        this.mSrcRect = srcRect;
        this.mDstRect = dstRect;
        this.mDuration = duration;
        this.mInterpolator = interpolator;
        this.mWidthDiff = dstRect.width() - srcRect.width();
        this.mHeightDiff = dstRect.height() - srcRect.height();
        this.mCenterXDiff = dstRect.centerX() - srcRect.centerX();
        this.mCenterYDiff = dstRect.centerY() - srcRect.centerY();
    }

    public RectF getSourceRect() {
        return this.mSrcRect;
    }

    public RectF getDestinyRect() {
        return this.mDstRect;
    }

    public RectF getInterpolatedRect(long elapsedTime) {
        float elapsedTimeFraction = ((float) elapsedTime) / ((float) this.mDuration);
        float interpolationProgress = Math.min(elapsedTimeFraction, 1.0f);
        float interpolation = this.mInterpolator.getInterpolation(interpolationProgress);
        float currentWidth = this.mSrcRect.width() + (this.mWidthDiff * interpolation);
        float currentHeight = this.mSrcRect.height() + (this.mHeightDiff * interpolation);
        float currentCenterX = this.mSrcRect.centerX() + (this.mCenterXDiff * interpolation);
        float currentCenterY = this.mSrcRect.centerY() + (this.mCenterYDiff * interpolation);
        float left = currentCenterX - (currentWidth / 2.0f);
        float top = currentCenterY - (currentHeight / 2.0f);
        float right = left + currentWidth;
        float bottom = top + currentHeight;
        this.mCurrentRect.set(left, top, right, bottom);
        return this.mCurrentRect;
    }

    public long getDuration() {
        return this.mDuration;
    }
}