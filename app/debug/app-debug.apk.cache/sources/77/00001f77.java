package orderspage;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mtel.shoe.R;
import com.shoeARstore.MainActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class GridListViewItemAdapter extends BaseAdapter {
    Context context;
    Typeface fonts1;
    Typeface fonts2;
    ArrayList<BeanclassOrderPage> orders;

    public GridListViewItemAdapter(Context context, ArrayList<BeanclassOrderPage> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.orders.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.orders.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        this.fonts1 = Typeface.createFromAsset(this.context.getAssets(), "fonts/MavenPro-Regular.ttf");
        this.fonts2 = Typeface.createFromAsset(this.context.getAssets(), "fonts/MavenPro-Regular.ttf");
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
            convertView = layoutInflater.inflate(R.layout.order_page_grid_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.orderstatus = (TextView) convertView.findViewById(R.id.orderStatus);
            viewHolder.orderprice = (TextView) convertView.findViewById(R.id.orderPrice);
            viewHolder.ordernumber = (TextView) convertView.findViewById(R.id.orderNumber);
            viewHolder.orderdate = (TextView) convertView.findViewById(R.id.orderDate);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.orderImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BeanclassOrderPage beanclassOrderPage = (BeanclassOrderPage) getItem(position);
        Picasso.get().load(MainActivity.server_url + "assets/images/orderspageicon.png").into(viewHolder.image);
        viewHolder.orderdate.setText(beanclassOrderPage.getOrderdate());
        if (beanclassOrderPage.getStatus() == 2) {
            viewHolder.orderstatus.setText("Preparing");
        } else if (beanclassOrderPage.getStatus() == 3) {
            viewHolder.orderstatus.setText("in cargo");
        } else if (beanclassOrderPage.getStatus() == 4) {
            viewHolder.orderstatus.setText("Delivered");
        }
        viewHolder.ordernumber.setText("Order No:" + beanclassOrderPage.getOrderno());
        viewHolder.orderprice.setText(beanclassOrderPage.getPrice() + "₹");
        return convertView;
    }

    /* loaded from: classes3.dex */
    private class ViewHolder {
        ImageView image;
        TextView orderdate;
        TextView ordernumber;
        TextView orderprice;
        TextView orderstatus;

        private ViewHolder() {
        }
    }
}