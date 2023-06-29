package favoritespage;

import android.content.Context;
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

/* loaded from: classes9.dex */
public class FavBaseAdapter extends BaseAdapter {
    ArrayList<FavBeanlist> Bean;
    Context context;

    public FavBaseAdapter(ArrayList<FavBeanlist> bean, Context context) {
        this.Bean = bean;
        this.context = context;
    }

    public void setItem(int position, FavBeanlist element) {
        this.Bean.set(position, element);
        notifyDataSetChanged();
    }

    public void addItem(int position, FavBeanlist element) {
        this.Bean.add(position, element);
        notifyDataSetChanged();
    }

    public FavBeanlist deleteItem(int position) {
        FavBeanlist silinen = this.Bean.remove(position);
        notifyDataSetChanged();
        return silinen;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.Bean.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.Bean.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return this.Bean.get(position).hashCode();
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder cvh;
        if (convertView == null) {
            cvh = new CustomViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_custom_btn, (ViewGroup) null);
            cvh.imgLogo = (ImageView) convertView.findViewById(R.id.image);
            cvh.txtName = (TextView) convertView.findViewById(R.id.title);
            cvh.txtPrice = (TextView) convertView.findViewById(R.id.price);
            cvh.txtId = (TextView) convertView.findViewById(R.id.id);
            cvh.txtStock = (TextView) convertView.findViewById(R.id.stockcode);
            convertView.setTag(cvh);
        } else {
            cvh = (CustomViewHolder) convertView.getTag();
        }
        FavBeanlist beanlist = (FavBeanlist) getItem(position);
        Picasso.get().load(MainActivity.server_url + beanlist.getImage()).into(cvh.imgLogo);
        cvh.txtName.setText(beanlist.getTitle());
        cvh.txtPrice.setText(beanlist.getPrice() + "₹");
        cvh.txtStock.setText(beanlist.getStockcode());
        return convertView;
    }

    /* loaded from: classes9.dex */
    class CustomViewHolder {
        public ImageView imgLogo;
        public TextView txtId;
        public TextView txtName;
        public TextView txtPrice;
        public TextView txtStock;

        CustomViewHolder() {
        }
    }
}