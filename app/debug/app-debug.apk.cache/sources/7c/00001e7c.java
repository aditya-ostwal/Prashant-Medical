package loadinganimation;

import android.content.Context;
import android.util.SparseArray;
import java.lang.reflect.Constructor;

/* loaded from: classes10.dex */
public final class LoadingRendererFactory {
    private static final SparseArray<Class<? extends LoadingRenderer>> LOADING_RENDERERS;

    static {
        SparseArray<Class<? extends LoadingRenderer>> sparseArray = new SparseArray<>();
        LOADING_RENDERERS = sparseArray;
        sparseArray.put(6, DanceLoadingRenderer.class);
    }

    private LoadingRendererFactory() {
    }

    public static LoadingRenderer createLoadingRenderer(Context context, int loadingRendererId) throws Exception {
        Class<?> loadingRendererClazz = LOADING_RENDERERS.get(loadingRendererId);
        Constructor<?>[] constructors = loadingRendererClazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes != null && parameterTypes.length == 1 && parameterTypes[0].equals(Context.class)) {
                constructor.setAccessible(true);
                return (LoadingRenderer) constructor.newInstance(context);
            }
        }
        throw new InstantiationException();
    }
}