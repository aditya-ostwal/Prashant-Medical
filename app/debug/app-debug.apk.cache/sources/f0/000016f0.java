package com.shoeARstore;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.PagerAdapter;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;
import slider.library.Animations.BaseAnimationInterface;
import slider.library.Indicators.PagerIndicator;
import slider.library.SliderAdapter;
import slider.library.SliderTypes.BaseSliderView;
import slider.library.Transformers.AccordionTransformer;
import slider.library.Transformers.BackgroundToForegroundTransformer;
import slider.library.Transformers.BaseTransformer;
import slider.library.Transformers.CubeInTransformer;
import slider.library.Transformers.DefaultTransformer;
import slider.library.Transformers.DepthPageTransformer;
import slider.library.Transformers.FadeTransformer;
import slider.library.Transformers.FlipHorizontalTransformer;
import slider.library.Transformers.FlipPageViewTransformer;
import slider.library.Transformers.ForegroundToBackgroundTransformer;
import slider.library.Transformers.RotateDownTransformer;
import slider.library.Transformers.RotateUpTransformer;
import slider.library.Transformers.StackTransformer;
import slider.library.Transformers.TabletTransformer;
import slider.library.Transformers.ZoomInTransformer;
import slider.library.Transformers.ZoomOutSlideTransformer;
import slider.library.Transformers.ZoomOutTransformer;
import slider.library.Tricks.FixedSpeedScroller;
import slider.library.Tricks.InfinitePagerAdapter;
import slider.library.Tricks.InfiniteViewPager;
import slider.library.Tricks.ViewPagerEx;

/* loaded from: classes9.dex */
public class SliderLayout extends RelativeLayout {
    private boolean mAutoCycle;
    private boolean mAutoRecover;
    private Context mContext;
    private BaseAnimationInterface mCustomAnimation;
    private TimerTask mCycleTask;
    private Timer mCycleTimer;
    private boolean mCycling;
    private PagerIndicator mIndicator;
    private PagerIndicator.IndicatorVisibility mIndicatorVisibility;
    private TimerTask mResumingTask;
    private Timer mResumingTimer;
    private SliderAdapter mSliderAdapter;
    private long mSliderDuration;
    private int mTransformerId;
    private int mTransformerSpan;
    private InfiniteViewPager mViewPager;
    private BaseTransformer mViewPagerTransformer;
    private Handler mh;

    public SliderLayout(Context context) {
        this(context, null);
    }

    public SliderLayout(Context context, AttributeSet attrs) {
        this(context, attrs, com.mtel.shoe.R.attr.SliderStyle);
    }

