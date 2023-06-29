package favoritespage.items;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes7.dex */
public abstract class BaseLayout extends FrameLayout {
    protected MenuItem mMenuItem;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void build();

    public abstract ImageView getImageView();

    public abstract TextView getTextView();

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseLayout(Context context, MenuItem menuItem) {
        super(context);
        this.mMenuItem = menuItem;
    }
}