package favoritespage.items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.Scroller;

/* loaded from: classes7.dex */
class ItemMainLayout extends FrameLayout {
    private static final int INTENTION_LEFT_ALREADY_OPEN = 3;
    private static final int INTENTION_LEFT_CLOSE = 2;
    private static final int INTENTION_LEFT_OPEN = 1;
    private static final int INTENTION_RIGHT_ALREADY_OPEN = -3;
    private static final int INTENTION_RIGHT_CLOSE = -2;
    private static final int INTENTION_RIGHT_OPEN = -1;
    private static final int INTENTION_SCROLL_BACK = -4;
    private static final int INTENTION_ZERO = 0;
    protected static final int SCROLL_BACK_ALREADY_CLOSED = 2;
    protected static final int SCROLL_BACK_CLICK_MENU_BUTTON = 3;
    protected static final int SCROLL_BACK_CLICK_NOTHING = 0;
    protected static final int SCROLL_BACK_CLICK_OWN = 1;
    private static final int SCROLL_BACK_TIME = 250;
    private static final int SCROLL_DELETE_TIME = 300;
    protected static final int SCROLL_STATE_CLOSE = 0;
    protected static final int SCROLL_STATE_OPEN = 1;
    private static final int SCROLL_TIME = 500;
    private int mBtnLeftTotalWidth;
    private int mBtnRightTotalWidth;
    private int mIntention;
    private boolean mIsMoving;
    private View mItemCustomView;
    private ItemBackGroundLayout mItemLeftBackGroundLayout;
    private ItemBackGroundLayout mItemRightBackGroundLayout;
    private Drawable mNormalCustomBackgroundDrawable;
    private Drawable mNormalListSelectorDrawable;
    private OnItemScrollBackListenerProxy mOnItemScrollBackListenerProxy;
    private OnItemSlideListenerProxy mOnItemSlideListenerProxy;
    private int mScrollState;
    private Scroller mScroller;
    private Drawable mTotalCustomBackgroundDrawable;
    private Drawable mTotalListSelectorDrawable;
    private int mTouchSlop;
    private boolean mWannaOver;

    /* loaded from: classes7.dex */
    protected interface OnItemDeleteListenerProxy {
        void onDelete(View view);

        void onDeleteBegin();
    }

