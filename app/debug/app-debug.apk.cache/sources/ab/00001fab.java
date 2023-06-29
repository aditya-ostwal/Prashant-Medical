package slider.library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import slider.library.SliderTypes.BaseSliderView;

/* loaded from: classes9.dex */
public class SliderAdapter extends PagerAdapter implements BaseSliderView.ImageLoadListener {
    private Context mContext;
    private ArrayList<BaseSliderView> mImageContents = new ArrayList<>();

    public SliderAdapter(Context context) {
        this.mContext = context;
    }

    public <T extends BaseSliderView> void addSlider(T slider2) {
        slider2.setOnImageLoadListener(this);
        this.mImageContents.add(slider2);
        notifyDataSetChanged();
    }

    public BaseSliderView getSliderView(int position) {
        if (position < 0 || position >= this.mImageContents.size()) {
            return null;
        }
        return this.mImageContents.get(position);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object object) {
        return -2;
    }

    public <T extends BaseSliderView> void removeSlider(T slider2) {
        if (this.mImageContents.contains(slider2)) {
            this.mImageContents.remove(slider2);
            notifyDataSetChanged();
        }
    }

    public void removeSliderAt(int position) {
        if (this.mImageContents.size() > position) {
            this.mImageContents.remove(position);
            notifyDataSetChanged();
        }
    }

    public void removeAllSliders() {
        this.mImageContents.clear();
        notifyDataSetChanged();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mImageContents.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup container, int position) {
        BaseSliderView b = this.mImageContents.get(position);
        View v = b.getView();
        container.addView(v);
        return v;
    }

    @Override // slider.library.SliderTypes.BaseSliderView.ImageLoadListener
    public void onStart(BaseSliderView target) {
    }

    @Override // slider.library.SliderTypes.BaseSliderView.ImageLoadListener
    public void onEnd(boolean result, BaseSliderView target) {
        if (!target.isErrorDisappear() || result) {
            return;
        }
        Iterator<BaseSliderView> it = this.mImageContents.iterator();
        while (it.hasNext()) {
            BaseSliderView slider2 = it.next();
            if (slider2.equals(target)) {
                removeSlider(target);
                return;
            }
        }
    }
}