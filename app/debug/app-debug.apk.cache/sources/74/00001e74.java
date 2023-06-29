package loadinganimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

/* loaded from: classes10.dex */
public class DanceLoadingRenderer extends LoadingRenderer {
    private static final long ANIMATION_DURATION = 1888;
    private static final float BALL_FORWARD_END_ENTER_DURATION_OFFSET = 0.125f;
    private static final float BALL_FORWARD_END_EXIT_DURATION_OFFSET = 0.54f;
    private static final float BALL_FORWARD_START_ENTER_DURATION_OFFSET = 0.0f;
    private static final float BALL_FORWARD_START_EXIT_DURATION_OFFSET = 0.375f;
    private static final float BALL_REVERSAL_END_ENTER_DURATION_OFFSET = 0.725f;
    private static final float BALL_REVERSAL_END_EXIT_DURATION_OFFSET = 1.0f;
    private static final float BALL_REVERSAL_START_ENTER_DURATION_OFFSET = 0.6f;
    private static final float BALL_REVERSAL_START_EXIT_DURATION_OFFSET = 0.875f;
    private static final float CENTER_CIRCLE_FORWARD_END_SCALE_DURATION_OFFSET = 0.475f;
    private static final float CENTER_CIRCLE_FORWARD_START_SCALE_DURATION_OFFSET = 0.225f;
    private static final float CENTER_CIRCLE_REVERSAL_END_SCALE_DURATION_OFFSET = 0.875f;
    private static final float CENTER_CIRCLE_REVERSAL_START_SCALE_DURATION_OFFSET = 0.675f;
    private static final int DANCE_INTERVAL_ANGLE = 60;
    private static final int DANCE_START_ANGLE = 0;
    private static final float DEFAULT_CENTER_RADIUS = 12.5f;
    private static final int DEFAULT_COLOR = -1;
    private static final float DEFAULT_DANCE_BALL_RADIUS = 2.0f;
    private static final float DEFAULT_STROKE_WIDTH = 1.5f;
    private static final int DEGREE_360 = 360;
    private static final int NUM_POINTS = 3;
    private static final float RING_FORWARD_END_ROTATE_DURATION_OFFSET = 0.375f;
    private static final float RING_FORWARD_START_ROTATE_DURATION_OFFSET = 0.125f;
    private static final float RING_REVERSAL_END_ROTATE_DURATION_OFFSET = 0.75f;
    private static final float RING_REVERSAL_START_ROTATE_DURATION_OFFSET = 0.5f;
    private static final int RING_START_ANGLE = -90;
    private int mArcColor;
    private float mCenterRadius;
    private int mColor;
    private final RectF mCurrentBounds;
    private float mDanceBallRadius;
    private final Paint mPaint;
    private float mRotation;
    private float mScale;
    private float mShapeChangeHeight;
    private float mShapeChangeWidth;
    private float mStrokeInset;
    private float mStrokeWidth;
    private final RectF mTempBounds;
    private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final Interpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final float[] POINT_X = new float[3];
    private static final float[] POINT_Y = new float[3];
    private static final int[] DIRECTION = {1, 1, -1};

    private DanceLoadingRenderer(Context context) {
        super(context);
        this.mPaint = new Paint();
        this.mTempBounds = new RectF();
        this.mCurrentBounds = new RectF();
        init(context);
        setupPaint();
    }

    private void init(Context context) {
        this.mStrokeWidth = DensityUtil.dip2px(context, DEFAULT_STROKE_WIDTH);
        this.mCenterRadius = DensityUtil.dip2px(context, DEFAULT_CENTER_RADIUS);
        this.mDanceBallRadius = DensityUtil.dip2px(context, DEFAULT_DANCE_BALL_RADIUS);
        setColor(-1);
        setInsets((int) this.mWidth, (int) this.mHeight);
        this.mDuration = ANIMATION_DURATION;
    }

