package favoritespage.items;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import favoritespage.items.Callback;
import java.util.List;

/* loaded from: classes7.dex */
public class SlideAndDragListView extends FrameLayout implements Callback.OnDragDropListener {
    private static final float DRAG_VIEW_ALPHA = 0.7f;
    private final float BOUND_GAP_RATIO;
    private final int DRAG_SCROLL_PX_UNIT;
    private final long SCROLL_HANDLER_DELAY_MILLIS;
    private boolean interceptTouchEvent;
    private int mBottomScrollBound;
    private int mDragDelta;
    private final Runnable mDragScroller;
    private ImageView mDragView;
    private Bitmap mDragViewBitmap;
    private boolean mIsDragScrollerRunning;
    private int mLastDragY;
    private Handler mScrollHandler;
    private SlideListView mSlideListView;
    private int mTopScrollBound;
    private int mTouchDownForDragStartY;
    private float mTouchSlop;

    /* loaded from: classes7.dex */
    public interface OnDragDropListener {
        void onDragDropViewMoved(int i, int i2);

        void onDragViewDown(int i);

        void onDragViewStart(int i);
    }

    /* loaded from: classes7.dex */
    public interface OnItemDeleteListener {
        void onItemDeleteAnimationFinished(View view, int i);
    }

    /* loaded from: classes7.dex */
    public interface OnItemScrollBackListener {
        void onScrollBackAnimationFinished(View view, int i);
    }

    /* loaded from: classes7.dex */
    public interface OnMenuItemClickListener {
        int onMenuItemClick(View view, int i, int i2, int i3);
    }

    /* loaded from: classes7.dex */
    public interface OnSlideListener {
        void onSlideClose(View view, View view2, int i, int i2);

        void onSlideOpen(View view, View view2, int i, int i2);
    }

    public SlideAndDragListView(Context context) {
        this(context, null);
    }

    public SlideAndDragListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideAndDragListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.SCROLL_HANDLER_DELAY_MILLIS = 5L;
        this.DRAG_SCROLL_PX_UNIT = 25;
        this.BOUND_GAP_RATIO = 0.2f;
        this.mIsDragScrollerRunning = false;
        this.interceptTouchEvent = false;
        this.mDragScroller = new Runnable() { // from class: favoritespage.items.SlideAndDragListView.1
            @Override // java.lang.Runnable
            public void run() {
                if (SlideAndDragListView.this.mLastDragY <= SlideAndDragListView.this.mTopScrollBound) {
                    SlideAndDragListView.this.mSlideListView.smoothScrollBy(-25, 5);
                } else if (SlideAndDragListView.this.mLastDragY >= SlideAndDragListView.this.mBottomScrollBound) {
                    SlideAndDragListView.this.mSlideListView.smoothScrollBy(25, 5);
                }
                SlideAndDragListView.this.mScrollHandler.postDelayed(this, 5L);
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
        createView(context, attrs);
    }

    private void createView(Context context, AttributeSet attrs) {
        SlideListView slideListView = new SlideListView(context, attrs);
        this.mSlideListView = slideListView;
        addView(slideListView, new FrameLayout.LayoutParams(-1, -1));
        ImageView imageView = new ImageView(context);
        this.mDragView = imageView;
        addView(imageView, new FrameLayout.LayoutParams(-2, -2));
        this.mDragView.setVisibility(8);
        this.mSlideListView.setListDragDropListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInterceptTouchEvent(boolean interceptTouchEvent) {
        this.interceptTouchEvent = interceptTouchEvent;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mTouchDownForDragStartY = (int) ev.getY();
        }
        if (this.interceptTouchEvent) {
            int boundGap = (int) (getHeight() * 0.2f);
            this.mTopScrollBound = getTop() + boundGap;
            this.mBottomScrollBound = getBottom() - boundGap;
            this.mSlideListView.handleDragStarted((int) ev.getX(), (int) ev.getY());
        }
        return this.interceptTouchEvent;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int eX = (int) event.getX();
        int eY = (int) event.getY();
        switch (event.getAction() & 255) {
            case 1:
            case 3:
                ensureScrollHandler();
                this.mScrollHandler.removeCallbacks(this.mDragScroller);
                this.mIsDragScrollerRunning = false;
                this.mSlideListView.handleDragFinished(eX, eY);
                this.interceptTouchEvent = false;
                break;
            case 2:
                this.mLastDragY = eY;
                this.mSlideListView.handleDragMoving(eX, eY);
                if (!this.mIsDragScrollerRunning && Math.abs(this.mLastDragY - this.mTouchDownForDragStartY) >= this.mTouchSlop * 4.0f) {
                    this.mIsDragScrollerRunning = true;
                    ensureScrollHandler();
                    this.mScrollHandler.postDelayed(this.mDragScroller, 5L);
                    break;
                }
                break;
        }
        return this.interceptTouchEvent || super.onTouchEvent(event);
    }

    @Override // favoritespage.items.Callback.OnDragDropListener
    public boolean onDragStarted(int x, int y, View view) {
        Bitmap createDraggedChildBitmap = createDraggedChildBitmap(view);
        this.mDragViewBitmap = createDraggedChildBitmap;
        if (createDraggedChildBitmap == null) {
            return false;
        }
        this.mDragView.setImageBitmap(createDraggedChildBitmap);
        this.mDragView.setVisibility(0);
        this.mDragView.setAlpha(DRAG_VIEW_ALPHA);
        this.mDragView.setX(this.mSlideListView.getPaddingLeft() + getPaddingLeft());
        int top = y - view.getTop();
        this.mDragDelta = top;
        this.mDragView.setY(y - top);
        return true;
    }

    private Bitmap createDraggedChildBitmap(View view) {
        if (view instanceof ItemMainLayout) {
            ((ItemMainLayout) view).disableBackgroundDrawable();
        }
        view.setDrawingCacheEnabled(true);
        Bitmap cache = view.getDrawingCache();
        Bitmap bitmap = null;
        if (cache != null) {
            try {
                bitmap = cache.copy(Bitmap.Config.ARGB_8888, false);
            } catch (OutOfMemoryError e) {
                bitmap = null;
            }
        }
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);
        if (view instanceof ItemMainLayout) {
            ((ItemMainLayout) view).enableBackgroundDrawable();
        }
        return bitmap;
    }

