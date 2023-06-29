package girisanimation;

import android.graphics.RectF;

/* loaded from: classes10.dex */
public final class MathUtils {
    /* JADX INFO: Access modifiers changed from: protected */
    public static float truncate(float f, int decimalPlaces) {
        float decimalShift = (float) Math.pow(10.0d, decimalPlaces);
        return Math.round(f * decimalShift) / decimalShift;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean haveSameAspectRatio(RectF r1, RectF r2) {
        float srcRectRatio = truncate(getRectRatio(r1), 2);
        float dstRectRatio = truncate(getRectRatio(r2), 2);
        return Math.abs(srcRectRatio - dstRectRatio) <= 0.01f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float getRectRatio(RectF rect) {
        return rect.width() / rect.height();
    }
}