package slider.library.SliderTypes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mtel.shoe.R;

/* loaded from: classes9.dex */
public class DefaultSliderView extends BaseSliderView {
    public DefaultSliderView(Context context) {
        super(context);
    }

    @Override // slider.library.SliderTypes.BaseSliderView
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_default, (ViewGroup) null);
        ImageView target = (ImageView) v.findViewById(R.id.daimajia_slider_image);
        bindEventAndShow(v, target);
        return v;
    }
}