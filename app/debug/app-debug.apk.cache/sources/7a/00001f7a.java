package orderspage;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mtel.shoe.R;
import com.shoeARstore.MainActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ListBaseAdapterOrder extends BaseAdapter {
    ArrayList<BeanclassOrderDetail> bean;
    Context context;
    Typeface fonts1;
    private ProgressBar progressBar1;

    public ListBaseAdapterOrder(Context context, ArrayList<BeanclassOrderDetail> bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.bean.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.bean.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        this.fonts1 = Typeface.createFromAsset(this.context.getAssets(), "fonts/MavenPro-Regular.ttf");
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
            convertView = layoutInflater.inflate(R.layout.list_order, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.orderday = (TextView) convertView.findViewById(R.id.orderday);
            viewHolder.ordername = (TextView) convertView.findViewById(R.id.ordername);
            viewHolder.qt = (TextView) convertView.findViewById(R.id.qt);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.idnumber = (TextView) convertView.findViewById(R.id.idnumber);
            viewHolder.progress = (ProgressBar) convertView.findViewById(R.id.progressBar1);
            viewHolder.orderday.setTypeface(this.fonts1);
            viewHolder.ordername.setTypeface(this.fonts1);
            viewHolder.qt.setTypeface(this.fonts1);
            viewHolder.date.setTypeface(this.fonts1);
            viewHolder.idnumber.setTypeface(this.fonts1);
            ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar1);
            this.progressBar1 = progressBar;
            progressBar.getProgressDrawable().setColorFilter(this.context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
            viewHolder.progress.getProgressDrawable().setColorFilter(this.context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BeanclassOrderDetail bean = (BeanclassOrderDetail) getItem(position);
        Picasso.get().load(MainActivity.server_url + bean.getImage()).into(viewHolder.image);
        viewHolder.orderday.setText(bean.getOrderday());
        viewHolder.ordername.setText(bean.getOrdername());
        viewHolder.qt.setText(bean.getQt());
        viewHolder.date.setText(bean.getDate());
        viewHolder.idnumber.setText(bean.getIdnumber());
        viewHolder.progress.setProgress(bean.getProgress());
        return convertView;
    }

    /* loaded from: classes3.dex */
    private class ViewHolder {
        TextView date;
        TextView idnumber;
        ImageView image;
        TextView orderday;
        TextView ordername;
        ProgressBar progress;
        TextView qt;

        private ViewHolder() {
        }
    }
}