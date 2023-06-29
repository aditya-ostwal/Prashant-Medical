package loadinganimation;

import android.content.Context;

/* loaded from: classes10.dex */
public class DensityUtil {
    public static float dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }
}