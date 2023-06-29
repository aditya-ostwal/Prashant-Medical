package favoritespage.items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
class ItemBackGroundLayout extends ViewGroup implements View.OnClickListener {
    private int mDirection;
    private int mMarginLeft;
    private int mMarginRight;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private Map<View, Integer> mViewPositionMap;

    /* loaded from: classes7.dex */
    protected interface OnMenuItemClickListener {
        void onClick(int i, int i2, View view);
    }

    public ItemBackGroundLayout(Context context) {
        this(context, null);
    }

    public ItemBackGroundLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemBackGroundLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMarginLeft = 0;
        this.mMarginRight = 0;
        this.mDirection = -1;
        this.mViewPositionMap = new HashMap();
        setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addMenuItem(MenuItem menuItem, int position) {
        int count = getChildCount();
        BaseLayout parent = new SDMenuItemView(getContext(), menuItem);
        parent.build();
        addView(parent, count);
        this.mViewPositionMap.put(parent, Integer.valueOf(position));
        parent.setOnClickListener(this);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setDirection(int direction) {
        this.mDirection = direction;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int total = getChildCount();
        for (int i = 0; i < total; i++) {
            BaseLayout view = (BaseLayout) getChildAt(i);
            MenuItem menuItem = view.mMenuItem;
            measureChild(view, View.MeasureSpec.makeMeasureSpec(menuItem.width, BasicMeasure.EXACTLY), heightMeasureSpec);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int total = getChildCount();
        this.mMarginLeft = 0;
        this.mMarginRight = getMeasuredWidth();
        for (int i = 0; i < total; i++) {
            BaseLayout view = (BaseLayout) getChildAt(i);
            MenuItem menuItem = view.mMenuItem;
            if (menuItem.direction == 1) {
                view.layout(this.mMarginLeft, t, menuItem.width + this.mMarginLeft, b);
                this.mMarginLeft += menuItem.width;
            } else {
                view.layout(this.mMarginRight - menuItem.width, t, this.mMarginRight, b);
                this.mMarginRight -= menuItem.width;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasMenuItemViews() {
        return this.mViewPositionMap.size() != 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer position = this.mViewPositionMap.get(v);
        OnMenuItemClickListener onMenuItemClickListener = this.mOnMenuItemClickListener;
        if (onMenuItemClickListener != null) {
            onMenuItemClickListener.onClick(position.intValue(), this.mDirection, v);
        }
    }
}