package slider.library.Tricks;

import android.content.Context;
import android.util.AttributeSet;
import androidx.viewpager.widget.PagerAdapter;

/* loaded from: classes6.dex */
public class InfiniteViewPager extends ViewPagerEx {
    public InfiniteViewPager(Context context) {
        super(context);
    }

    public InfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // slider.library.Tricks.ViewPagerEx
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
    }
}