    public SliderLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mAutoRecover = true;
        this.mTransformerSpan = 1200;
        this.mSliderDuration = 4000L;
        this.mIndicatorVisibility = PagerIndicator.IndicatorVisibility.Invisible;
        this.mh = new Handler() { // from class: com.shoeARstore.SliderLayout.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                SliderLayout.this.moveNextPosition(true);
            }
        };
        this.mContext = context;
        LayoutInflater.from(context).inflate(com.mtel.shoe.R.layout.slider_layout, (ViewGroup) this, true);
        int i = 0;
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SliderLayout, defStyle, 0);
        this.mTransformerSpan = attributes.getInteger(3, 1100);
        this.mTransformerId = attributes.getInt(2, Transformer.Default.ordinal());
        this.mAutoCycle = attributes.getBoolean(0, true);
        int visibility = attributes.getInt(1, 0);
        PagerIndicator.IndicatorVisibility[] values = PagerIndicator.IndicatorVisibility.values();
        int length = values.length;
        while (true) {
            if (i >= length) {
                break;
            }
            PagerIndicator.IndicatorVisibility v = values[i];
            if (v.ordinal() != visibility) {
                i++;
            } else {
                this.mIndicatorVisibility = v;
                break;
            }
        }
        this.mSliderAdapter = new SliderAdapter(this.mContext);
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(this.mSliderAdapter);
        InfiniteViewPager infiniteViewPager = (InfiniteViewPager) findViewById(com.mtel.shoe.R.id.daimajia_slider_viewpager);
        this.mViewPager = infiniteViewPager;
        infiniteViewPager.setAdapter(wrappedAdapter);
        this.mViewPager.setOnTouchListener(new View.OnTouchListener() { // from class: com.shoeARstore.SliderLayout.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v2, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case 1:
                        SliderLayout.this.recoverCycle();
                        return false;
                    default:
                        return false;
                }
            }
        });
        attributes.recycle();
        setPresetIndicator(PresetIndicators.Center_Bottom);
        setPresetTransformer(this.mTransformerId);
        setSliderTransformDuration(this.mTransformerSpan, null);
        setIndicatorVisibility(this.mIndicatorVisibility);
        if (this.mAutoCycle) {
            startAutoCycle();
        }
    }

    public void addOnPageChangeListener(ViewPagerEx.OnPageChangeListener onPageChangeListener) {
        if (onPageChangeListener != null) {
            this.mViewPager.addOnPageChangeListener(onPageChangeListener);
        }
    }

    public void removeOnPageChangeListener(ViewPagerEx.OnPageChangeListener onPageChangeListener) {
        this.mViewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    public void setCustomIndicator(PagerIndicator indicator) {
        PagerIndicator pagerIndicator = this.mIndicator;
        if (pagerIndicator != null) {
            pagerIndicator.destroySelf();
        }
        this.mIndicator = indicator;
        indicator.setIndicatorVisibility(this.mIndicatorVisibility);
        this.mIndicator.setViewPager(this.mViewPager);
        this.mIndicator.redraw();
    }

    public <T extends BaseSliderView> void addSlider(T imageContent) {
        this.mSliderAdapter.addSlider(imageContent);
    }

    public void startAutoCycle() {
        long j = this.mSliderDuration;
        startAutoCycle(j, j, this.mAutoRecover);
    }

    public void startAutoCycle(long delay, long duration, boolean autoRecover) {
        Timer timer = this.mCycleTimer;
        if (timer != null) {
            timer.cancel();
        }
        TimerTask timerTask = this.mCycleTask;
        if (timerTask != null) {
            timerTask.cancel();
        }
        TimerTask timerTask2 = this.mResumingTask;
        if (timerTask2 != null) {
            timerTask2.cancel();
        }
        Timer timer2 = this.mResumingTimer;
        if (timer2 != null) {
            timer2.cancel();
        }
        this.mSliderDuration = duration;
        this.mCycleTimer = new Timer();
        this.mAutoRecover = autoRecover;
        TimerTask timerTask3 = new TimerTask() { // from class: com.shoeARstore.SliderLayout.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                SliderLayout.this.mh.sendEmptyMessage(0);
            }
        };
        this.mCycleTask = timerTask3;
        this.mCycleTimer.schedule(timerTask3, delay, this.mSliderDuration);
        this.mCycling = true;
        this.mAutoCycle = true;
    }

    private void pauseAutoCycle() {
        if (this.mCycling) {
            this.mCycleTimer.cancel();
            this.mCycleTask.cancel();
            this.mCycling = false;
        } else if (this.mResumingTimer != null && this.mResumingTask != null) {
            recoverCycle();
        }
    }

    public void setDuration(long duration) {
        if (duration >= 500) {
            this.mSliderDuration = duration;
            if (this.mAutoCycle && this.mCycling) {
                startAutoCycle();
            }
        }
    }

    public void stopAutoCycle() {
        TimerTask timerTask = this.mCycleTask;
        if (timerTask != null) {
            timerTask.cancel();
        }
        Timer timer = this.mCycleTimer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = this.mResumingTimer;
        if (timer2 != null) {
            timer2.cancel();
        }
        TimerTask timerTask2 = this.mResumingTask;
        if (timerTask2 != null) {
            timerTask2.cancel();
        }
        this.mAutoCycle = false;
        this.mCycling = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recoverCycle() {
        Timer timer;
        if (this.mAutoRecover && this.mAutoCycle && !this.mCycling) {
            if (this.mResumingTask != null && (timer = this.mResumingTimer) != null) {
                timer.cancel();
                this.mResumingTask.cancel();
            }
            this.mResumingTimer = new Timer();
            TimerTask timerTask = new TimerTask() { // from class: com.shoeARstore.SliderLayout.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    SliderLayout.this.startAutoCycle();
                }
            };
            this.mResumingTask = timerTask;
            this.mResumingTimer.schedule(timerTask, 6000L);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case 0:
                pauseAutoCycle();
                return false;
            default:
                return false;
        }
    }

    public void setPagerTransformer(boolean reverseDrawingOrder, BaseTransformer transformer) {
        this.mViewPagerTransformer = transformer;
        transformer.setCustomAnimationInterface(this.mCustomAnimation);
        this.mViewPager.setPageTransformer(reverseDrawingOrder, this.mViewPagerTransformer);
    }

    public void setSliderTransformDuration(int period, Interpolator interpolator) {
        try {
            Field mScroller = ViewPagerEx.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(this.mViewPager.getContext(), interpolator, period);
            mScroller.set(this.mViewPager, scroller);
        } catch (Exception e) {
        }
    }

    /* loaded from: classes9.dex */
    public enum Transformer {
        Default("Default"),
        Accordion("Accordion"),
        Background2Foreground("Background2Foreground"),
        CubeIn("CubeIn"),
        DepthPage("DepthPage"),
        Fade("Fade"),
        FlipHorizontal("FlipHorizontal"),
        FlipPage("FlipPage"),
        Foreground2Background("Foreground2Background"),
        RotateDown("RotateDown"),
        RotateUp("RotateUp"),
        Stack("Stack"),
        Tablet("Tablet"),
        ZoomIn("ZoomIn"),
        ZoomOutSlide("ZoomOutSlide"),
        ZoomOut("ZoomOut");
        
        private final String name;

        Transformer(String s) {
            this.name = s;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }

        public boolean equals(String other) {
            if (other == null) {
                return false;
            }
            return this.name.equals(other);
        }
    }

    public void setPresetTransformer(int transformerId) {
        Transformer[] values;
        for (Transformer t : Transformer.values()) {
            if (t.ordinal() == transformerId) {
                setPresetTransformer(t);
                return;
            }
        }
    }

    public void setPresetTransformer(String transformerName) {
        Transformer[] values;
        for (Transformer t : Transformer.values()) {
            if (t.equals(transformerName)) {
                setPresetTransformer(t);
                return;
            }
        }
    }

    public void setCustomAnimation(BaseAnimationInterface animation) {
        this.mCustomAnimation = animation;
        BaseTransformer baseTransformer = this.mViewPagerTransformer;
        if (baseTransformer != null) {
            baseTransformer.setCustomAnimationInterface(animation);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.shoeARstore.SliderLayout$5  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$shoeARstore$SliderLayout$Transformer;

        static {
            int[] iArr = new int[Transformer.values().length];
            $SwitchMap$com$shoeARstore$SliderLayout$Transformer = iArr;
            try {
                iArr[Transformer.Default.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.Accordion.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.Background2Foreground.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.CubeIn.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.DepthPage.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.Fade.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.FlipHorizontal.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.FlipPage.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.Foreground2Background.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.RotateDown.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.RotateUp.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.Stack.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.Tablet.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.ZoomIn.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.ZoomOutSlide.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$shoeARstore$SliderLayout$Transformer[Transformer.ZoomOut.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
        }
    }

    public void setPresetTransformer(Transformer ts) {
        BaseTransformer t = null;
        switch (AnonymousClass5.$SwitchMap$com$shoeARstore$SliderLayout$Transformer[ts.ordinal()]) {
            case 1:
                t = new DefaultTransformer();
                break;
            case 2:
                t = new AccordionTransformer();
                break;
            case 3:
                t = new BackgroundToForegroundTransformer();
                break;
            case 4:
                t = new CubeInTransformer();
                break;
            case 5:
                t = new DepthPageTransformer();
                break;
            case 6:
                t = new FadeTransformer();
                break;
            case 7:
                t = new FlipHorizontalTransformer();
                break;
            case 8:
                t = new FlipPageViewTransformer();
                break;
            case 9:
                t = new ForegroundToBackgroundTransformer();
                break;
            case 10:
                t = new RotateDownTransformer();
                break;
            case 11:
                t = new RotateUpTransformer();
                break;
            case 12:
                t = new StackTransformer();
                break;
            case 13:
                t = new TabletTransformer();
                break;
            case 14:
                t = new ZoomInTransformer();
                break;
            case 15:
                t = new ZoomOutSlideTransformer();
                break;
            case 16:
                t = new ZoomOutTransformer();
                break;
        }
        setPagerTransformer(true, t);
    }

    public void setIndicatorVisibility(PagerIndicator.IndicatorVisibility visibility) {
        PagerIndicator pagerIndicator = this.mIndicator;
        if (pagerIndicator == null) {
            return;
        }
        pagerIndicator.setIndicatorVisibility(visibility);
    }

    public PagerIndicator.IndicatorVisibility getIndicatorVisibility() {
        PagerIndicator pagerIndicator = this.mIndicator;
        if (pagerIndicator == null) {
            return pagerIndicator.getIndicatorVisibility();
        }
        return PagerIndicator.IndicatorVisibility.Invisible;
    }

    public PagerIndicator getPagerIndicator() {
        return this.mIndicator;
    }

    /* loaded from: classes9.dex */
    public enum PresetIndicators {
        Center_Bottom("Center_Bottom", com.mtel.shoe.R.id.default_center_bottom_indicator),
        Right_Bottom("Right_Bottom", com.mtel.shoe.R.id.default_bottom_right_indicator),
        Left_Bottom("Left_Bottom", com.mtel.shoe.R.id.default_bottom_left_indicator),
        Center_Top("Center_Top", com.mtel.shoe.R.id.default_center_top_indicator),
        Right_Top("Right_Top", com.mtel.shoe.R.id.default_center_top_right_indicator),
        Left_Top("Left_Top", com.mtel.shoe.R.id.default_center_top_left_indicator);
        
        private final int id;
        private final String name;

        PresetIndicators(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }

        public int getResourceId() {
            return this.id;
        }
    }

    public void setPresetIndicator(PresetIndicators presetIndicator) {
        PagerIndicator pagerIndicator = (PagerIndicator) findViewById(presetIndicator.getResourceId());
        setCustomIndicator(pagerIndicator);
    }

    private InfinitePagerAdapter getWrapperAdapter() {
        PagerAdapter adapter = this.mViewPager.getAdapter();
        if (adapter != null) {
            return (InfinitePagerAdapter) adapter;
        }
        return null;
    }

    private SliderAdapter getRealAdapter() {
        PagerAdapter adapter = this.mViewPager.getAdapter();
        if (adapter != null) {
            return ((InfinitePagerAdapter) adapter).getRealAdapter();
        }
        return null;
    }

    public int getCurrentPosition() {
        if (getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        return this.mViewPager.getCurrentItem() % getRealAdapter().getCount();
    }

    public BaseSliderView getCurrentSlider() {
        if (getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        int count = getRealAdapter().getCount();
        int realCount = this.mViewPager.getCurrentItem() % count;
        return getRealAdapter().getSliderView(realCount);
    }

    public void removeSliderAt(int position) {
        if (getRealAdapter() != null) {
            getRealAdapter().removeSliderAt(position);
            InfiniteViewPager infiniteViewPager = this.mViewPager;
            infiniteViewPager.setCurrentItem(infiniteViewPager.getCurrentItem(), false);
        }
    }

    public void removeAllSliders() {
        if (getRealAdapter() != null) {
            int count = getRealAdapter().getCount();
            getRealAdapter().removeAllSliders();
            InfiniteViewPager infiniteViewPager = this.mViewPager;
            infiniteViewPager.setCurrentItem(infiniteViewPager.getCurrentItem() + count, false);
        }
    }

    public void setCurrentPosition(int position, boolean smooth) {
        if (getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        if (position >= getRealAdapter().getCount()) {
            throw new IllegalStateException("Item position is not exist");
        }
        int p = this.mViewPager.getCurrentItem() % getRealAdapter().getCount();
        int n = (position - p) + this.mViewPager.getCurrentItem();
        this.mViewPager.setCurrentItem(n, smooth);
    }

    public void setCurrentPosition(int position) {
        setCurrentPosition(position, true);
    }

    public void movePrevPosition(boolean smooth) {
        if (getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        InfiniteViewPager infiniteViewPager = this.mViewPager;
        infiniteViewPager.setCurrentItem(infiniteViewPager.getCurrentItem() - 1, smooth);
    }

    public void movePrevPosition() {
        movePrevPosition(true);
    }

    public void moveNextPosition(boolean smooth) {
        if (getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        InfiniteViewPager infiniteViewPager = this.mViewPager;
        infiniteViewPager.setCurrentItem(infiniteViewPager.getCurrentItem() + 1, smooth);
    }

    public void moveNextPosition() {
        moveNextPosition(true);
    }
}