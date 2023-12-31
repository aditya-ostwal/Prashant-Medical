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

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created.
 */
class WrapperAdapter implements WrapperListAdapter, ItemMainLayout.OnItemSlideListenerProxy, View.OnClickListener,
        AbsListView.OnScrollListener, ItemMainLayout.OnItemDeleteListenerProxy, Callback.OnDragDropListener,
        ItemMainLayout.OnItemScrollBackListenerProxy, ItemBackGroundLayout.OnMenuItemClickListener {
    private final static int TAG_LEFT = 3 << 24;
    private final static int TAG_RIGHT = 4 << 24;
    private Context mContext;
    private ListAdapter mAdapter;
    private SparseArray<Menu> mMenuSparseArray;
    private SlideListView mListView;
    private int mSlideItemPosition = -1;
    private boolean isInDragging = false;
    private Object mDraggedEntity = null;
    private int mDragEnteredEntityIndex = -1;
    private HashMap<Integer, Integer> mItemIdTopMap;
    private int mStartLimit = -1;
    private int mEndLimit = Integer.MAX_VALUE;
    private int mAnimationDuration = 300;

    private OnAdapterSlideListenerProxy mOnAdapterSlideListenerProxy;
    private OnAdapterMenuClickListenerProxy mOnAdapterMenuClickListenerProxy;
    private onItemDeleteListenerProxy mOnItemDeleteListenerProxy;
    private OnScrollListenerProxy mOnScrollListenerProxy;
    private OnItemScrollBackListenerProxy mOnItemScrollBackListenerProxy;

    protected WrapperAdapter(Context context, SlideListView listView, ListAdapter adapter, SparseArray<Menu> sparseArray) {
        mContext = context;
        mListView = listView;
        mListView.setOnSuperScrollListener(this);
        mAdapter = adapter;
        mMenuSparseArray = sparseArray;
        mAdapter.registerDataSetObserver(mDataSetObserver);
        mListView.serAdapterDragDropListener(this);
        mItemIdTopMap = new HashMap<>();
    }

    @Override
    public ListAdapter getWrappedAdapter() {
        return mAdapter;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return mAdapter.areAllItemsEnabled();
    }

    @Override
    public boolean isEnabled(int position) {
        return mAdapter.isEnabled(position);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mAdapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mAdapter.unregisterDataSetObserver(observer);
    }

    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            returnSlideItemPosition();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    };

    @Override
    public int getCount() {
        return mAdapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        return mAdapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }

    @Override
    public boolean hasStableIds() {
        return mAdapter.hasStableIds();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemMainLayout itemMainLayout = null;
        if (convertView == null) {
            View contentView = mAdapter.getView(position, convertView, parent);
            itemMainLayout = new ItemMainLayout(mContext, contentView);
            int type = mAdapter.getItemViewType(position);
            Menu menu = mMenuSparseArray.get(type);
            if (menu == null) {
                throw new IllegalArgumentException("No menu matches any view types in ListView");
            }
            itemMainLayout.setParams(menu.getTotalBtnLength(MenuItem.DIRECTION_LEFT),
                    menu.getTotalBtnLength(MenuItem.DIRECTION_RIGHT), menu.isSlideOver());
            createMenu(menu, itemMainLayout);
            itemMainLayout.setOnItemSlideListenerProxy(this);
            itemMainLayout.setSelector(mListView.getSelector());
        } else {
            itemMainLayout = (ItemMainLayout) convertView;
            mAdapter.getView(position, itemMainLayout.getItemCustomView(), parent);
        }
        hide(itemMainLayout, position);
        return itemMainLayout;
    }

    private void hide(View itemMainLayout, int position) {
        if (mDraggedEntity == null) {
            if (itemMainLayout != null && itemMainLayout.getVisibility() != View.VISIBLE) {
                itemMainLayout.setVisibility(View.VISIBLE);
            }
            return;
        }
        if (getItem(position) == mDraggedEntity & itemMainLayout != null) {
            itemMainLayout.setVisibility(View.INVISIBLE);
        } else if (itemMainLayout.getVisibility() != View.VISIBLE) {
            itemMainLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     *
     *
     * @param itemMainLayout
     */
    private void createMenu(Menu menu, ItemMainLayout itemMainLayout) {
        if (menu.getTotalBtnLength(MenuItem.DIRECTION_LEFT) > 0) {
            for (int i = 0; i < menu.getMenuItems(MenuItem.DIRECTION_LEFT).size(); i++) {
                ItemBackGroundLayout itemBackGroundLayout = itemMainLayout.getItemLeftBackGroundLayout();
                itemBackGroundLayout.addMenuItem(menu.getMenuItems(MenuItem.DIRECTION_LEFT).get(i), i);
                itemBackGroundLayout.setDirection(MenuItem.DIRECTION_LEFT);
                itemBackGroundLayout.setOnMenuItemClickListener(this);
            }
        } else {
            itemMainLayout.getItemLeftBackGroundLayout().setVisibility(View.GONE);
        }
        if (menu.getTotalBtnLength(MenuItem.DIRECTION_RIGHT) > 0) {
            for (int i = 0; i < menu.getMenuItems(MenuItem.DIRECTION_RIGHT).size(); i++) {
                ItemBackGroundLayout itemBackGroundLayout = itemMainLayout.getItemRightBackGroundLayout();
                itemBackGroundLayout.addMenuItem(menu.getMenuItems(MenuItem.DIRECTION_RIGHT).get(i), i);
                itemBackGroundLayout.setDirection(MenuItem.DIRECTION_RIGHT);
                itemBackGroundLayout.setOnMenuItemClickListener(this);
            }
        } else {
            itemMainLayout.getItemRightBackGroundLayout().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapter.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return mAdapter.getViewTypeCount();
    }

    @Override
    public boolean isEmpty() {
        return mAdapter.isEmpty();
    }

    /**
     *
     *
     * @param position
     */
    protected void setSlideItemPosition(int position) {
        if (mSlideItemPosition != -1 && mSlideItemPosition != position) {
            returnSlideItemPosition();
        }
        if (mSlideItemPosition == position) {
            return;
        }
        mSlideItemPosition = position;
    }

    /**
     *
     *
     * @return
     */
    protected int getSlideItemPosition() {
        return mSlideItemPosition;
    }

    /**
     *
     */
    protected void returnSlideItemPosition() {
        if (mSlideItemPosition != -1) {
            ItemMainLayout itemMainLayout = (ItemMainLayout) mListView.getChildAt(mSlideItemPosition - mListView.getFirstVisiblePosition());
            if (itemMainLayout != null) {
                itemMainLayout.scrollBack();
            }
            mSlideItemPosition = -1;
        }
    }

    private void returnSlideItemPosition(ItemMainLayout.OnItemScrollBackListenerProxy onItemScrollBackListenerProxy) {
        if (mSlideItemPosition != -1) {
            ItemMainLayout itemMainLayout = (ItemMainLayout) mListView.getChildAt(mSlideItemPosition - mListView.getFirstVisiblePosition());
            if (itemMainLayout != null) {
                itemMainLayout.scrollBack(onItemScrollBackListenerProxy);
            }
            mSlideItemPosition = -1;
        }
    }

    protected void deleteSlideItemPosition() {
        if (mSlideItemPosition != -1) {
            ItemMainLayout itemMainLayout = (ItemMainLayout) mListView.getChildAt(mSlideItemPosition - mListView.getFirstVisiblePosition());
            if (itemMainLayout != null) {
                itemMainLayout.deleteItem(this);
            }
        }
    }

    /**
     * @param x
     * @return
     */
    protected int returnSlideItemPosition(float x) {
        if (mSlideItemPosition != -1) {
            ItemMainLayout itemMainLayout = (ItemMainLayout) mListView.getChildAt(mSlideItemPosition - mListView.getFirstVisiblePosition());
            if (itemMainLayout != null) {
                int scrollBackSituation = itemMainLayout.scrollBack(x);
                switch (scrollBackSituation) {
                    case ItemMainLayout.SCROLL_BACK_ALREADY_CLOSED:
                    case ItemMainLayout.SCROLL_BACK_CLICK_OWN:
                        mSlideItemPosition = -1;
                        break;
                    case ItemMainLayout.SCROLL_BACK_CLICK_MENU_BUTTON:
                        break;
                }
                return scrollBackSituation;
            }
            mSlideItemPosition = -1;
            return ItemMainLayout.SCROLL_BACK_CLICK_NOTHING;
        }
        return ItemMainLayout.SCROLL_BACK_CLICK_NOTHING;
    }

    protected void removeDataSetObserver() {
        if (mAdapter != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }
    }

    /**
     *
     *
     * @param onAdapterSlideListenerProxy
     */
    protected void setOnAdapterSlideListenerProxy(OnAdapterSlideListenerProxy onAdapterSlideListenerProxy) {
        mOnAdapterSlideListenerProxy = onAdapterSlideListenerProxy;
    }

    @Override
    public void onSlideOpen(View view, int direction) {
        if (mOnAdapterSlideListenerProxy != null) {
            mOnAdapterSlideListenerProxy.onSlideOpen(view, mSlideItemPosition, direction);
        }
    }

    @Override
    public void onSlideClose(View view, int direction) {
        if (mOnAdapterSlideListenerProxy != null) {
            mOnAdapterSlideListenerProxy.onSlideClose(view, mSlideItemPosition, direction);
        }
    }

    /**
     *
     *
     * @param onAdapterMenuClickListenerProxy
     */
    protected void setOnAdapterMenuClickListenerProxy(OnAdapterMenuClickListenerProxy onAdapterMenuClickListenerProxy) {
        mOnAdapterMenuClickListenerProxy = onAdapterMenuClickListenerProxy;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(int position, int direction, View view) {
        if (mOnAdapterMenuClickListenerProxy != null) {
            int scroll = mOnAdapterMenuClickListenerProxy.onMenuItemClick(view, mSlideItemPosition, position, direction);
            switch (scroll) {
                case Menu.ITEM_NOTHING:
                    break;
                case Menu.ITEM_SCROLL_BACK:
                    //归位
                    returnSlideItemPosition(this);
                    break;
                case Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP:
                    deleteSlideItemPosition();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            returnSlideItemPosition();
        }
        if (mOnScrollListenerProxy != null) {
            mOnScrollListenerProxy.onScrollStateChangedProxy(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mOnScrollListenerProxy != null) {
            mOnScrollListenerProxy.onScrollProxy(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    @Override
    public void onDeleteBegin() {
        if (mOnItemDeleteListenerProxy != null) {
            mOnItemDeleteListenerProxy.onDeleteBegin();
        }
    }

    @Override
    public void onDelete(View view) {
        int position = mSlideItemPosition;
        if (mSlideItemPosition != -1) {
            if (mOnItemDeleteListenerProxy != null) {
                mOnItemDeleteListenerProxy.onItemDelete(view, position);
            }
            mSlideItemPosition = -1;
        }
    }

    protected void setStartLimit(int startLimit) {
        mStartLimit = startLimit;
    }

    protected void setEndLimit(int endLimit) {
        mEndLimit = endLimit;
    }

    private void setInDragging(boolean inDragging) {
        isInDragging = inDragging;
    }

    @Override
    public boolean onDragStarted(int x, int y, View view) {
        int itemIndex = mListView.getPositionForView(view) - mListView.getHeaderViewsCount();
        if (itemIndex > mStartLimit && itemIndex < mEndLimit) {
            setInDragging(true);
            popDragEntry(itemIndex);
        } else {
            setInDragging(false);
        }
        return isInDragging;
    }

    private void popDragEntry(int index) {
        if (isIndexInBound(index)) {
            mDraggedEntity = getItem(index);
            mDragEnteredEntityIndex = index;
            markDropArea(index, null);
        }
    }

    private void markDropArea(int itemIndex, SlideAndDragListView.OnDragDropListener listener) {
        if (mDraggedEntity != null && isIndexInBound(mDragEnteredEntityIndex) && isIndexInBound(itemIndex)) {
            cacheOffsetsForDataSetChanged();
            if (listener != null) {
                listener.onDragDropViewMoved(mDragEnteredEntityIndex, itemIndex);
            }
            mDragEnteredEntityIndex = itemIndex;
            doAnimation();
            notifyDataSetChanged();
        }
    }

    private void notifyDataSetChanged() {
        if (mAdapter != null && mAdapter instanceof BaseAdapter) {
            ((BaseAdapter) mAdapter).notifyDataSetChanged();
        }
    }

    private void cacheOffsetsForDataSetChanged() {
        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        for (int i = 0; i < mListView.getChildCount(); i++) {
            View child = mListView.getChildAt(i);
            int position = firstVisiblePosition + i;
            if (position < mListView.getHeaderViewsCount()) {
                continue;
            }
            if (!isIndexInBound(position - mListView.getHeaderViewsCount())) {
                continue;
            }
            if (getItem(position - mListView.getHeaderViewsCount()) == null) {
                throw new NullPointerException("The value of getItem(position) is NULL!");
            }
            int itemId = getItem(position - mListView.getHeaderViewsCount()).hashCode();
            mItemIdTopMap.put(itemId, child.getTop());
        }
    }

    private boolean isIndexInBound(int itemIndex) {
        return itemIndex >= 0 && itemIndex < getCount();
    }

    @Override
    public void onDragMoving(int x, int y, View view, SlideAndDragListView.OnDragDropListener listener) {
        if (view == null) {
            return;
        }
        int itemIndex = mListView.getPositionForView(view) - mListView.getHeaderViewsCount();
        if (isInDragging && mDragEnteredEntityIndex != itemIndex && isIndexInBound(itemIndex)
                && itemIndex > mStartLimit && itemIndex < mEndLimit) {
            markDropArea(itemIndex, listener);
        }
    }

    @Override
    public void onDragFinished(int x, int y, SlideAndDragListView.OnDragDropListener listener) {
        setInDragging(false);
        handleDrop(listener);
    }

    private void handleDrop(SlideAndDragListView.OnDragDropListener listener) {
        if (mDraggedEntity != null) {
            if (isIndexInBound(mDragEnteredEntityIndex)) {
                if (listener != null) {
                    listener.onDragViewDown(mDragEnteredEntityIndex);
                }
                cacheOffsetsForDataSetChanged();
                notifyDataSetChanged();
            }
            mDraggedEntity = null;
        }
    }

    private void doAnimation() {
        if (mItemIdTopMap.isEmpty()) {
            return;
        }

        mListView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mListView.getViewTreeObserver().removeOnPreDrawListener(this);
                int firstVisiblePosition = mListView.getFirstVisiblePosition();

                AnimatorSet animSet = new AnimatorSet();
                ArrayList<Animator> animators = new ArrayList<>();
                for (int i = 0; i < mListView.getChildCount(); i++) {
                    View child = mListView.getChildAt(i);
                    int position = firstVisiblePosition + i;
                    if (position < mListView.getHeaderViewsCount()) {
                        continue;
                    }
                    if (!isIndexInBound(position - mListView.getHeaderViewsCount())) {
                        continue;
                    }

                    int itemId = getItem(position - mListView.getHeaderViewsCount()).hashCode();

                    Integer startTop = mItemIdTopMap.get(itemId);
                    int top = child.getTop();
                    int deltaY = 0;

                    if (startTop != null) {
                        if (startTop != top) {
                            deltaY = startTop - top;
                            animators.add(ObjectAnimator.ofFloat(child, "translationY", deltaY, 0.0f));
                        }
                    }
                }
                if (animators.size() > 0) {
                    animSet.setDuration(mAnimationDuration).playTogether(animators);
                    animSet.start();
                }

                mItemIdTopMap.clear();
                return true;
            }
        });
    }

    @Override
    public void onScrollBack(View view) {
        if (mOnItemScrollBackListenerProxy != null) {
            mOnItemScrollBackListenerProxy.onScrollBack(view, mListView.getPositionForView(view));
        }
    }

    protected interface OnAdapterMenuClickListenerProxy {
        int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction);
    }

    protected interface OnAdapterSlideListenerProxy {
        void onSlideOpen(View view, int position, int direction);

        void onSlideClose(View view, int position, int direction);
    }

    protected void setOnScrollListenerProxy(OnScrollListenerProxy onScrollListenerProxy) {
        mOnScrollListenerProxy = onScrollListenerProxy;
    }

    protected interface OnScrollListenerProxy {
        void onScrollStateChangedProxy(AbsListView view, int scrollState);

        void onScrollProxy(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
    }

    protected void setOnItemDeleteListenerProxy(onItemDeleteListenerProxy onItemDeleteListenerProxy) {
        mOnItemDeleteListenerProxy = onItemDeleteListenerProxy;
    }

    protected interface onItemDeleteListenerProxy {
        void onDeleteBegin();

        void onItemDelete(View view, int position);
    }

    public void setOnItemScrollBackListenerProxy(OnItemScrollBackListenerProxy onItemScrollBackListenerProxy) {
        mOnItemScrollBackListenerProxy = onItemScrollBackListenerProxy;
    }

    protected interface OnItemScrollBackListenerProxy {
        void onScrollBack(View view, int position);
    }
}
