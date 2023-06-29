package slider.library.Animations;

import android.view.View;
import com.mtel.shoe.R;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes5.dex */
public class DescriptionAnimation implements BaseAnimationInterface {
    @Override // slider.library.Animations.BaseAnimationInterface
    public void onPrepareCurrentItemLeaveScreen(View current) {
        View descriptionLayout = current.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            current.findViewById(R.id.description_layout).setVisibility(4);
        }
    }

    @Override // slider.library.Animations.BaseAnimationInterface
    public void onPrepareNextItemShowInScreen(View next) {
        View descriptionLayout = next.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            next.findViewById(R.id.description_layout).setVisibility(4);
        }
    }

    @Override // slider.library.Animations.BaseAnimationInterface
    public void onCurrentItemDisappear(View view) {
    }

    @Override // slider.library.Animations.BaseAnimationInterface
    public void onNextItemAppear(View view) {
        View descriptionLayout = view.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            float layoutY = ViewHelper.getY(descriptionLayout);
            view.findViewById(R.id.description_layout).setVisibility(0);
            ValueAnimator animator = ObjectAnimator.ofFloat(descriptionLayout, "y", descriptionLayout.getHeight() + layoutY, layoutY).setDuration(500L);
            animator.start();
        }
    }
}