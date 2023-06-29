package com.alexzh.circleimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class CircleImageView extends ImageView {
    private static final float BORDER_WIDTH = 0.0f;
    private static final float CIRCLE_DEGREE = 360.0f;
    private static final float SHADOW_DX = 2.0f;
    private static final float SHADOW_DY = 2.0f;
    private static final float SHADOW_RADIUS = 0.0f;
    private static final String TAG = "CIRCLE_IMAGE_VIEW";
    private Paint mBackgroundPaint;
    private BitmapShader mBitmapShader;
    private int mBorderColor;
    private Paint mBorderPaint;
    private int mBorderSelectedColor;
    private float mBorderWidth;
    private boolean mHasBorder;
    private boolean mHasShadow;
    private Bitmap mImage;
    private Paint mImagePaint;
    private boolean mIsSelected;
    private ItemSelectedListener mListener;
    private float mMeasuredHeight;
    private float mMeasuredWidth;
    private float mMinCanvasSide;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mShadowColor;
    private float mShadowDx;
    private float mShadowDy;
    private float mShadowRadius;

    public CircleImageView(Context context) {
        this(context, null, R.styleable.CircleImageViewStyle_circleImageViewDefault);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, R.styleable.CircleImageViewStyle_circleImageViewDefault);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.mImagePaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBackgroundPaint = new Paint();
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
        int backgroundColor = attributes.getColor(R.styleable.CircleImageView_view_backgroundColor, context.getResources().getColor(17170445));
        this.mPaddingLeft = getPaddingLeft();
        this.mPaddingRight = getPaddingRight();
        this.mPaddingTop = getPaddingTop();
        this.mPaddingBottom = getPaddingBottom();
        this.mBorderWidth = attributes.getDimension(R.styleable.CircleImageView_view_borderWidth, 0.0f);
        this.mShadowRadius = attributes.getDimension(R.styleable.CircleImageView_view_shadowRadius, 0.0f);
        setBackgroundColor(backgroundColor);
        this.mImagePaint.setAntiAlias(true);
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBackgroundPaint.setAntiAlias(true);
        this.mBackgroundPaint.setStyle(Paint.Style.FILL);
        if (this.mBorderWidth > 0.0f) {
            this.mBorderColor = attributes.getColor(R.styleable.CircleImageView_view_borderColor, context.getResources().getColor(17170443));
            this.mBorderSelectedColor = attributes.getColor(R.styleable.CircleImageView_view_selectedColor, context.getResources().getColor(17170443));
            setBorderWidth(this.mBorderWidth);
            setBorderColor(this.mBorderColor);
            this.mHasBorder = true;
        }
        if (this.mShadowRadius > 0.0f) {
            this.mShadowDx = attributes.getDimension(R.styleable.CircleImageView_view_shadowDx, 2.0f);
            this.mShadowDy = attributes.getDimension(R.styleable.CircleImageView_view_shadowDy, 2.0f);
            this.mShadowColor = attributes.getColor(R.styleable.CircleImageView_view_shadowColor, context.getResources().getColor(17170444));
            this.mHasShadow = true;
            drawShadow();
        }
        attributes.recycle();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        float maxShadowValue;
        float f;
        float maxShadowValue2 = this.mShadowDx;
        float f2 = this.mShadowDy;
        if (maxShadowValue2 > f2) {
            maxShadowValue = this.mShadowDx;
        } else if (maxShadowValue2 < f2) {
            maxShadowValue = this.mShadowDy;
        } else {
            maxShadowValue = this.mShadowDx;
        }
        this.mMeasuredWidth = (getMeasuredWidth() - this.mPaddingLeft) - this.mPaddingRight;
        float measuredHeight = (getMeasuredHeight() - this.mPaddingTop) - this.mPaddingBottom;
        this.mMeasuredHeight = measuredHeight;
        float oldCanvasSize = this.mMinCanvasSide;
        float f3 = this.mMeasuredWidth;
        if (f3 < measuredHeight) {
            measuredHeight = f3;
        }
        this.mMinCanvasSide = measuredHeight;
        if (oldCanvasSize != measuredHeight) {
            updateBitmapShader();
        }
        this.mImagePaint.setShader(this.mBitmapShader);
        float f4 = this.mMeasuredWidth;
        float centerX = ((f4 - maxShadowValue) / 2.0f) + this.mPaddingLeft;
        float f5 = this.mMeasuredHeight;
        float centerY = ((f5 - maxShadowValue) / 2.0f) + this.mPaddingTop;
        if (f5 > f4) {
            centerY -= (f5 - f4) / 2.0f;
        } else if (f4 > f5) {
            centerX -= (f4 - f5) / 2.0f;
        }
        float f6 = this.mMinCanvasSide;
        float radius = (((f6 / 2.0f) - this.mBorderWidth) - maxShadowValue) - this.mShadowRadius;
        if (this.mHasBorder) {
            if (this.mHasShadow) {
                this.mMinCanvasSide = (int) ((f6 - f) - maxShadowValue);
            }
            RectF rect = getBorderRectF(maxShadowValue);
            if (this.mIsSelected) {
                setBorderColor(this.mBorderSelectedColor);
                canvas.drawArc(rect, CIRCLE_DEGREE, CIRCLE_DEGREE, false, this.mBorderPaint);
            } else {
                setBorderColor(this.mBorderColor);
                canvas.drawArc(rect, CIRCLE_DEGREE, CIRCLE_DEGREE, false, this.mBorderPaint);
            }
        }
        canvas.drawCircle(centerX, centerY, radius, this.mBackgroundPaint);
        canvas.drawCircle(centerX, centerY, radius, this.mImagePaint);
    }

    private RectF getBorderRectF(float maxShadowValue) {
        float f = this.mBorderWidth;
        float f2 = this.mShadowRadius;
        float left = (((f + 0.0f) + f2) / 2.0f) + maxShadowValue;
        float top = (((0.0f + f) + f2) / 2.0f) + maxShadowValue;
        float f3 = this.mMinCanvasSide;
        float right = (f3 - ((f - f2) / 2.0f)) - maxShadowValue;
        float bottom = (f3 - ((f - f2) / 2.0f)) - maxShadowValue;
        int i = this.mPaddingLeft;
        float left2 = left + i;
        int i2 = this.mPaddingTop;
        return new RectF(left2, top + i2, right + i, bottom + i2);
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        ItemSelectedListener itemSelectedListener;
        ItemSelectedListener itemSelectedListener2;
        if (!isClickable()) {
            this.mIsSelected = false;
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case 0:
                boolean z = !this.mIsSelected;
                this.mIsSelected = z;
                if (z && (itemSelectedListener2 = this.mListener) != null) {
                    itemSelectedListener2.onSelected(this);
                    break;
                } else if (!z && (itemSelectedListener = this.mListener) != null) {
                    itemSelectedListener.onUnselected(this);
                    break;
                }
                break;
        }
        invalidate();
        return super.dispatchTouchEvent(event);
    }

    private void drawShadow() {
        if (this.mHasBorder) {
            this.mBorderPaint.setShadowLayer(this.mShadowRadius, this.mShadowDx, this.mShadowDy, this.mShadowColor);
        } else {
            this.mBackgroundPaint.setShadowLayer(this.mShadowRadius, this.mShadowDx, this.mShadowDy, this.mShadowColor);
        }
    }

    public void setOnItemSelectedClickListener(ItemSelectedListener listener) {
        this.mListener = listener;
    }

    @Override // android.view.View
    public void setBackgroundColor(int backgroundColor) {
        Paint paint = this.mBackgroundPaint;
        if (paint != null) {
            paint.setColor(backgroundColor);
        }
        invalidate();
    }

    public void setBorderColor(int borderColor) {
        Paint paint = this.mBorderPaint;
        if (paint != null) {
            paint.setColor(borderColor);
        }
        invalidate();
    }

    public void setBorderWidth(float borderWidth) {
        this.mBorderWidth = borderWidth;
        Paint paint = this.mBorderPaint;
        if (paint != null) {
            paint.setStrokeWidth(borderWidth);
        }
        requestLayout();
        invalidate();
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            Log.i(TAG, "Bitmap drawable!");
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            return null;
        }
        try {
            Bitmap bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            Log.e(TAG, "Encountered OutOfMemoryError while generating bitmap!");
            return null;
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.mImage = drawableToBitmap(getDrawable());
        if (this.mMinCanvasSide > 0.0f) {
            updateBitmapShader();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        this.mImage = drawableToBitmap(getDrawable());
        if (this.mMinCanvasSide > 0.0f) {
            updateBitmapShader();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.mImage = drawableToBitmap(getDrawable());
        if (this.mMinCanvasSide > 0.0f) {
            updateBitmapShader();
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        this.mImage = bm;
        if (this.mMinCanvasSide > 0.0f) {
            updateBitmapShader();
        }
    }

    public void updateBitmapShader() {
        float scale;
        if (this.mImage == null) {
            return;
        }
        this.mBitmapShader = new BitmapShader(this.mImage, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        if (this.mMinCanvasSide != this.mImage.getWidth() || this.mMinCanvasSide != this.mImage.getHeight()) {
            Matrix matrix = new Matrix();
            float f = this.mMeasuredHeight;
            float f2 = this.mMeasuredWidth;
            if (f > f2) {
                scale = (f2 - this.mBorderWidth) / this.mImage.getHeight();
            } else {
                float scale2 = this.mBorderWidth;
                scale = (f - scale2) / this.mImage.getWidth();
            }
            matrix.setScale(scale, scale);
            float f3 = this.mBorderWidth;
            matrix.postTranslate(this.mPaddingLeft + f3, this.mPaddingTop + f3);
            this.mBitmapShader.setLocalMatrix(matrix);
        }
    }
}