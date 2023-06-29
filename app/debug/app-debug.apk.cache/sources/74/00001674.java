package com.shoeARstore;

import android.util.Log;
import android.view.View;
import slider.library.Animations.BaseAnimationInterface;

/* loaded from: classes9.dex */
public class ChildAnimationExample implements BaseAnimationInterface {
    private static final String TAG = "ChildAnimationExample";

    @Override // slider.library.Animations.BaseAnimationInterface
    public void onPrepareCurrentItemLeaveScreen(View current) {
        View descriptionLayout = current.findViewById(com.mtel.shoe.R.id.description_layout);
        if (descriptionLayout != null) {
            current.findViewById(com.mtel.shoe.R.id.description_layout).setVisibility(4);
        }
        Log.e(TAG, "onPrepareCurrentItemLeaveScreen called");
    }

    @Override // slider.library.Animations.BaseAnimationInterface
    public void onPrepareNextItemShowInScreen(View next) {
        View descriptionLayout = next.findViewById(com.mtel.shoe.R.id.description_layout);
        if (descriptionLayout != null) {
            next.findViewById(com.mtel.shoe.R.id.description_layout).setVisibility(4);
        }
        Log.e(TAG, "onPrepareNextItemShowInScreen called");
    }

    @Override // slider.library.Animations.BaseAnimationInterface
    public void onCurrentItemDisappear(View view) {
        Log.e(TAG, "onCurrentItemDisappear called");
    }

    @Override // slider.library.Animations.BaseAnimationInterface
    public void onNextItemAppear(View view) {
        View descriptionLayout = view.findViewById(com.mtel.shoe.R.id.description_layout);
        if (descriptionLayout != null) {
            view.findViewById(com.mtel.shoe.R.id.description_layout).setVisibility(4);
        }
        Log.e(TAG, "onCurrentItemDisappear called");
    }
}