    @Override // favoritespage.items.Callback.OnDragDropListener
    public void onDragMoving(int x, int y, View view, OnDragDropListener listener) {
        this.mDragView.setX(this.mSlideListView.getPaddingLeft() + getPaddingLeft());
        this.mDragView.setY(y - this.mDragDelta);
    }

    @Override // favoritespage.items.Callback.OnDragDropListener
    public void onDragFinished(int x, int y, OnDragDropListener listener) {
        this.mDragDelta = 0;
        ImageView imageView = this.mDragView;
        if (imageView != null && imageView.getVisibility() == 0) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this.mDragView, "alpha", DRAG_VIEW_ALPHA, 0.0f);
            objectAnimator.setDuration(100L);
            objectAnimator.addListener(new DragFinishAnimation());
            objectAnimator.start();
        }
    }

    /* loaded from: classes7.dex */
    private class DragFinishAnimation extends AnimatorListenerAdapter {
        private DragFinishAnimation() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            if (SlideAndDragListView.this.mDragViewBitmap != null) {
                SlideAndDragListView.this.mDragViewBitmap.recycle();
                SlideAndDragListView.this.mDragViewBitmap = null;
            }
            SlideAndDragListView.this.mDragView.setVisibility(8);
            SlideAndDragListView.this.mDragView.setImageBitmap(null);
        }
    }

    private void ensureScrollHandler() {
        if (this.mScrollHandler == null) {
            this.mScrollHandler = getHandler();
        }
        if (this.mScrollHandler == null) {
            this.mScrollHandler = new Handler();
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mSlideListView.setOnItemClickListener(listener);
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener listener) {
        this.mSlideListView.setOnItemLongClickListener(listener);
    }

    public void setOnScrollListener(AbsListView.OnScrollListener l) {
        this.mSlideListView.setOnScrollListener(l);
    }

    public void setOnItemDeleteListener(OnItemDeleteListener onItemDeleteListener) {
        this.mSlideListView.setOnItemDeleteListener(onItemDeleteListener);
    }

    public void setOnItemScrollBackListener(OnItemScrollBackListener onItemScrollBackListener) {
        this.mSlideListView.setOnItemScrollBackListener(onItemScrollBackListener);
    }

    public void setOnSlideListener(OnSlideListener listener) {
        this.mSlideListView.setOnSlideListener(listener);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mSlideListView.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    public void setOnDragDropListener(OnDragDropListener onDragDropListener) {
        this.mSlideListView.setOnDragDropListener(onDragDropListener);
    }

    public void closeSlidedItem() {
        this.mSlideListView.closeSlidedItem();
    }

    public void deleteSlideItem() {
        this.mSlideListView.deleteSlideItem();
    }

    public boolean startDrag(int position) {
        return this.mSlideListView.startDrag(position);
    }

    public void setNotDragHeaderCount(int headerCount) {
        WrapperAdapter adapter = this.mSlideListView.getWrapperAdapter();
        if (adapter != null) {
            adapter.setStartLimit(headerCount - 1);
        }
    }

    public void setNotDragFooterCount(int footerCount) {
        WrapperAdapter adapter = this.mSlideListView.getWrapperAdapter();
        if (adapter != null) {
            adapter.setEndLimit(adapter.getCount() - footerCount);
        }
    }

    public void setMenu(Menu menu) {
        this.mSlideListView.setMenu(menu);
    }

    public void setMenu(List<Menu> list) {
        this.mSlideListView.setMenu(list);
    }

    public void setMenu(Menu... menus) {
        this.mSlideListView.setMenu(menus);
    }

    public int getMaxScrollAmount() {
        return this.mSlideListView.getMaxScrollAmount();
    }

    public void addHeaderView(View v, Object data, boolean isSelectable) {
        this.mSlideListView.addHeaderView(v, data, isSelectable);
    }

    public void addHeaderView(View v) {
        this.mSlideListView.addHeaderView(v);
    }

    public int getHeaderViewsCount() {
        return this.mSlideListView.getHeaderViewsCount();
    }

    public boolean removeHeaderView(View v) {
        return this.mSlideListView.removeHeaderView(v);
    }

    public void addFooterView(View v, Object data, boolean isSelectable) {
        this.mSlideListView.addFooterView(v, data, isSelectable);
    }

    public void addFooterView(View v) {
        this.mSlideListView.addFooterView(v);
    }

    public int getFooterViewsCount() {
        return this.mSlideListView.getFooterViewsCount();
    }

    public boolean removeFooterView(View v) {
        return this.mSlideListView.removeFooterView(v);
    }

    public ListAdapter getAdapter() {
        return this.mSlideListView.getAdapter();
    }

    public void setRemoteViewsAdapter(Intent intent) {
        this.mSlideListView.setRemoteViewsAdapter(intent);
    }

    public void setAdapter(ListAdapter adapter) {
        this.mSlideListView.setAdapter(adapter);
    }

    public void smoothScrollToPosition(int position) {
        this.mSlideListView.smoothScrollToPosition(position);
    }

    public void smoothScrollByOffset(int offset) {
        this.mSlideListView.smoothScrollByOffset(offset);
    }

    public void setSelection(int position) {
        this.mSlideListView.setSelection(position);
    }

    public void setSelectionAfterHeaderView() {
        this.mSlideListView.setSelectionAfterHeaderView();
    }

    public void setItemsCanFocus(boolean itemsCanFocus) {
        this.mSlideListView.setItemsCanFocus(itemsCanFocus);
    }

    public boolean getItemsCanFocus() {
        return this.mSlideListView.getItemsCanFocus();
    }

    public void setCacheColorHint(int color) {
        this.mSlideListView.setCacheColorHint(color);
    }

    public Drawable getDivider() {
        return this.mSlideListView.getDivider();
    }

    public void setDivider(Drawable divider) {
        this.mSlideListView.setDivider(divider);
    }

    public int getDividerHeight() {
        return this.mSlideListView.getDividerHeight();
    }

    public void setDividerHeight(int height) {
        this.mSlideListView.setDividerHeight(height);
    }

    public void setHeaderDividersEnabled(boolean headerDividersEnabled) {
        this.mSlideListView.setHeaderDividersEnabled(headerDividersEnabled);
    }

    public boolean areHeaderDividersEnabled() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mSlideListView.areHeaderDividersEnabled();
        }
        return false;
    }

    public void setFooterDividersEnabled(boolean footerDividersEnabled) {
        this.mSlideListView.setFooterDividersEnabled(footerDividersEnabled);
    }

    public boolean areFooterDividersEnabled() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mSlideListView.areFooterDividersEnabled();
        }
        return false;
    }

    public void setOverscrollHeader(Drawable header) {
        this.mSlideListView.setOverscrollHeader(header);
    }

    public Drawable getOverscrollHeader() {
        return this.mSlideListView.getOverscrollHeader();
    }

    public void setOverscrollFooter(Drawable footer) {
        this.mSlideListView.setOverscrollFooter(footer);
    }

    public Drawable getOverscrollFooter() {
        return this.mSlideListView.getOverscrollFooter();
    }

    @Deprecated
    public long[] getCheckItemIds() {
        return this.mSlideListView.getCheckItemIds();
    }

    public int getCheckedItemCount() {
        return this.mSlideListView.getCheckedItemCount();
    }

    public boolean isItemChecked(int position) {
        return this.mSlideListView.isItemChecked(position);
    }

    public int getCheckedItemPosition() {
        return this.mSlideListView.getCheckedItemPosition();
    }

    public SparseBooleanArray getCheckedItemPositions() {
        return this.mSlideListView.getCheckedItemPositions();
    }

    public long[] getCheckedItemIds() {
        return this.mSlideListView.getCheckedItemIds();
    }

    public void clearChoices() {
        this.mSlideListView.clearChoices();
    }

    public void setItemChecked(int position, boolean value) {
        this.mSlideListView.setItemChecked(position, value);
    }

    public boolean performItemClick(View view, int position, long id) {
        return this.mSlideListView.performItemClick(view, position, id);
    }

    public int getChoiceMode() {
        return this.mSlideListView.getChoiceMode();
    }

    public void setChoiceMode(int choiceMode) {
        this.mSlideListView.setChoiceMode(choiceMode);
    }

    public void setMultiChoiceModeListener(AbsListView.MultiChoiceModeListener listener) {
        this.mSlideListView.setMultiChoiceModeListener(listener);
    }

    public void setFastScrollEnabled(boolean enabled) {
        this.mSlideListView.setFastScrollEnabled(enabled);
    }

    public void setFastScrollStyle(int styleResId) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mSlideListView.setFastScrollStyle(styleResId);
        }
    }

    public void setFastScrollAlwaysVisible(boolean alwaysShow) {
        this.mSlideListView.setFastScrollAlwaysVisible(alwaysShow);
    }

    public boolean isFastScrollAlwaysVisible() {
        return this.mSlideListView.isFastScrollAlwaysVisible();
    }

    @ViewDebug.ExportedProperty
    public boolean isFastScrollEnabled() {
        return this.mSlideListView.isFastScrollEnabled();
    }

    public void setSmoothScrollbarEnabled(boolean enabled) {
        this.mSlideListView.setSmoothScrollbarEnabled(enabled);
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSlideListView.isSmoothScrollbarEnabled();
    }

    public boolean isScrollingCacheEnabled() {
        return this.mSlideListView.isScrollingCacheEnabled();
    }

    public void setScrollingCacheEnabled(boolean enabled) {
        this.mSlideListView.setScrollingCacheEnabled(enabled);
    }

    public void setTextFilterEnabled(boolean textFilterEnabled) {
        this.mSlideListView.setTextFilterEnabled(textFilterEnabled);
    }

    @ViewDebug.ExportedProperty
    public boolean isTextFilterEnabled() {
        return this.mSlideListView.isTextFilterEnabled();
    }

    @ViewDebug.ExportedProperty
    public boolean isStackFromBottom() {
        return this.mSlideListView.isStackFromBottom();
    }

    public void setStackFromBottom(boolean stackFromBottom) {
        this.mSlideListView.setStackFromBottom(stackFromBottom);
    }

    public void setFilterText(String filterText) {
        this.mSlideListView.setFilterText(filterText);
    }

    public CharSequence getTextFilter() {
        return this.mSlideListView.getTextFilter();
    }

    @ViewDebug.ExportedProperty
    public View getSelectedView() {
        return this.mSlideListView.getSelectedView();
    }

    public int getListPaddingTop() {
        return this.mSlideListView.getListPaddingTop();
    }

    public int getListPaddingBottom() {
        return this.mSlideListView.getListPaddingBottom();
    }

    public int getListPaddingLeft() {
        return this.mSlideListView.getListPaddingLeft();
    }

    public int getListPaddingRight() {
        return this.mSlideListView.getListPaddingRight();
    }

    public void setDrawSelectorOnTop(boolean onTop) {
        this.mSlideListView.setDrawSelectorOnTop(onTop);
    }

    public void setSelector(int resID) {
        this.mSlideListView.setSelector(resID);
    }

    public void setSelector(Drawable sel) {
        this.mSlideListView.setSelector(sel);
    }

    public Drawable getSelector() {
        return this.mSlideListView.getSelector();
    }

    public void setScrollIndicators(View up, View down) {
        this.mSlideListView.setScrollIndicators(up, down);
    }

    public void setFriction(float friction) {
        this.mSlideListView.setFriction(friction);
    }

    public void setVelocityScale(float scale) {
        this.mSlideListView.setVelocityScale(scale);
    }

    public void smoothScrollToPositionFromTop(int position, int offset, int duration) {
        this.mSlideListView.smoothScrollToPositionFromTop(position, offset, duration);
    }

    public void smoothScrollToPositionFromTop(int position, int offset) {
        this.mSlideListView.smoothScrollToPositionFromTop(position, offset);
    }

    public void smoothScrollToPosition(int position, int boundPosition) {
        this.mSlideListView.smoothScrollToPosition(position, boundPosition);
    }

    public void smoothScrollBy(int distance, int duration) {
        this.mSlideListView.smoothScrollBy(distance, duration);
    }

    public void scrollListBy(int y) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mSlideListView.scrollListBy(y);
        }
    }

    public boolean canScrollList(int direction) {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mSlideListView.canScrollList(direction);
        }
        return false;
    }

    public void invalidateViews() {
        this.mSlideListView.invalidateViews();
    }

    public void setTranscriptMode(int mode) {
        this.mSlideListView.setTranscriptMode(mode);
    }

    public int getTranscriptMode() {
        return this.mSlideListView.getTranscriptMode();
    }

    @ViewDebug.ExportedProperty(category = "drawing")
    public int getCacheColorHint() {
        return this.mSlideListView.getCacheColorHint();
    }

    public void reclaimViews(List<View> views) {
        this.mSlideListView.reclaimViews(views);
    }

    public void setRecyclerListener(AbsListView.RecyclerListener listener) {
        this.mSlideListView.setRecyclerListener(listener);
    }

    public void setSelectionFromTop(int position, int y) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mSlideListView.setSelectionFromTop(position, y);
        }
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        this.mSlideListView.setOnItemSelectedListener(listener);
    }

    public final AdapterView.OnItemSelectedListener getOnItemSelectedListener() {
        return this.mSlideListView.getOnItemSelectedListener();
    }

    @ViewDebug.CapturedViewProperty
    public int getSelectedItemPosition() {
        return this.mSlideListView.getSelectedItemPosition();
    }

    @ViewDebug.CapturedViewProperty
    public long getSelectedItemId() {
        return this.mSlideListView.getSelectedItemId();
    }

    public Object getSelectedItem() {
        return this.mSlideListView.getSelectedItem();
    }

    @ViewDebug.CapturedViewProperty
    public int getCount() {
        return this.mSlideListView.getCount();
    }

    public int getPositionForView(View view) {
        return this.mSlideListView.getPositionForView(view);
    }

    public int getFirstVisiblePosition() {
        return this.mSlideListView.getFirstVisiblePosition();
    }

    public int getLastVisiblePosition() {
        return this.mSlideListView.getLastVisiblePosition();
    }

    public void setEmptyView(View emptyView) {
        this.mSlideListView.setEmptyView(emptyView);
    }

    public View getEmptyView() {
        return this.mSlideListView.getEmptyView();
    }

    public Object getItemAtPosition(int position) {
        return this.mSlideListView.getItemAtPosition(position);
    }

    public long getItemIdAtPosition(int position) {
        return this.mSlideListView.getItemIdAtPosition(position);
    }
}