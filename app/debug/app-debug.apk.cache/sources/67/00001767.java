package favoritespage.items;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/* loaded from: classes7.dex */
class Compat {
    Compat() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}