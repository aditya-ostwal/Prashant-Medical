package favoritespage.items;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* loaded from: classes7.dex */
class SDMenuItemView extends BaseLayout {
    protected ImageView mImageView;
    protected TextView mTextView;

    /* JADX INFO: Access modifiers changed from: protected */
    public SDMenuItemView(Context context, MenuItem menuItem) {
        super(context, menuItem);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // favoritespage.items.BaseLayout
    public void build() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mMenuItem.width, -1);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        addView(createBG());
        if (!TextUtils.isEmpty(this.mMenuItem.text) && this.mMenuItem.icon != null) {
            this.mImageView = createImageView();
            this.mTextView = createTextView();
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(1);
            linearLayout.addView(this.mImageView);
            linearLayout.addView(this.mTextView);
            linearLayout.setGravity(17);
            addView(linearLayout);
        } else if (this.mMenuItem.icon != null) {
            ImageView createImageView = createImageView();
            this.mImageView = createImageView;
            addView(createImageView);
        } else if (!TextUtils.isEmpty(this.mMenuItem.text)) {
            TextView createTextView = createTextView();
            this.mTextView = createTextView;
            addView(createTextView);
        } else {
            addView(createEmptyView());
        }
    }

    private ImageView createBG() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(this.mMenuItem.background);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    private TextView createTextView() {
        TextView textView = new TextView(getContext());
        textView.setText(this.mMenuItem.text);
        textView.setTextSize(this.mMenuItem.textSize);
        textView.setTextColor(this.mMenuItem.textColor);
        textView.setGravity(17);
        return textView;
    }

    private View createEmptyView() {
        View view = new View(getContext());
        return view;
    }

    protected ImageView createImageView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(this.mMenuItem.icon);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return imageView;
    }

    @Override // favoritespage.items.BaseLayout
    public TextView getTextView() {
        return this.mTextView;
    }

    @Override // favoritespage.items.BaseLayout
    public ImageView getImageView() {
        return this.mImageView;
    }
}