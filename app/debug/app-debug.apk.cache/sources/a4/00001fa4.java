package slider.library.Animations;

import android.view.View;

/* loaded from: classes5.dex */
public interface BaseAnimationInterface {
    void onCurrentItemDisappear(View view);

    void onNextItemAppear(View view);

    void onPrepareCurrentItemLeaveScreen(View view);

    void onPrepareNextItemShowInScreen(View view);
}