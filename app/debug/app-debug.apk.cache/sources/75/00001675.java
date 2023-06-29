package com.shoeARstore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.core.view.ViewCompat;

/* loaded from: classes9.dex */
public class ExpandableHeightGridView extends GridView {
    boolean expanded;

    public ExpandableHeightGridView(Context context) {
        super(context);
        this.expanded = true;
    }

    public ExpandableHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.expanded = true;
    }

    public ExpandableHeightGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.expanded = true;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isExpanded()) {
            int expandSpec = View.MeasureSpec.makeMeasureSpec(ViewCompat.MEASURED_SIZE_MASK, Integer.MIN_VALUE);
            super.onMeasure(widthMeasureSpec, expandSpec);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}