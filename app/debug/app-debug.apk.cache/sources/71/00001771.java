package favoritespage.items;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public final class Menu {
    public static final int ITEM_DELETE_FROM_BOTTOM_TO_TOP = 2;
    public static final int ITEM_NOTHING = 0;
    public static final int ITEM_SCROLL_BACK = 1;
    private boolean isSlideOver;
    private List<MenuItem> mLeftMenuItems;
    private int mMenuViewType;
    private List<MenuItem> mRightMenuItems;

    public Menu(boolean slideOver) {
        this(slideOver, 0);
    }

    public Menu(boolean slideOver, int menuViewType) {
        this.isSlideOver = true;
        this.mMenuViewType = 0;
        this.isSlideOver = slideOver;
        this.mLeftMenuItems = new ArrayList();
        this.mRightMenuItems = new ArrayList();
        this.mMenuViewType = menuViewType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSlideOver() {
        return this.isSlideOver;
    }

    public void addItem(MenuItem menuItem) {
        if (menuItem.direction == 1) {
            this.mLeftMenuItems.add(menuItem);
        } else {
            this.mRightMenuItems.add(menuItem);
        }
    }

    public void addItem(MenuItem menuItem, int position) {
        if (menuItem.direction == 1) {
            this.mLeftMenuItems.add(position, menuItem);
        } else {
            this.mRightMenuItems.add(position, menuItem);
        }
    }

    public boolean removeItem(MenuItem menuItem) {
        if (menuItem.direction == 1) {
            return this.mLeftMenuItems.remove(menuItem);
        }
        return this.mRightMenuItems.remove(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getTotalBtnLength(int direction) {
        int total = 0;
        if (direction == 1) {
            for (MenuItem menuItem : this.mLeftMenuItems) {
                total += menuItem.width;
            }
            return total;
        }
        for (MenuItem menuItem2 : this.mRightMenuItems) {
            total += menuItem2.width;
        }
        return total;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<MenuItem> getMenuItems(int direction) {
        if (direction == 1) {
            return this.mLeftMenuItems;
        }
        return this.mRightMenuItems;
    }

    public int getMenuViewType() {
        return this.mMenuViewType;
    }
}