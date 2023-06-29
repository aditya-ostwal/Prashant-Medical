package slider.library.Indicators;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.shoeARstore.R;
import java.util.ArrayList;
import java.util.Iterator;
import slider.library.Tricks.InfinitePagerAdapter;
import slider.library.Tricks.ViewPagerEx;

/* loaded from: classes4.dex */
public class PagerIndicator extends LinearLayout implements ViewPagerEx.OnPageChangeListener {
    private DataSetObserver dataChangeObserver;
    private Context mContext;
    private int mDefaultSelectedColor;
    private float mDefaultSelectedHeight;
    private float mDefaultSelectedWidth;
    private int mDefaultUnSelectedColor;
    private float mDefaultUnSelectedHeight;
    private float mDefaultUnSelectedWidth;
    private Shape mIndicatorShape;
    private ArrayList<ImageView> mIndicators;
    private int mItemCount;
    private float mPadding_bottom;
    private float mPadding_left;
    private float mPadding_right;
    private float mPadding_top;
    private ViewPagerEx mPager;
    private ImageView mPreviousSelectedIndicator;
    private int mPreviousSelectedPosition;
    private Drawable mSelectedDrawable;
    private GradientDrawable mSelectedGradientDrawable;
    private LayerDrawable mSelectedLayerDrawable;
    private float mSelectedPadding_Bottom;
    private float mSelectedPadding_Left;
    private float mSelectedPadding_Right;
    private float mSelectedPadding_Top;
    private GradientDrawable mUnSelectedGradientDrawable;
    private LayerDrawable mUnSelectedLayerDrawable;
    private float mUnSelectedPadding_Bottom;
    private float mUnSelectedPadding_Left;
    private float mUnSelectedPadding_Right;
    private float mUnSelectedPadding_Top;
    private Drawable mUnselectedDrawable;
    private int mUserSetSelectedIndicatorResId;
    private int mUserSetUnSelectedIndicatorResId;
    private IndicatorVisibility mVisibility;

    /* loaded from: classes4.dex */
    public enum IndicatorVisibility {
        Visible,
        Invisible
    }

    /* loaded from: classes4.dex */
    public enum Shape {
        Oval,
        Rectangle
    }

    /* loaded from: classes4.dex */
    public enum Unit {
        DP,
        Px
    }

    public PagerIndicator(Context context) {
        this(context, null);
    }

