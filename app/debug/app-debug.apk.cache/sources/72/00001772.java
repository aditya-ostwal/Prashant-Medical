package favoritespage.items;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;

/* loaded from: classes7.dex */
public final class MenuItem {
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = -1;
    public final Drawable background;
    public final int direction;
    public final Drawable icon;
    public final String text;
    public final int textColor;
    public final int textSize;
    public final int width;

    public MenuItem(int width, String text, int textSize, int textColor, Drawable icon, Drawable background, int direction) {
        this.width = width;
        this.text = text;
        this.textSize = textSize;
        this.textColor = textColor;
        this.icon = icon;
        this.background = background;
        this.direction = direction;
    }

    /* loaded from: classes7.dex */
    public static class Builder {
        private int width = 50;
        private String text = null;
        private int textSize = 14;
        private int textColor = ViewCompat.MEASURED_STATE_MASK;
        private Drawable icon = null;
        private Drawable background = new ColorDrawable(-1);
        private int direction = 1;

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder setIcon(Drawable icon) {
            this.icon = icon;
            return this;
        }

        public Builder setBackground(Drawable background) {
            this.background = background;
            return this;
        }

        public Builder setDirection(int direction) {
            this.direction = direction;
            return this;
        }

        public int getWidth() {
            return this.width;
        }

        public String getText() {
            return this.text;
        }

        public int getTextSize() {
            return this.textSize;
        }

        public int getTextColor() {
            return this.textColor;
        }

        public Drawable getIcon() {
            return this.icon;
        }

        public Drawable getBackground() {
            return this.background;
        }

        public int getDirection() {
            return this.direction;
        }

        public MenuItem build() {
            return new MenuItem(this.width, this.text, this.textSize, this.textColor, this.icon, this.background, this.direction);
        }
    }
}