    private void setupPaint() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
        this.mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // loadinganimation.LoadingRenderer
    protected void draw(Canvas canvas, Rect bounds) {
        int saveCount = canvas.save();
        this.mTempBounds.set(bounds);
        RectF rectF = this.mTempBounds;
        float f = this.mStrokeInset;
        rectF.inset(f, f);
        this.mCurrentBounds.set(this.mTempBounds);
        float outerCircleRadius = Math.min(this.mTempBounds.height(), this.mTempBounds.width()) / DEFAULT_DANCE_BALL_RADIUS;
        float interCircleRadius = outerCircleRadius / DEFAULT_DANCE_BALL_RADIUS;
        float centerRingWidth = interCircleRadius - (this.mStrokeWidth / DEFAULT_DANCE_BALL_RADIUS);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mColor);
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
        canvas.drawCircle(this.mTempBounds.centerX(), this.mTempBounds.centerY(), outerCircleRadius, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(this.mTempBounds.centerX(), this.mTempBounds.centerY(), this.mScale * interCircleRadius, this.mPaint);
        if (this.mRotation != 0.0f) {
            this.mPaint.setColor(this.mArcColor);
            this.mPaint.setStyle(Paint.Style.STROKE);
            RectF rectF2 = this.mTempBounds;
            float f2 = centerRingWidth / DEFAULT_DANCE_BALL_RADIUS;
            float f3 = this.mStrokeWidth;
            rectF2.inset(f2 + (f3 / DEFAULT_DANCE_BALL_RADIUS), (centerRingWidth / DEFAULT_DANCE_BALL_RADIUS) + (f3 / DEFAULT_DANCE_BALL_RADIUS));
            this.mPaint.setStrokeWidth(centerRingWidth);
            canvas.drawArc(this.mTempBounds, -90.0f, this.mRotation, false, this.mPaint);
        }
        this.mPaint.setColor(this.mColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 3; i++) {
            float[] fArr = POINT_X;
            float f4 = fArr[i];
            float[] fArr2 = POINT_Y;
            canvas.rotate(i * 60, f4, fArr2[i]);
            float f5 = fArr[i];
            float f6 = this.mDanceBallRadius;
            float f7 = this.mShapeChangeWidth;
            float f8 = this.mShapeChangeHeight;
            RectF rectF3 = new RectF((f5 - f6) - (f7 / DEFAULT_DANCE_BALL_RADIUS), (fArr2[i] - f6) - (f8 / DEFAULT_DANCE_BALL_RADIUS), fArr[i] + f6 + (f7 / DEFAULT_DANCE_BALL_RADIUS), fArr2[i] + f6 + (f8 / DEFAULT_DANCE_BALL_RADIUS));
            canvas.drawOval(rectF3, this.mPaint);
            canvas.rotate((-i) * 60, fArr[i], fArr2[i]);
        }
        canvas.restoreToCount(saveCount);
    }

