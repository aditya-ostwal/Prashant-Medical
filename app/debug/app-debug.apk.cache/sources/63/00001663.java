package com.shoeARstore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class CartListBaseAdapter extends BaseAdapter {
    ArrayList<CartListBeanlist> bean;
    Context context;
    Typeface fonts1;
    Typeface fonts2;

    public CartListBaseAdapter(Context context, ArrayList<CartListBeanlist> bean) {
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
        this.fonts2 = Typeface.createFromAsset(this.context.getAssets(), "fonts/MavenPro-Regular.ttf");
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
            convertView = layoutInflater.inflate(com.mtel.shoe.R.layout.cart_list, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.deletebutton = (ImageView) convertView.findViewById(com.mtel.shoe.R.id.deleteitem);
            viewHolder.image = (ImageView) convertView.findViewById(com.mtel.shoe.R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(com.mtel.shoe.R.id.title);
            viewHolder.price = (TextView) convertView.findViewById(com.mtel.shoe.R.id.price);
            viewHolder.stockcode = (TextView) convertView.findViewById(com.mtel.shoe.R.id.productstockcode);
            viewHolder.text = (TextView) convertView.findViewById(com.mtel.shoe.R.id.text);
            viewHolder.title.setTypeface(this.fonts2);
            viewHolder.min = (ImageView) convertView.findViewById(com.mtel.shoe.R.id.min);
            viewHolder.plus = (ImageView) convertView.findViewById(com.mtel.shoe.R.id.plus);
            viewHolder.size = (TextView) convertView.findViewById(com.mtel.shoe.R.id.size);
            viewHolder.id = (TextView) convertView.findViewById(com.mtel.shoe.R.id.id);
            viewHolder.totalprice = (TextView) convertView.findViewById(com.mtel.shoe.R.id.totalprice);
            viewHolder.text.setTypeface(this.fonts1);
            viewHolder.price.setTypeface(this.fonts2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.deletebutton.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.CartListBaseAdapter.1
            /* JADX WARN: Type inference failed for: r1v1, types: [com.shoeARstore.CartListBaseAdapter$1$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                TextView id = (TextView) ((LinearLayout) v.getParent()).findViewById(com.mtel.shoe.R.id.id);
                new getData(CartListBaseAdapter.this.context) { // from class: com.shoeARstore.CartListBaseAdapter.1.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(String s) {
                        if (!s.equals("") && s != null) {
                            Toast.makeText(this.c, "Product Removed from Cart...", 0).show();
                            Intent gec = new Intent(this.c, HomeFragment.class);
                            gec.putExtra("fragment", "4");
                            this.c.startActivity(gec);
                        } else {
                            Toast.makeText(this.c, "The product could not be removed from the cart!!!", 0).show();
                        }
                        this.dialog.dismiss();
                        super.onPostExecute((AsyncTaskC00071) s);
                    }
                }.execute(new String[]{MainActivity.server_url + "cartlist.php", id.getText().toString(), "delete"});
            }
        });
        CartListBeanlist bean = (CartListBeanlist) getItem(position);
        Picasso.get().load(bean.getImage()).into(viewHolder.image);
        viewHolder.title.setText(bean.getTitle());
        viewHolder.stockcode.setText(bean.getStockcode());
        Float toplamfiyat = Float.valueOf(Float.parseFloat(bean.getPiece()) * Float.parseFloat(bean.getPrice()));
        viewHolder.totalprice.setText(toplamfiyat + " ₹");
        viewHolder.price.setText(bean.getPrice() + " ₹");
        viewHolder.text.setText(bean.getPiece());
        viewHolder.size.setText(bean.getSize());
        viewHolder.id.setText(bean.getId());
        final ViewHolder finalViewHolder = viewHolder;
        final int[] number = {Integer.parseInt(bean.getPiece())};
        viewHolder.min.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.CartListBaseAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (number[0] == 1) {
                    finalViewHolder.text.setText("" + number[0]);
                }
                int[] iArr = number;
                if (iArr[0] > 1) {
                    iArr[0] = iArr[0] - 1;
                    finalViewHolder.text.setText("" + number[0]);
                }
                CartListBaseAdapter.this.pieceUpdate(finalViewHolder, number[0]);
            }
        });
        viewHolder.plus.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.CartListBaseAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                int[] iArr = number;
                iArr[0] = iArr[0] + 1;
                finalViewHolder.text.setText(String.valueOf(number[0]));
                CartListBaseAdapter.this.pieceUpdate(finalViewHolder, number[0]);
            }
        });
        return convertView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.shoeARstore.CartListBaseAdapter$4] */
    public void pieceUpdate(ViewHolder finalViewHolder, int piece) {
        new getData(this.context) { // from class: com.shoeARstore.CartListBaseAdapter.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String s) {
                if (s.equals("") || s == null) {
                    Toast.makeText(this.c, "Product Failed to Update!!!", 0).show();
                }
                this.dialog.dismiss();
                super.onPostExecute((AnonymousClass4) s);
            }
        }.execute(new String[]{MainActivity.server_url + "cartlist.php", finalViewHolder.id.getText().toString(), String.valueOf(piece)});
        Float toplamfiyat = Float.valueOf(Float.parseFloat(finalViewHolder.price.getText().toString().replace(" ₹", "")) * Float.parseFloat(finalViewHolder.text.getText().toString()));
        finalViewHolder.totalprice.setText(toplamfiyat + " ₹");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class ViewHolder {
        ImageView deletebutton;
        TextView id;
        ImageView image;
        ImageView min;
        ImageView plus;
        TextView price;
        TextView size;
        TextView stockcode;
        TextView text;
        TextView title;
        TextView totalprice;

        private ViewHolder() {
        }
    }
}