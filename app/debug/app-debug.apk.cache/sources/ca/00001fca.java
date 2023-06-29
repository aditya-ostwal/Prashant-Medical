package slider.library.Transformers;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/* loaded from: classes11.dex */
public class TabletTransformer extends BaseTransformer {
    private static final Matrix OFFSET_MATRIX = new Matrix();
    private static final Camera OFFSET_CAMERA = new Camera();
    private static final float[] OFFSET_TEMP_FLOAT = new float[2];

    @Override // slider.library.Transformers.BaseTransformer
    protected void onTransform(View view, float position) {
        float rotation = (position < 0.0f ? 30.0f : -30.0f) * Math.abs(position);
        ViewHelper.setTranslationX(view, getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
        ViewHelper.setPivotX(view, view.getWidth() * 0.5f);
        ViewHelper.setPivotY(view, 0.0f);
        ViewHelper.setRotationY(view, rotation);
    }

    protected static final float getOffsetXForRotation(float degrees, int width, int height) {
        Matrix matrix = OFFSET_MATRIX;
        matrix.reset();
        Camera camera = OFFSET_CAMERA;
        camera.save();
        camera.rotateY(Math.abs(degrees));
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate((-width) * 0.5f, (-height) * 0.5f);
        matrix.postTranslate(width * 0.5f, height * 0.5f);
        float[] fArr = OFFSET_TEMP_FLOAT;
        fArr[0] = width;
        fArr[1] = height;
        matrix.mapPoints(fArr);
        return (width - fArr[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
    }
}