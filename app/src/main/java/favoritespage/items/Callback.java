package favoritespage.items;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created.
 */
interface Callback {

    interface OnDragDropListener {

        boolean onDragStarted(int x, int y, View view);

        void onDragMoving(int x, int y, View view, SlideAndDragListView.OnDragDropListener listener);

        void onDragFinished(int x, int y, SlideAndDragListView.OnDragDropListener listener);
    }

    interface OnScrollListenerWrapper {

        void onScrollStateChanged(AbsListView view, int scrollState);

        void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
    }

    interface OnItemLongClickListenerWrapper {
        void onListItemLongClick(View view, int position);
    }

    interface OnItemClickListenerWrapper {
        void onListItemClick(View v, int position);
    }
}
