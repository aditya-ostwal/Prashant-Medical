package favoritespage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* loaded from: classes9.dex */
public class Utils {
    public static Drawable getDrawable(Context context, int res) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getResources().getDrawable(res, context.getTheme());
        }
        return context.getResources().getDrawable(res);
    }
}