    public PagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mItemCount = 0;
        this.mIndicatorShape = Shape.Oval;
        this.mVisibility = IndicatorVisibility.Visible;
        this.mIndicators = new ArrayList<>();
        this.dataChangeObserver = new DataSetObserver() { // from class: slider.library.Indicators.PagerIndicator.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                int count;
                PagerAdapter adapter = PagerIndicator.this.mPager.getAdapter();
                if (adapter instanceof InfinitePagerAdapter) {
                    count = ((InfinitePagerAdapter) adapter).getRealCount();
                } else {
                    count = adapter.getCount();
                }
                if (count <= PagerIndicator.this.mItemCount) {
                    if (count < PagerIndicator.this.mItemCount) {
                        for (int i = 0; i < PagerIndicator.this.mItemCount - count; i++) {
                            PagerIndicator pagerIndicator = PagerIndicator.this;
                            pagerIndicator.removeView((View) pagerIndicator.mIndicators.get(0));
                            PagerIndicator.this.mIndicators.remove(0);
                        }
                    }
                } else {
                    for (int i2 = 0; i2 < count - PagerIndicator.this.mItemCount; i2++) {
                        ImageView indicator = new ImageView(PagerIndicator.this.mContext);
                        indicator.setImageDrawable(PagerIndicator.this.mUnselectedDrawable);
                        indicator.setPadding((int) PagerIndicator.this.mUnSelectedPadding_Left, (int) PagerIndicator.this.mUnSelectedPadding_Top, (int) PagerIndicator.this.mUnSelectedPadding_Right, (int) PagerIndicator.this.mUnSelectedPadding_Bottom);
                        PagerIndicator.this.addView(indicator);
                        PagerIndicator.this.mIndicators.add(indicator);
                    }
                }
                PagerIndicator.this.mItemCount = count;
                PagerIndicator.this.mPager.setCurrentItem((PagerIndicator.this.mItemCount * 20) + PagerIndicator.this.mPager.getCurrentItem());
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                super.onInvalidated();
                PagerIndicator.this.redraw();
            }
        };
        this.mContext = context;
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PagerIndicator, 0, 0);
        int visibility = attributes.getInt(21, IndicatorVisibility.Visible.ordinal());
        IndicatorVisibility[] values = IndicatorVisibility.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            IndicatorVisibility v = values[i];
            if (v.ordinal() != visibility) {
                i++;
            } else {
                this.mVisibility = v;
                break;
            }
        }
        int shape = attributes.getInt(12, Shape.Oval.ordinal());
        Shape[] values2 = Shape.values();
        int length2 = values2.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length2) {
                break;
            }
            Shape s = values2[i2];
            if (s.ordinal() != shape) {
                i2++;
            } else {
                this.mIndicatorShape = s;
                break;
            }
        }
        this.mUserSetSelectedIndicatorResId = attributes.getResourceId(5, 0);
        this.mUserSetUnSelectedIndicatorResId = attributes.getResourceId(14, 0);
        this.mDefaultSelectedColor = attributes.getColor(4, Color.rgb(255, 255, 255));
        this.mDefaultUnSelectedColor = attributes.getColor(13, Color.argb(33, 255, 255, 255));
        this.mDefaultSelectedWidth = attributes.getDimension(11, (int) pxFromDp(6.0f));
        this.mDefaultSelectedHeight = attributes.getDimensionPixelSize(6, (int) pxFromDp(6.0f));
        this.mDefaultUnSelectedWidth = attributes.getDimensionPixelSize(20, (int) pxFromDp(6.0f));
        this.mDefaultUnSelectedHeight = attributes.getDimensionPixelSize(15, (int) pxFromDp(6.0f));
        this.mSelectedGradientDrawable = new GradientDrawable();
        this.mUnSelectedGradientDrawable = new GradientDrawable();
        this.mPadding_left = attributes.getDimensionPixelSize(1, (int) pxFromDp(3.0f));
        this.mPadding_right = attributes.getDimensionPixelSize(2, (int) pxFromDp(3.0f));
        this.mPadding_top = attributes.getDimensionPixelSize(3, (int) pxFromDp(0.0f));
        this.mPadding_bottom = attributes.getDimensionPixelSize(0, (int) pxFromDp(0.0f));
        this.mSelectedPadding_Left = attributes.getDimensionPixelSize(8, (int) this.mPadding_left);
        this.mSelectedPadding_Right = attributes.getDimensionPixelSize(9, (int) this.mPadding_right);
        this.mSelectedPadding_Top = attributes.getDimensionPixelSize(10, (int) this.mPadding_top);
        this.mSelectedPadding_Bottom = attributes.getDimensionPixelSize(7, (int) this.mPadding_bottom);
        this.mUnSelectedPadding_Left = attributes.getDimensionPixelSize(17, (int) this.mPadding_left);
        this.mUnSelectedPadding_Right = attributes.getDimensionPixelSize(18, (int) this.mPadding_right);
        this.mUnSelectedPadding_Top = attributes.getDimensionPixelSize(19, (int) this.mPadding_top);
        this.mUnSelectedPadding_Bottom = attributes.getDimensionPixelSize(16, (int) this.mPadding_bottom);
        this.mSelectedLayerDrawable = new LayerDrawable(new Drawable[]{this.mSelectedGradientDrawable});
        this.mUnSelectedLayerDrawable = new LayerDrawable(new Drawable[]{this.mUnSelectedGradientDrawable});
        setIndicatorStyleResource(this.mUserSetSelectedIndicatorResId, this.mUserSetUnSelectedIndicatorResId);
        setDefaultIndicatorShape(this.mIndicatorShape);
        setDefaultSelectedIndicatorSize(this.mDefaultSelectedWidth, this.mDefaultSelectedHeight, Unit.Px);
        setDefaultUnselectedIndicatorSize(this.mDefaultUnSelectedWidth, this.mDefaultUnSelectedHeight, Unit.Px);
        setDefaultIndicatorColor(this.mDefaultSelectedColor, this.mDefaultUnSelectedColor);
        setIndicatorVisibility(this.mVisibility);
        attributes.recycle();
    }

    public void setDefaultIndicatorShape(Shape shape) {
        if (this.mUserSetSelectedIndicatorResId == 0) {
            if (shape == Shape.Oval) {
                this.mSelectedGradientDrawable.setShape(1);
            } else {
                this.mSelectedGradientDrawable.setShape(0);
            }
        }
        if (this.mUserSetUnSelectedIndicatorResId == 0) {
            if (shape == Shape.Oval) {
                this.mUnSelectedGradientDrawable.setShape(1);
            } else {
                this.mUnSelectedGradientDrawable.setShape(0);
            }
        }
        resetDrawable();
    }

    public void setIndicatorStyleResource(int selected, int unselected) {
        this.mUserSetSelectedIndicatorResId = selected;
        this.mUserSetUnSelectedIndicatorResId = unselected;
        if (selected == 0) {
            this.mSelectedDrawable = this.mSelectedLayerDrawable;
        } else {
            this.mSelectedDrawable = this.mContext.getResources().getDrawable(this.mUserSetSelectedIndicatorResId);
        }
        if (unselected == 0) {
            this.mUnselectedDrawable = this.mUnSelectedLayerDrawable;
        } else {
            this.mUnselectedDrawable = this.mContext.getResources().getDrawable(this.mUserSetUnSelectedIndicatorResId);
        }
        resetDrawable();
    }

    public void setDefaultIndicatorColor(int selectedColor, int unselectedColor) {
        if (this.mUserSetSelectedIndicatorResId == 0) {
            this.mSelectedGradientDrawable.setColor(selectedColor);
        }
        if (this.mUserSetUnSelectedIndicatorResId == 0) {
            this.mUnSelectedGradientDrawable.setColor(unselectedColor);
        }
        resetDrawable();
    }

    public void setDefaultSelectedIndicatorSize(float width, float height, Unit unit) {
        if (this.mUserSetSelectedIndicatorResId == 0) {
            float w = width;
            float h = height;
            if (unit == Unit.DP) {
                w = pxFromDp(width);
                h = pxFromDp(height);
            }
            this.mSelectedGradientDrawable.setSize((int) w, (int) h);
            resetDrawable();
        }
    }

    public void setDefaultUnselectedIndicatorSize(float width, float height, Unit unit) {
        if (this.mUserSetUnSelectedIndicatorResId == 0) {
            float w = width;
            float h = height;
            if (unit == Unit.DP) {
                w = pxFromDp(width);
                h = pxFromDp(height);
            }
            this.mUnSelectedGradientDrawable.setSize((int) w, (int) h);
            resetDrawable();
        }
    }

    public void setDefaultIndicatorSize(float width, float height, Unit unit) {
        setDefaultSelectedIndicatorSize(width, height, unit);
        setDefaultUnselectedIndicatorSize(width, height, unit);
    }

    private float dpFromPx(float px) {
        return px / getContext().getResources().getDisplayMetrics().density;
    }

    private float pxFromDp(float dp) {
        return getContext().getResources().getDisplayMetrics().density * dp;
    }

    public void setIndicatorVisibility(IndicatorVisibility visibility) {
        if (visibility == IndicatorVisibility.Visible) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
        resetDrawable();
    }

    public void destroySelf() {
        ViewPagerEx viewPagerEx = this.mPager;
        if (viewPagerEx == null || viewPagerEx.getAdapter() == null) {
            return;
        }
        InfinitePagerAdapter wrapper = (InfinitePagerAdapter) this.mPager.getAdapter();
        PagerAdapter adapter = wrapper.getRealAdapter();
        if (adapter != null) {
            adapter.unregisterDataSetObserver(this.dataChangeObserver);
        }
        removeAllViews();
    }

    public void setViewPager(ViewPagerEx pager) {
        if (pager.getAdapter() == null) {
            throw new IllegalStateException("Viewpager does not have adapter instance");
        }
        this.mPager = pager;
        pager.addOnPageChangeListener(this);
        ((InfinitePagerAdapter) this.mPager.getAdapter()).getRealAdapter().registerDataSetObserver(this.dataChangeObserver);
    }

    private void resetDrawable() {
        Iterator<ImageView> it = this.mIndicators.iterator();
        while (it.hasNext()) {
            View i = it.next();
            ImageView imageView = this.mPreviousSelectedIndicator;
            if (imageView != null && imageView.equals(i)) {
                ((ImageView) i).setImageDrawable(this.mSelectedDrawable);
            } else {
                ((ImageView) i).setImageDrawable(this.mUnselectedDrawable);
            }
        }
    }

    public void redraw() {
        this.mItemCount = getShouldDrawCount();
        this.mPreviousSelectedIndicator = null;
        Iterator<ImageView> it = this.mIndicators.iterator();
        while (it.hasNext()) {
            View i = it.next();
            removeView(i);
        }
        for (int i2 = 0; i2 < this.mItemCount; i2++) {
            ImageView indicator = new ImageView(this.mContext);
            indicator.setImageDrawable(this.mUnselectedDrawable);
            indicator.setPadding((int) this.mUnSelectedPadding_Left, (int) this.mUnSelectedPadding_Top, (int) this.mUnSelectedPadding_Right, (int) this.mUnSelectedPadding_Bottom);
            addView(indicator);
            this.mIndicators.add(indicator);
        }
        int i3 = this.mPreviousSelectedPosition;
        setItemAsSelected(i3);
    }

    private int getShouldDrawCount() {
        if (this.mPager.getAdapter() instanceof InfinitePagerAdapter) {
            return ((InfinitePagerAdapter) this.mPager.getAdapter()).getRealCount();
        }
        return this.mPager.getAdapter().getCount();
    }

    private void setItemAsSelected(int position) {
        ImageView imageView = this.mPreviousSelectedIndicator;
        if (imageView != null) {
            imageView.setImageDrawable(this.mUnselectedDrawable);
            this.mPreviousSelectedIndicator.setPadding((int) this.mUnSelectedPadding_Left, (int) this.mUnSelectedPadding_Top, (int) this.mUnSelectedPadding_Right, (int) this.mUnSelectedPadding_Bottom);
        }
        ImageView currentSelected = (ImageView) getChildAt(position + 1);
        if (currentSelected != null) {
            currentSelected.setImageDrawable(this.mSelectedDrawable);
            currentSelected.setPadding((int) this.mSelectedPadding_Left, (int) this.mSelectedPadding_Top, (int) this.mSelectedPadding_Right, (int) this.mSelectedPadding_Bottom);
            this.mPreviousSelectedIndicator = currentSelected;
        }
        this.mPreviousSelectedPosition = position;
    }

    @Override // slider.library.Tricks.ViewPagerEx.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public IndicatorVisibility getIndicatorVisibility() {
        return this.mVisibility;
    }

    @Override // slider.library.Tricks.ViewPagerEx.OnPageChangeListener
    public void onPageSelected(int position) {
        if (this.mItemCount == 0) {
            return;
        }
        setItemAsSelected(position - 1);
    }

    @Override // slider.library.Tricks.ViewPagerEx.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
    }

    public int getSelectedIndicatorResId() {
        return this.mUserSetSelectedIndicatorResId;
    }

    public int getUnSelectedIndicatorResId() {
        return this.mUserSetUnSelectedIndicatorResId;
    }
}