    @Override // loadinganimation.LoadingRenderer
    protected void computeRender(float renderProgress) {
        float radius;
        float originCoordinateX;
        DanceLoadingRenderer danceLoadingRenderer;
        float originCoordinateY;
        float radius2;
        DanceLoadingRenderer danceLoadingRenderer2;
        float min = Math.min(this.mCurrentBounds.height(), this.mCurrentBounds.width());
        float f = DEFAULT_DANCE_BALL_RADIUS;
        float radius3 = min / DEFAULT_DANCE_BALL_RADIUS;
        float originCoordinateX2 = this.mCurrentBounds.left;
        float originCoordinateY2 = this.mCurrentBounds.top + radius3;
        double d = 6.283185307179586d;
        float f2 = 360.0f;
        if (renderProgress > 0.125f || renderProgress <= 0.0f) {
            radius = radius3;
            originCoordinateX = originCoordinateX2;
        } else {
            float ballForwardEnterProgress = (renderProgress - 0.0f) / 0.125f;
            float f3 = ((0.5f - ballForwardEnterProgress) * this.mDanceBallRadius) / DEFAULT_DANCE_BALL_RADIUS;
            this.mShapeChangeHeight = f3;
            this.mShapeChangeWidth = -f3;
            int i = 0;
            for (int i2 = 3; i < i2; i2 = 3) {
                float k = (float) Math.tan((((i * 60) + 0) / 360.0f) * d);
                float progress = ((ACCELERATE_INTERPOLATOR.getInterpolation(ballForwardEnterProgress) / f) - 0.5f) * f * DIRECTION[i];
                float[] fArr = POINT_X;
                float originCoordinateX3 = originCoordinateX2;
                float radius4 = radius3;
                fArr[i] = (float) (radius3 + (progress * (radius3 / Math.sqrt(Math.pow(k, 2.0d) + 1.0d))));
                float[] fArr2 = POINT_Y;
                fArr2[i] = (fArr[i] - radius4) * k;
                fArr[i] = fArr[i] + originCoordinateX3;
                fArr2[i] = fArr2[i] + originCoordinateY2;
                i++;
                ballForwardEnterProgress = ballForwardEnterProgress;
                originCoordinateX2 = originCoordinateX3;
                radius3 = radius4;
                f = DEFAULT_DANCE_BALL_RADIUS;
                d = 6.283185307179586d;
            }
            radius = radius3;
            originCoordinateX = originCoordinateX2;
        }
        if (renderProgress > 0.375f || renderProgress <= 0.125f) {
            danceLoadingRenderer = this;
        } else {
            float forwardRotateProgress = (renderProgress - 0.125f) / 0.25f;
            danceLoadingRenderer = this;
            danceLoadingRenderer.mRotation = MATERIAL_INTERPOLATOR.getInterpolation(forwardRotateProgress) * 360.0f;
        }
        if (renderProgress <= CENTER_CIRCLE_FORWARD_END_SCALE_DURATION_OFFSET && renderProgress > CENTER_CIRCLE_FORWARD_START_SCALE_DURATION_OFFSET) {
            float centerCircleScaleProgress = (renderProgress - CENTER_CIRCLE_FORWARD_START_SCALE_DURATION_OFFSET) / 0.25f;
            if (centerCircleScaleProgress <= 0.5f) {
                danceLoadingRenderer.mScale = (DECELERATE_INTERPOLATOR.getInterpolation(centerCircleScaleProgress * DEFAULT_DANCE_BALL_RADIUS) * 0.2f) + 1.0f;
            } else {
                danceLoadingRenderer.mScale = 1.2f - (ACCELERATE_INTERPOLATOR.getInterpolation((centerCircleScaleProgress - 0.5f) * DEFAULT_DANCE_BALL_RADIUS) * 0.2f);
            }
        }
        if (renderProgress > 0.54f || renderProgress <= 0.375f) {
            originCoordinateY = originCoordinateY2;
            radius2 = radius;
        } else {
            float ballForwardExitProgress = (renderProgress - 0.375f) / 0.16500002f;
            float f4 = ((ballForwardExitProgress - 0.5f) * danceLoadingRenderer.mDanceBallRadius) / DEFAULT_DANCE_BALL_RADIUS;
            danceLoadingRenderer.mShapeChangeHeight = f4;
            danceLoadingRenderer.mShapeChangeWidth = -f4;
            int i3 = 0;
            while (i3 < 3) {
                float k2 = (float) Math.tan((((i3 * 60) + 0) / f2) * 6.283185307179586d);
                float progress2 = (DECELERATE_INTERPOLATOR.getInterpolation(ballForwardExitProgress) / DEFAULT_DANCE_BALL_RADIUS) * DEFAULT_DANCE_BALL_RADIUS * DIRECTION[i3];
                float[] fArr3 = POINT_X;
                float radius5 = radius;
                float originCoordinateY3 = originCoordinateY2;
                fArr3[i3] = (float) (radius5 + (progress2 * (radius5 / Math.sqrt(Math.pow(k2, 2.0d) + 1.0d))));
                float[] fArr4 = POINT_Y;
                fArr4[i3] = (fArr3[i3] - radius5) * k2;
                fArr3[i3] = fArr3[i3] + originCoordinateX;
                fArr4[i3] = fArr4[i3] + originCoordinateY3;
                i3++;
                originCoordinateY2 = originCoordinateY3;
                f2 = 360.0f;
            }
            originCoordinateY = originCoordinateY2;
            radius2 = radius;
        }
        if (renderProgress > 0.75f || renderProgress <= 0.5f) {
            danceLoadingRenderer2 = this;
            if (renderProgress > 0.75f) {
                danceLoadingRenderer2.mRotation = 0.0f;
            }
        } else {
            float scaledTime = (renderProgress - 0.5f) / 0.25f;
            danceLoadingRenderer2 = this;
            danceLoadingRenderer2.mRotation = (MATERIAL_INTERPOLATOR.getInterpolation(scaledTime) * 360.0f) - 360.0f;
        }
        if (renderProgress <= BALL_REVERSAL_END_ENTER_DURATION_OFFSET && renderProgress > BALL_REVERSAL_START_ENTER_DURATION_OFFSET) {
            float ballReversalEnterProgress = (renderProgress - BALL_REVERSAL_START_ENTER_DURATION_OFFSET) / 0.125f;
            float f5 = ((0.5f - ballReversalEnterProgress) * danceLoadingRenderer2.mDanceBallRadius) / DEFAULT_DANCE_BALL_RADIUS;
            danceLoadingRenderer2.mShapeChangeHeight = f5;
            danceLoadingRenderer2.mShapeChangeWidth = -f5;
            int i4 = 0;
            while (i4 < 3) {
                float k3 = (float) Math.tan((((i4 * 60) + 0) / 360.0f) * 6.283185307179586d);
                float progress3 = (0.5f - (ACCELERATE_INTERPOLATOR.getInterpolation(ballReversalEnterProgress) / DEFAULT_DANCE_BALL_RADIUS)) * DEFAULT_DANCE_BALL_RADIUS * DIRECTION[i4];
                float[] fArr5 = POINT_X;
                float ballReversalEnterProgress2 = ballReversalEnterProgress;
                int i5 = i4;
                fArr5[i5] = (float) (radius2 + (progress3 * (radius2 / Math.sqrt(Math.pow(k3, 2.0d) + 1.0d))));
                float[] fArr6 = POINT_Y;
                fArr6[i5] = (fArr5[i5] - radius2) * k3;
                fArr5[i5] = fArr5[i5] + originCoordinateX;
                fArr6[i5] = fArr6[i5] + originCoordinateY;
                i4 = i5 + 1;
                ballReversalEnterProgress = ballReversalEnterProgress2;
            }
        }
        if (renderProgress <= 0.875f && renderProgress > CENTER_CIRCLE_REVERSAL_START_SCALE_DURATION_OFFSET) {
            float centerCircleScaleProgress2 = (renderProgress - CENTER_CIRCLE_REVERSAL_START_SCALE_DURATION_OFFSET) / 0.19999999f;
            if (centerCircleScaleProgress2 <= 0.5f) {
                danceLoadingRenderer2.mScale = (DECELERATE_INTERPOLATOR.getInterpolation(centerCircleScaleProgress2 * DEFAULT_DANCE_BALL_RADIUS) * 0.2f) + 1.0f;
            } else {
                danceLoadingRenderer2.mScale = 1.2f - (ACCELERATE_INTERPOLATOR.getInterpolation((centerCircleScaleProgress2 - 0.5f) * DEFAULT_DANCE_BALL_RADIUS) * 0.2f);
            }
        }
        if (renderProgress <= 1.0f && renderProgress > 0.875f) {
            float ballReversalExitProgress = (renderProgress - 0.875f) / 0.125f;
            float f6 = ((ballReversalExitProgress - 0.5f) * danceLoadingRenderer2.mDanceBallRadius) / DEFAULT_DANCE_BALL_RADIUS;
            danceLoadingRenderer2.mShapeChangeHeight = f6;
            danceLoadingRenderer2.mShapeChangeWidth = -f6;
            for (int i6 = 0; i6 < 3; i6++) {
                float k4 = (float) Math.tan((((i6 * 60) + 0) / 360.0f) * 6.283185307179586d);
                float progress4 = (0.0f - (DECELERATE_INTERPOLATOR.getInterpolation(ballReversalExitProgress) / DEFAULT_DANCE_BALL_RADIUS)) * DEFAULT_DANCE_BALL_RADIUS * DIRECTION[i6];
                float[] fArr7 = POINT_X;
                fArr7[i6] = (float) (radius2 + (progress4 * (radius2 / Math.sqrt(Math.pow(k4, 2.0d) + 1.0d))));
                float[] fArr8 = POINT_Y;
                fArr8[i6] = (fArr7[i6] - radius2) * k4;
                fArr7[i6] = fArr7[i6] + originCoordinateX;
                fArr8[i6] = fArr8[i6] + originCoordinateY;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // loadinganimation.LoadingRenderer
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // loadinganimation.LoadingRenderer
    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    @Override // loadinganimation.LoadingRenderer
    protected void reset() {
        this.mScale = 1.0f;
        this.mRotation = 0.0f;
    }

    private void setColor(int color) {
        this.mColor = color;
        this.mArcColor = halfAlphaColor(color);
    }

    private void setRotation(float rotation) {
        this.mRotation = rotation;
    }

    private void setDanceBallRadius(float danceBallRadius) {
        this.mDanceBallRadius = danceBallRadius;
    }

    private float getDanceBallRadius() {
        return this.mDanceBallRadius;
    }

    private float getRotation() {
        return this.mRotation;
    }

    private void setInsets(int width, int height) {
        float insets;
        float minEdge = Math.min(width, height);
        float f = this.mCenterRadius;
        if (f <= 0.0f || minEdge < 0.0f) {
            insets = (float) Math.ceil(this.mStrokeWidth / DEFAULT_DANCE_BALL_RADIUS);
        } else {
            insets = (minEdge / DEFAULT_DANCE_BALL_RADIUS) - f;
        }
        this.mStrokeInset = insets;
    }

    private int halfAlphaColor(int colorValue) {
        int startA = (colorValue >> 24) & 255;
        int startR = (colorValue >> 16) & 255;
        int startG = (colorValue >> 8) & 255;
        int startB = colorValue & 255;
        return ((startA / 2) << 24) | (startR << 16) | (startG << 8) | startB;
    }

    /* loaded from: classes10.dex */
    public static class Builder {
        private Context mContext;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public DanceLoadingRenderer build() {
            DanceLoadingRenderer loadingRenderer = new DanceLoadingRenderer(this.mContext);
            return loadingRenderer;
        }
    }
}