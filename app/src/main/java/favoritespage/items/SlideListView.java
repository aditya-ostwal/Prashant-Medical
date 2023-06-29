/*
 * Copyright (C) 2015 yydcdut (yuyidong2015@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package favoritespage.items;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created.
 */
class SlideListView<T> extends DragListView implements WrapperAdapter.OnAdapterSlideListenerProxy,
        WrapperAdapter.OnAdapterMenuClickListenerProxy, WrapperAdapter.onItemDeleteListenerProxy,
        WrapperAdapter.OnScrollListenerProxy, AbsListView.OnItemLongClickListener, WrapperAdapter.OnItemScrollBackListenerProxy {
    private static final int STATE_NOTHING = -1;
    private static final int STATE_DOWN = 0;
    private static final int STATE_SCROLL = 2;
    private static final int STATE_LONG_CLICK_FINISH = 3;
    private static final int STATE_MORE_FINGERS = 4;
    private int mState = STATE_NOTHING;

    private static final int RETURN_SCROLL_BACK_OWN = 1;
    private static final int RETURN_SCROLL_BACK_OTHER = 2;
    private static final int RETURN_SCROLL_BACK_CLICK_MENU_BUTTON = 3;
    private static final int RETURN_SCROLL_BACK_NOTHING = 0;


    private boolean mIsWannaTriggerClick = true;

    private boolean mIsScrolling = false;

    private boolean mIsDeleteAnimationRunning = false;

    private int mXDown;
    private int mYDown;
    /* Menu */
    private SparseArray<Menu> mMenuSparseArray;

    private WrapperAdapter mWrapperAdapter;

    private int mShortestDistance = 25;

    private int mItemLeftDistance = 0;

    private boolean isItemViewHandlingMotionEvent = false;


    private SlideAndDragListView.OnSlideListener mOnSlideListener;
    private SlideAndDragListView.OnMenuItemClickListener mOnMenuItemClickListener;
    private Callback.OnItemLongClickListenerWrapper mOnListItemLongClickListener;
    private Callback.OnItemClickListenerWrapper mOnListItemClickListener;
    private SlideAndDragListView.OnItemDeleteListener mOnItemDeleteListener;
    private SlideAndDragListView.OnItemScrollBackListener mOnItemScrollBackListener;
    private Callback.OnScrollListenerWrapper mOnListScrollListener;

    public SlideListView(Context context) {
        this(context, null);
    }

    public SlideListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mShortestDistance = ViewConfiguration.get(context).getScaledTouchSlop();
        super.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

        View view = getChildAt(position - getFirstVisiblePosition());
        if (mOnListItemLongClickListener != null && view instanceof ItemMainLayout) {
            ItemMainLayout itemMainLayout = (ItemMainLayout) view;
            if (itemMainLayout.getItemCustomView().getLeft() == 0) {
                mState = STATE_LONG_CLICK_FINISH;

                mWrapperAdapter.returnSlideItemPosition();

                mOnListItemLongClickListener.onListItemLongClick(itemMainLayout.getItemCustomView(), position);
            }
        }
        if (mState == STATE_LONG_CLICK_FINISH || mState == STATE_DOWN) {
            startDrag(position);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mXDown = (int) ev.getX();
                mYDown = (int) ev.getY();

                mState = STATE_DOWN;
                ItemMainLayout itemMainLayoutDown = getItemMainLayoutByPosition((int) ev.getX(), (int) ev.getY());
                if (itemMainLayoutDown != null) {
                    mItemLeftDistance = itemMainLayoutDown.getItemCustomView().getLeft();
                } else {
                    mItemLeftDistance = 0;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (fingerLeftAndRightMove(ev)) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mIsDeleteAnimationRunning) {
            return false;
        }
        if (mIsScrolling) {
            return super.onTouchEvent(ev);
        }
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                mXDown = (int) ev.getX();
                mYDown = (int) ev.getY();

                mState = STATE_DOWN;

                ItemMainLayout itemMainLayoutDown = getItemMainLayoutByPosition(mXDown, mYDown);
                if (itemMainLayoutDown != null) {
                    mItemLeftDistance = itemMainLayoutDown.getItemCustomView().getLeft();
                } else {
                    mItemLeftDistance = 0;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mState = STATE_MORE_FINGERS;
                return false;
            case MotionEvent.ACTION_MOVE:
                if (fingerLeftAndRightMove(ev) && !isItemViewHandlingMotionEvent) {
                    int position = pointToPosition(mXDown, mYDown);
                    ItemMainLayout itemMainLayout = getItemMainLayoutByPosition(mXDown, mYDown);
                    if (itemMainLayout != null) {

                        if (mItemLeftDistance > 0) {
                            if (ev.getX() < mItemLeftDistance) {
                                return true;
                            }
                        } else if (mItemLeftDistance < 0) {
                            if (ev.getX() > mItemLeftDistance + itemMainLayout.getItemCustomView().getWidth()) {
                                return true;
                            }
                        }


                        if (isFingerMoving2Right(ev)) {
                            if (!itemMainLayout.getItemLeftBackGroundLayout().hasMenuItemViews() &&
                                    itemMainLayout.getScrollState() == ItemMainLayout.SCROLL_STATE_CLOSE) {
                                mState = STATE_NOTHING;
                                return true;
                            }
                        } else if (isFingerMoving2Left(ev)) {
                            if (!itemMainLayout.getItemRightBackGroundLayout().hasMenuItemViews() &&
                                    itemMainLayout.getScrollState() == ItemMainLayout.SCROLL_STATE_CLOSE) {
                                mState = STATE_NOTHING;
                                return true;
                            }
                        }

                        mWrapperAdapter.setSlideItemPosition(position);
                        isItemViewHandlingMotionEvent = true;
                        mState = STATE_SCROLL;
                        itemMainLayout.handleMotionEvent(ev, mXDown, mYDown, mItemLeftDistance);
                        return true;
                    } else {
                        mState = STATE_NOTHING;
                        return true;
                    }
                } else {
                    if (isItemViewHandlingMotionEvent) {
                        ItemMainLayout itemMainLayout = getItemMainLayoutByPosition(mXDown, mYDown);
                        if (itemMainLayout != null) {
                            itemMainLayout.handleMotionEvent(ev, mXDown, mYDown, mItemLeftDistance);
                            return true;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                int position = pointToPosition(mXDown, mYDown);
                if (position != AdapterView.INVALID_POSITION) {
                    if (mState == STATE_DOWN || mState == STATE_LONG_CLICK_FINISH) {

                        int scrollBackState = scrollBack(position, ev.getX());
                        if (scrollBackState == RETURN_SCROLL_BACK_NOTHING) {
                            if (mOnListItemClickListener != null && mIsWannaTriggerClick && !mIsScrolling) {
                                View v = getChildAt(position - getFirstVisiblePosition());
                                if (v instanceof ItemMainLayout) {
                                    ItemMainLayout itemMainLayout = (ItemMainLayout) v;
                                    mOnListItemClickListener.onListItemClick(itemMainLayout.getItemCustomView(), position);
                                }
                            }
                        }
                    } else {
                        ItemMainLayout itemMainLayout = getItemMainLayoutByPosition(mXDown, mYDown);
                        if (itemMainLayout != null) {
                            itemMainLayout.handleMotionEvent(ev, mXDown, mYDown, -1);
                        }
                    }
                }
                mState = STATE_NOTHING;
                mItemLeftDistance = 0;
                isItemViewHandlingMotionEvent = false;
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                mState = STATE_NOTHING;
                mItemLeftDistance = 0;
                isItemViewHandlingMotionEvent = false;
                break;
            default:
                break;

        }
        return super.onTouchEvent(ev);
    }

    /**
     *
     *
     * @param position
     * @param
     * @return
     */
    private int scrollBack(int position, float x) {

        if (mWrapperAdapter.getSlideItemPosition() == position) {
            int scrollBackSituation = mWrapperAdapter.returnSlideItemPosition(x);
            switch (scrollBackSituation) {
                case ItemMainLayout.SCROLL_BACK_CLICK_OWN:
                    return RETURN_SCROLL_BACK_OWN;
                case ItemMainLayout.SCROLL_BACK_ALREADY_CLOSED:
                    return RETURN_SCROLL_BACK_NOTHING;
                case ItemMainLayout.SCROLL_BACK_CLICK_MENU_BUTTON:
                    return RETURN_SCROLL_BACK_CLICK_MENU_BUTTON;
            }
        } else if (mWrapperAdapter.getSlideItemPosition() != -1) {
            mWrapperAdapter.returnSlideItemPosition();
            return RETURN_SCROLL_BACK_OTHER;
        }
        return RETURN_SCROLL_BACK_NOTHING;
    }

    /**
     *      *
     * @param position
     * @return true---
     */
    private boolean scrollBackByDrag(int position) {
        //是不是当前滑开的这个
        if (mWrapperAdapter.getSlideItemPosition() == position) {
            return false;
        } else if (mWrapperAdapter.getSlideItemPosition() != -1) {
            mWrapperAdapter.returnSlideItemPosition();
            return true;
        }
        return true;
    }

    /**
     *
     *
     * @param ev
     * @return
     */
    private boolean fingerLeftAndRightMove(MotionEvent ev) {
        return ((ev.getX() - mXDown > mShortestDistance || ev.getX() - mXDown < -mShortestDistance) &&
                ev.getY() - mYDown < mShortestDistance && ev.getY() - mYDown > -mShortestDistance);
    }

    /**
     *
     *
     * @return
     */
    private boolean isFingerMoving2Right(MotionEvent ev) {
        return (ev.getX() - mXDown > mShortestDistance);
    }

    /**
     *
     *
     * @return
     */
    private boolean isFingerMoving2Left(MotionEvent ev) {
        return (ev.getX() - mXDown < -mShortestDistance);
    }

    /**
     *
     *
     * @param x
     * @param y
     * @return
     */
    private ItemMainLayout getItemMainLayoutByPosition(int x, int y) {
        int position = pointToPosition(x, y);
        if (position != AdapterView.INVALID_POSITION) {
            View view = getChildAt(position - getFirstVisiblePosition());
            if (view instanceof ItemMainLayout) {
                ItemMainLayout itemMainLayout = (ItemMainLayout) view;
                return itemMainLayout;
            }
        }
        return null;
    }

    /**
     *
     *
     * @param menu
     */
    public void setMenu(Menu menu) {
        if (mMenuSparseArray != null) {
            mMenuSparseArray.clear();
        } else {
            mMenuSparseArray = new SparseArray<>();
        }
        mMenuSparseArray.put(menu.getMenuViewType(), menu);
    }

    /**
     *
     *
     * @param list
     */
    public void setMenu(List<Menu> list) {
        if (mMenuSparseArray != null) {
            mMenuSparseArray.clear();
        } else {
            mMenuSparseArray = new SparseArray<>();
        }
        for (Menu menu : list) {
            mMenuSparseArray.put(menu.getMenuViewType(), menu);
        }
    }

    /**
     *
     *
     * @param menus
     */
    public void setMenu(Menu... menus) {
        if (mMenuSparseArray != null) {
            mMenuSparseArray.clear();
        } else {
            mMenuSparseArray = new SparseArray<>();
        }
        for (Menu menu : menus) {
            mMenuSparseArray.put(menu.getMenuViewType(), menu);
        }
    }

    /**
     *
     */
    public void closeSlidedItem() {
        if (mWrapperAdapter == null) {
            return;
        }
        mWrapperAdapter.returnSlideItemPosition();
    }

    public void deleteSlideItem() {
        if (mWrapperAdapter == null) {
            return;
        }
        mWrapperAdapter.deleteSlideItemPosition();
    }

    @Override
    public void setAdapter(final ListAdapter adapter) {
        if (mMenuSparseArray == null || mMenuSparseArray.size() == 0) {
            throw new IllegalArgumentException("先设置Menu");
        }
        mWrapperAdapter = new WrapperAdapter(getContext(), this, adapter, mMenuSparseArray);
        mWrapperAdapter.setOnAdapterSlideListenerProxy(this);
        mWrapperAdapter.setOnAdapterMenuClickListenerProxy(this);
        mWrapperAdapter.setOnItemDeleteListenerProxy(this);
        mWrapperAdapter.setOnScrollListenerProxy(this);
        mWrapperAdapter.setOnItemScrollBackListenerProxy(this);
        super.setAdapter(mWrapperAdapter);
    }

    protected WrapperAdapter getWrapperAdapter() {
        return mWrapperAdapter;
    }

    @Override
    public void onScrollStateChangedProxy(AbsListView view, int scrollState) {
        if (scrollState == WrapperAdapter.SCROLL_STATE_IDLE) {
            mIsWannaTriggerClick = true;
            mIsScrolling = false;
        } else {
            mIsWannaTriggerClick = false;
            mIsScrolling = true;
        }
        if (mOnListScrollListener != null) {
            mOnListScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScrollProxy(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mOnListScrollListener != null) {
            mOnListScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    @Override
    public void onDeleteBegin() {
        mIsDeleteAnimationRunning = true;
    }

    @Override
    public void onItemDelete(View view, int position) {
        mIsDeleteAnimationRunning = false;
        if (mOnItemDeleteListener != null && view instanceof ItemMainLayout) {
            ItemMainLayout itemMainLayout = (ItemMainLayout) view;
            mOnItemDeleteListener.onItemDeleteAnimationFinished(itemMainLayout.getItemCustomView(), position);
        }
    }

    /**
     *
     *
     * @param position
     * @return
     */
    public boolean startDrag(int position) {
        boolean canDrag = scrollBackByDrag(position);

        View view = getChildAt(position - getFirstVisiblePosition());
        if (canDrag && view instanceof ItemMainLayout) {
            setDragPosition(position);
        }
        return canDrag && view instanceof ItemMainLayout;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mWrapperAdapter != null) {
            mWrapperAdapter.removeDataSetObserver();
        }
    }

    /**
     *
     *
     * @param listener
     */
    public void setOnSlideListener(SlideAndDragListView.OnSlideListener listener) {
        mOnSlideListener = listener;
    }


    @Override
    public void onSlideOpen(View view, int position, int direction) {
        if (mOnSlideListener != null && view instanceof ItemMainLayout) {
            ItemMainLayout itemMainLayout = (ItemMainLayout) view;
            mOnSlideListener.onSlideOpen(itemMainLayout.getItemCustomView(), this, position, direction);
        }
    }

    @Override
    public void onSlideClose(View view, int position, int direction) {
        if (mOnSlideListener != null && view instanceof ItemMainLayout) {
            ItemMainLayout itemMainLayout = (ItemMainLayout) view;
            mOnSlideListener.onSlideClose(itemMainLayout.getItemCustomView(), this, position, direction);
        }
    }

    /**
     *
     *
     * @param onMenuItemClickListener
     */
    public void setOnMenuItemClickListener(SlideAndDragListView.OnMenuItemClickListener onMenuItemClickListener) {
        mOnMenuItemClickListener = onMenuItemClickListener;
    }

    @Override
    public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
        if (mOnMenuItemClickListener != null) {
            return mOnMenuItemClickListener.onMenuItemClick(v, itemPosition, buttonPosition, direction);
        }
        return Menu.ITEM_NOTHING;
    }

    @Override
    public void setOnItemClickListener(final OnItemClickListener listener) {
        if (listener == null) {
            mOnListItemClickListener = null;
            return;
        }
        mOnListItemClickListener = new Callback.OnItemClickListenerWrapper() {
            @Override
            public void onListItemClick(View v, int position) {
                listener.onItemClick(SlideListView.this, v, position, SlideListView.this.getItemIdAtPosition(position));
            }
        };
    }

    @Override
    public void setOnItemLongClickListener(final OnItemLongClickListener listener) {
        if (listener == null) {
            mOnListItemLongClickListener = null;
            return;
        }
        mOnListItemLongClickListener = new Callback.OnItemLongClickListenerWrapper() {
            @Override
            public void onListItemLongClick(View view, int position) {
                listener.onItemLongClick(SlideListView.this, view, position, SlideListView.this.getItemIdAtPosition(position));
            }
        };
    }

    protected void setOnItemDeleteListener(SlideAndDragListView.OnItemDeleteListener onItemDeleteListener) {
        mOnItemDeleteListener = onItemDeleteListener;
    }

    public void setOnItemScrollBackListener(SlideAndDragListView.OnItemScrollBackListener onItemScrollBackListener) {
        mOnItemScrollBackListener = onItemScrollBackListener;
    }


    @Override
    public void setOnScrollListener(final OnScrollListener l) {
        if (l == null) {
            mOnListScrollListener = null;
            return;
        }
        mOnListScrollListener = new Callback.OnScrollListenerWrapper() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                l.onScrollStateChanged(view, scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                l.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        };
    }

    /**
     *
     * @param l
     */
    protected void setOnSuperScrollListener(OnScrollListener l) {
        super.setOnScrollListener(l);
    }

    @Override
    public void onScrollBack(View view, int position) {
        if (mOnItemScrollBackListener != null) {
            mOnItemScrollBackListener.onScrollBackAnimationFinished(view, position);
        }
    }
}