    /* loaded from: classes7.dex */
    protected interface OnItemScrollBackListenerProxy {
        void onScrollBack(View view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes7.dex */
    public interface OnItemSlideListenerProxy {
        void onSlideClose(View view, int i);

        void onSlideOpen(View view, int i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ItemMainLayout(Context context, View customView) {
        super(context);
        this.mIntention = 0;
        this.mScrollState = 0;
        this.mIsMoving = false;
        this.mWannaOver = true;
        this.mTouchSlop = 0;
        this.mScroller = new Scroller(context);
        ItemBackGroundLayout itemBackGroundLayout = new ItemBackGroundLayout(context);
        this.mItemRightBackGroundLayout = itemBackGroundLayout;
        addView(itemBackGroundLayout, new FrameLayout.LayoutParams(-1, -1));
        ItemBackGroundLayout itemBackGroundLayout2 = new ItemBackGroundLayout(context);
        this.mItemLeftBackGroundLayout = itemBackGroundLayout2;
        addView(itemBackGroundLayout2, new FrameLayout.LayoutParams(-1, -1));
        this.mItemCustomView = customView;
        ViewGroup.LayoutParams layoutParams = customView.getLayoutParams();
        if (layoutParams == null) {
            addView(this.mItemCustomView, new FrameLayout.LayoutParams(-1, -1));
        } else {
            addView(this.mItemCustomView, layoutParams);
        }
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        initBackgroundDrawable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View getItemCustomView() {
        return this.mItemCustomView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ItemBackGroundLayout getItemLeftBackGroundLayout() {
        return this.mItemLeftBackGroundLayout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ItemBackGroundLayout getItemRightBackGroundLayout() {
        return this.mItemRightBackGroundLayout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setParams(int btnLeftTotalWidth, int btnRightTotalWidth, boolean wannaOver) {
        requestLayout();
        this.mBtnLeftTotalWidth = btnLeftTotalWidth;
        this.mBtnRightTotalWidth = btnRightTotalWidth;
        this.mWannaOver = wannaOver;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSelector(Drawable drawable) {
        Compat.setBackgroundDrawable(this.mItemLeftBackGroundLayout, drawable);
        Compat.setBackgroundDrawable(this.mItemRightBackGroundLayout, drawable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleMotionEvent(MotionEvent ev, float xDown, float yDown, int leftDistance) {
        getParent().requestDisallowInterceptTouchEvent(false);
        switch (ev.getAction() & 255) {
            case 1:
            case 3:
                switch (this.mIntention) {
                    case -3:
                    case -2:
                    case -1:
                        int abs = Math.abs(this.mItemCustomView.getLeft());
                        int i = this.mBtnRightTotalWidth;
                        if (abs > i / 2) {
                            this.mIntention = -1;
                            int delta = i - Math.abs(this.mItemCustomView.getLeft());
                            this.mScroller.startScroll(this.mItemCustomView.getLeft(), 0, -delta, 0, SCROLL_TIME);
                            OnItemSlideListenerProxy onItemSlideListenerProxy = this.mOnItemSlideListenerProxy;
                            if (onItemSlideListenerProxy != null && this.mScrollState != 1) {
                                onItemSlideListenerProxy.onSlideOpen(this, -1);
                            }
                            this.mScrollState = 1;
                            break;
                        } else {
                            this.mIntention = -2;
                            this.mScroller.startScroll(this.mItemCustomView.getLeft(), 0, -this.mItemCustomView.getLeft(), 0, SCROLL_TIME);
                            OnItemSlideListenerProxy onItemSlideListenerProxy2 = this.mOnItemSlideListenerProxy;
                            if (onItemSlideListenerProxy2 != null && this.mScrollState != 0) {
                                onItemSlideListenerProxy2.onSlideClose(this, -1);
                            }
                            this.mScrollState = 0;
                            break;
                        }
                        break;
                    case 1:
                    case 2:
                    case 3:
                        int abs2 = Math.abs(this.mItemCustomView.getLeft());
                        int i2 = this.mBtnLeftTotalWidth;
                        if (abs2 > i2 / 2) {
                            this.mIntention = 1;
                            int delta2 = i2 - Math.abs(this.mItemCustomView.getLeft());
                            this.mScroller.startScroll(this.mItemCustomView.getLeft(), 0, delta2, 0, SCROLL_TIME);
                            OnItemSlideListenerProxy onItemSlideListenerProxy3 = this.mOnItemSlideListenerProxy;
                            if (onItemSlideListenerProxy3 != null && this.mScrollState != 1) {
                                onItemSlideListenerProxy3.onSlideOpen(this, 1);
                            }
                            this.mScrollState = 1;
                            break;
                        } else {
                            this.mIntention = 2;
                            this.mScroller.startScroll(this.mItemCustomView.getLeft(), 0, -this.mItemCustomView.getLeft(), 0, SCROLL_TIME);
                            OnItemSlideListenerProxy onItemSlideListenerProxy4 = this.mOnItemSlideListenerProxy;
                            if (onItemSlideListenerProxy4 != null && this.mScrollState != 0) {
                                onItemSlideListenerProxy4.onSlideClose(this, 1);
                            }
                            this.mScrollState = 0;
                            break;
                        }
                        break;
                }
                this.mIntention = 0;
                postInvalidate();
                this.mIsMoving = false;
                return;
            case 2:
                if (fingerNotMove(ev, xDown, yDown) && !this.mIsMoving) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return;
                } else if (fingerLeftAndRightMove(ev, xDown, yDown) || this.mIsMoving) {
                    disableBackgroundDrawable();
                    this.mIsMoving = true;
                    getParent().requestDisallowInterceptTouchEvent(true);
                    float moveDistance = ev.getX() - xDown;
                    if (moveDistance > 0.0f) {
                        if (leftDistance == 0) {
                            this.mIntention = 1;
                            setBackGroundVisible(true, false);
                        } else if (leftDistance < 0) {
                            this.mIntention = -2;
                            setBackGroundVisible(false, true);
                        } else if (leftDistance > 0) {
                            this.mIntention = 3;
                            setBackGroundVisible(true, false);
                        }
                    } else if (moveDistance < 0.0f) {
                        if (leftDistance == 0) {
                            this.mIntention = -1;
                            setBackGroundVisible(false, true);
                        } else if (leftDistance < 0) {
                            this.mIntention = -3;
                            setBackGroundVisible(false, true);
                        } else if (leftDistance > 0) {
                            this.mIntention = 2;
                            setBackGroundVisible(true, false);
                        }
                    }
                    switch (this.mIntention) {
                        case -3:
                        case -1:
                            if (this.mItemRightBackGroundLayout.hasMenuItemViews()) {
                                float distanceRightOpen = leftDistance + moveDistance;
                                if (!this.mWannaOver) {
                                    int i3 = this.mBtnRightTotalWidth;
                                    distanceRightOpen = (-distanceRightOpen) > ((float) i3) ? -i3 : distanceRightOpen;
                                }
                                View view = this.mItemCustomView;
                                view.layout((int) distanceRightOpen, view.getTop(), this.mItemCustomView.getWidth() + ((int) distanceRightOpen), this.mItemCustomView.getBottom());
                                return;
                            }
                            return;
                        case -2:
                            float distanceRightClose = ((float) leftDistance) + moveDistance <= 0.0f ? leftDistance + moveDistance : 0.0f;
                            View view2 = this.mItemCustomView;
                            view2.layout((int) distanceRightClose, view2.getTop(), this.mItemCustomView.getWidth() + ((int) distanceRightClose), this.mItemCustomView.getBottom());
                            return;
                        case 0:
                        default:
                            return;
                        case 1:
                        case 3:
                            if (this.mItemLeftBackGroundLayout.hasMenuItemViews()) {
                                float distanceLeftOpen = leftDistance + moveDistance;
                                if (!this.mWannaOver) {
                                    int i4 = this.mBtnLeftTotalWidth;
                                    distanceLeftOpen = distanceLeftOpen > ((float) i4) ? i4 : distanceLeftOpen;
                                }
                                View view3 = this.mItemCustomView;
                                view3.layout((int) distanceLeftOpen, view3.getTop(), this.mItemCustomView.getWidth() + ((int) distanceLeftOpen), this.mItemCustomView.getBottom());
                                return;
                            }
                            return;
                        case 2:
                            float distanceLeftClose = ((float) leftDistance) + moveDistance >= 0.0f ? leftDistance + moveDistance : 0.0f;
                            View view4 = this.mItemCustomView;
                            view4.layout((int) distanceLeftClose, view4.getTop(), this.mItemCustomView.getWidth() + ((int) distanceLeftClose), this.mItemCustomView.getBottom());
                            return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void setBackGroundVisible(boolean leftVisible, boolean rightVisible) {
        if (leftVisible) {
            if (this.mItemLeftBackGroundLayout.getVisibility() != 0) {
                this.mItemLeftBackGroundLayout.setVisibility(0);
            }
        } else if (this.mItemLeftBackGroundLayout.getVisibility() == 0) {
            this.mItemLeftBackGroundLayout.setVisibility(8);
        }
        if (rightVisible) {
            if (this.mItemRightBackGroundLayout.getVisibility() != 0) {
                this.mItemRightBackGroundLayout.setVisibility(0);
            }
        } else if (this.mItemRightBackGroundLayout.getVisibility() == 0) {
            this.mItemRightBackGroundLayout.setVisibility(8);
        }
    }

    private boolean fingerNotMove(MotionEvent ev, float xDown, float yDown) {
        return xDown - ev.getX() < ((float) this.mTouchSlop) && xDown - ev.getX() > ((float) (-this.mTouchSlop)) && yDown - ev.getY() < ((float) this.mTouchSlop) && yDown - ev.getY() > ((float) (-this.mTouchSlop));
    }

    private boolean fingerLeftAndRightMove(MotionEvent ev, float xDown, float yDown) {
        return (ev.getX() - xDown > ((float) this.mTouchSlop) || ev.getX() - xDown < ((float) (-this.mTouchSlop))) && ev.getY() - yDown < ((float) this.mTouchSlop) && ev.getY() - yDown > ((float) (-this.mTouchSlop));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void deleteItem(final OnItemDeleteListenerProxy onItemDeleteListenerProxy) {
        scrollBack();
        final int height = getMeasuredHeight();
        Animation.AnimationListener animationListener = new Animation.AnimationListener() { // from class: favoritespage.items.ItemMainLayout.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                OnItemDeleteListenerProxy onItemDeleteListenerProxy2 = onItemDeleteListenerProxy;
                if (onItemDeleteListenerProxy2 != null) {
                    onItemDeleteListenerProxy2.onDeleteBegin();
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ViewGroup.LayoutParams layoutParams = ItemMainLayout.this.getLayoutParams();
                layoutParams.height = height;
                ItemMainLayout.this.setLayoutParams(layoutParams);
                OnItemDeleteListenerProxy onItemDeleteListenerProxy2 = onItemDeleteListenerProxy;
                if (onItemDeleteListenerProxy2 != null) {
                    onItemDeleteListenerProxy2.onDelete(ItemMainLayout.this);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }
        };
        Animation animation = new Animation() { // from class: favoritespage.items.ItemMainLayout.2
            @Override // android.view.animation.Animation
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                ViewGroup.LayoutParams layoutParams = ItemMainLayout.this.getLayoutParams();
                int i = height;
                layoutParams.height = i - ((int) (i * interpolatedTime));
                ItemMainLayout.this.setLayoutParams(layoutParams);
            }
        };
        animation.setAnimationListener(animationListener);
        animation.setDuration(300L);
        startAnimation(animation);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            int left = this.mScroller.getCurrX();
            View view = this.mItemCustomView;
            view.layout(left, view.getTop(), this.mScroller.getCurrX() + this.mItemCustomView.getWidth(), this.mItemCustomView.getBottom());
            postInvalidate();
            if (left == 0) {
                setBackGroundVisible(false, false);
                enableBackgroundDrawable();
                OnItemScrollBackListenerProxy onItemScrollBackListenerProxy = this.mOnItemScrollBackListenerProxy;
                if (onItemScrollBackListenerProxy != null) {
                    onItemScrollBackListenerProxy.onScrollBack(this);
                    this.mOnItemScrollBackListenerProxy = null;
                }
            }
        }
        super.computeScroll();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void scrollBack() {
        this.mIntention = -4;
        this.mScroller.startScroll(this.mItemCustomView.getLeft(), 0, -this.mItemCustomView.getLeft(), 0, 250);
        OnItemSlideListenerProxy onItemSlideListenerProxy = this.mOnItemSlideListenerProxy;
        if (onItemSlideListenerProxy != null && this.mScrollState != 0) {
            onItemSlideListenerProxy.onSlideClose(this, getItemCustomView().getLeft() < 0 ? 1 : -1);
        }
        postInvalidate();
        this.mScrollState = 0;
        enableBackgroundDrawable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void scrollBack(OnItemScrollBackListenerProxy onItemScrollBackListenerProxy) {
        this.mOnItemScrollBackListenerProxy = onItemScrollBackListenerProxy;
        this.mIntention = -4;
        this.mScroller.startScroll(this.mItemCustomView.getLeft(), 0, -this.mItemCustomView.getLeft(), 0, 250);
        OnItemSlideListenerProxy onItemSlideListenerProxy = this.mOnItemSlideListenerProxy;
        if (onItemSlideListenerProxy != null && this.mScrollState != 0) {
            onItemSlideListenerProxy.onSlideClose(this, getItemCustomView().getLeft() < 0 ? 1 : -1);
        }
        postInvalidate();
        this.mScrollState = 0;
        enableBackgroundDrawable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int scrollBack(float x) {
        if (this.mScrollState == 0) {
            return 2;
        }
        if (this.mItemCustomView.getLeft() > 0) {
            if (x > this.mItemCustomView.getLeft()) {
                scrollBack();
                this.mScrollState = 0;
                return 1;
            }
            return 3;
        } else if (this.mItemCustomView.getLeft() < 0 && x < this.mItemCustomView.getRight()) {
            scrollBack();
            this.mScrollState = 0;
            return 1;
        } else {
            return 3;
        }
    }

    private void initBackgroundDrawable() {
        Drawable drawable = getItemCustomView().getBackground();
        if (drawable != null) {
            if (drawable instanceof StateListDrawable) {
                StateListDrawable stateListDrawable = (StateListDrawable) drawable;
                this.mNormalCustomBackgroundDrawable = stateListDrawable.getCurrent();
            } else {
                this.mNormalCustomBackgroundDrawable = drawable;
            }
            this.mTotalCustomBackgroundDrawable = drawable;
        }
        Drawable listDrawable = getItemLeftBackGroundLayout().getBackground();
        if (listDrawable != null) {
            if (listDrawable instanceof StateListDrawable) {
                StateListDrawable stateListDrawable2 = (StateListDrawable) listDrawable;
                this.mNormalListSelectorDrawable = stateListDrawable2.getCurrent();
            } else {
                this.mNormalListSelectorDrawable = listDrawable;
            }
            this.mTotalListSelectorDrawable = listDrawable;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void disableBackgroundDrawable() {
        if (this.mNormalCustomBackgroundDrawable != null) {
            Compat.setBackgroundDrawable(getItemCustomView(), this.mNormalCustomBackgroundDrawable);
        }
        if (this.mNormalListSelectorDrawable != null) {
            Compat.setBackgroundDrawable(getItemLeftBackGroundLayout(), this.mNormalListSelectorDrawable);
            Compat.setBackgroundDrawable(getItemRightBackGroundLayout(), this.mNormalListSelectorDrawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void enableBackgroundDrawable() {
        if (this.mTotalCustomBackgroundDrawable != null) {
            Compat.setBackgroundDrawable(getItemCustomView(), this.mTotalCustomBackgroundDrawable);
        }
        if (this.mTotalListSelectorDrawable != null) {
            Compat.setBackgroundDrawable(getItemLeftBackGroundLayout(), this.mTotalListSelectorDrawable);
            Compat.setBackgroundDrawable(getItemRightBackGroundLayout(), this.mTotalListSelectorDrawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnItemSlideListenerProxy(OnItemSlideListenerProxy onItemSlideListenerProxy) {
        this.mOnItemSlideListenerProxy = onItemSlideListenerProxy;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getScrollState() {
        return this.mScrollState;
    }
}