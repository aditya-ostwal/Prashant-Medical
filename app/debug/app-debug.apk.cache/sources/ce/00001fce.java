package slider.library.Tricks;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* loaded from: classes6.dex */
public class FixedSpeedScroller extends Scroller {
    private int mDuration;

    public FixedSpeedScroller(Context context) {
        super(context);
        this.mDuration = 1000;
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
        this.mDuration = 1000;
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator, int period) {
        this(context, interpolator);
        this.mDuration = period;
    }

    @Override // android.widget.Scroller
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, this.mDuration);
    }

    @Override // android.widget.Scroller
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, this.mDuration);
    }
}