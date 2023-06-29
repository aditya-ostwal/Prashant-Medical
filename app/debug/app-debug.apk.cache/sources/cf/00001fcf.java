package slider.library.Tricks;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import slider.library.SliderAdapter;

/* loaded from: classes6.dex */
public class InfinitePagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "InfinitePagerAdapter";
    private SliderAdapter adapter;

    public InfinitePagerAdapter(SliderAdapter adapter) {
        this.adapter = adapter;
    }

    public SliderAdapter getRealAdapter() {
        return this.adapter;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getRealCount() {
        return this.adapter.getCount();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup container, int position) {
        if (getRealCount() == 0) {
            return null;
        }
        int virtualPosition = position % getRealCount();
        debug("instantiateItem: real position: " + position);
        debug("instantiateItem: virtual position: " + virtualPosition);
        return this.adapter.instantiateItem(container, virtualPosition);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (getRealCount() == 0) {
            return;
        }
        int virtualPosition = position % getRealCount();
        debug("destroyItem: real position: " + position);
        debug("destroyItem: virtual position: " + virtualPosition);
        this.adapter.destroyItem(container, virtualPosition, object);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(ViewGroup container) {
        this.adapter.finishUpdate(container);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object object) {
        return this.adapter.isViewFromObject(view, object);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        this.adapter.restoreState(bundle, classLoader);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        return this.adapter.saveState();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(ViewGroup container) {
        this.adapter.startUpdate(container);
    }

    private void debug(String message) {
    }
}