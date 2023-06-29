package com.shoeARstore;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class ListviewAdapter extends BaseAdapter {
    ArrayList<Beanlist> bean;
    Context context;
    Typeface fonts1;
    Typeface fonts2;
    String kullaniciid;
    int listelayout;
    Activity main;
    SharedPreferences prefs;
    RatingBar ratingbar;

    public ListviewAdapter(Activity activity, Context context, ArrayList<Beanlist> bean, int listelayout) {
        this.main = activity;
        this.context = context;
        this.bean = bean;
        this.listelayout = listelayout;
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
        final ViewHolder viewHolder;
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("login", 0);
        this.prefs = sharedPreferences;
        this.kullaniciid = sharedPreferences.getString("id", "-1");
        this.fonts1 = Typeface.createFromAsset(this.context.getAssets(), "fonts/MavenPro-Regular.ttf");
        this.fonts2 = Typeface.createFromAsset(this.context.getAssets(), "fonts/MavenPro-Regular.ttf");
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
            convertView = layoutInflater.inflate(this.listelayout, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(com.mtel.shoe.R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(com.mtel.shoe.R.id.title);
            viewHolder.price = (TextView) convertView.findViewById(com.mtel.shoe.R.id.price);
            viewHolder.cutprice = (TextView) convertView.findViewById(com.mtel.shoe.R.id.cutprice);
            viewHolder.discount = (TextView) convertView.findViewById(com.mtel.shoe.R.id.discount);
            viewHolder.ratingtext = (TextView) convertView.findViewById(com.mtel.shoe.R.id.ratingtext);
            viewHolder.fav1 = (ImageView) convertView.findViewById(com.mtel.shoe.R.id.fav1);
            viewHolder.fav2 = (ImageView) convertView.findViewById(com.mtel.shoe.R.id.fav2);
            viewHolder.id = (TextView) convertView.findViewById(com.mtel.shoe.R.id.id);
            viewHolder.title.setTypeface(this.fonts1);
            viewHolder.price.setTypeface(this.fonts1);
            viewHolder.cutprice.setTypeface(this.fonts1);
            viewHolder.discount.setTypeface(this.fonts1);
            viewHolder.ratingtext.setTypeface(this.fonts1);
            RatingBar ratingBar = (RatingBar) convertView.findViewById(com.mtel.shoe.R.id.ratingbar);
            this.ratingbar = ratingBar;
            LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#f8d64e"), PorterDuff.Mode.SRC_ATOP);
            convertView.setTag(viewHolder);
            viewHolder.cutprice.setPaintFlags(viewHolder.cutprice.getPaintFlags() | 16);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Beanlist bean = (Beanlist) getItem(position);
        Picasso.get().load(bean.getImage()).into(viewHolder.image);
        viewHolder.title.setText(bean.getTitle());
        if (bean.getPrice().equals("0")) {
            viewHolder.price.setText(bean.getCutprice() + "₹");
            viewHolder.cutprice.setText("0");
            viewHolder.cutprice.setVisibility(4);
            viewHolder.discount.setText("%0");
        } else {
            viewHolder.price.setText(bean.getPrice() + "₹");
            viewHolder.cutprice.setText(bean.getCutprice() + "₹");
            viewHolder.discount.setText(bean.getDiscount());
        }
        if (bean.getFavorites().equals("1")) {
            viewHolder.fav2.setVisibility(0);
            viewHolder.fav1.setVisibility(8);
        }
        viewHolder.ratingtext.setText("(" + bean.getRatingtext() + ")");
        this.ratingbar.setRating(bean.getRating());
        viewHolder.mSmallBang = SmallBang.attach2Window(this.main);
        viewHolder.id.setText(bean.getId().toString());
        viewHolder.fav1.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.ListviewAdapter.1
            /* JADX WARN: Type inference failed for: r0v10, types: [com.shoeARstore.ListviewAdapter$1$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (!ListviewAdapter.this.kullaniciid.equals("-1")) {
                    viewHolder.fav2.setVisibility(0);
                    viewHolder.fav1.setVisibility(8);
                    like(v);
                    new getData(ListviewAdapter.this.context) { // from class: com.shoeARstore.ListviewAdapter.1.1
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public void onPostExecute(String s) {
                            if (!s.equals("")) {
                                Toast.makeText(ListviewAdapter.this.context, "Product Successfully Added to Favorites...", 0).show();
                            } else {
                                Toast.makeText(ListviewAdapter.this.context, "The Product Could Not Be Added To Favorites...", 0).show();
                            }
                            this.dialog.dismiss();
                            super.onPostExecute((AsyncTaskC00091) s);
                        }
                    }.execute(new String[]{MainActivity.server_url + "favorites.php", ListviewAdapter.this.kullaniciid, bean.getId() + "-add"});
                    return;
                }
                Toast.makeText(ListviewAdapter.this.context, "Please Login!!!", 0).show();
            }

            public void like(View view) {
                viewHolder.fav2.setImageResource(com.mtel.shoe.R.drawable.f4);
                viewHolder.mSmallBang.bang(view);
                viewHolder.mSmallBang.setmListener(new SmallBangListener() { // from class: com.shoeARstore.ListviewAdapter.1.2
                    @Override // com.shoeARstore.SmallBangListener
                    public void onAnimationStart() {
                    }

                    @Override // com.shoeARstore.SmallBangListener
                    public void onAnimationEnd() {
                    }
                });
            }
        });
        viewHolder.fav2.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.ListviewAdapter.2
            /* JADX WARN: Type inference failed for: r0v10, types: [com.shoeARstore.ListviewAdapter$2$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (!ListviewAdapter.this.kullaniciid.equals("-1")) {
                    viewHolder.fav2.setVisibility(8);
                    viewHolder.fav1.setVisibility(0);
                    new getData(ListviewAdapter.this.context) { // from class: com.shoeARstore.ListviewAdapter.2.1
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public void onPostExecute(String s) {
                            if (!s.equals("")) {
                                Toast.makeText(ListviewAdapter.this.context, "Product Successfully Removed From Favorites...", 0).show();
                            } else {
                                Toast.makeText(ListviewAdapter.this.context, "The Product Could Not Be Removed From Favorites...", 0).show();
                            }
                            this.dialog.dismiss();
                            super.onPostExecute((AnonymousClass1) s);
                        }
                    }.execute(new String[]{MainActivity.server_url + "favorites.php", ListviewAdapter.this.kullaniciid, bean.getId() + "-delete"});
                    return;
                }
                Toast.makeText(ListviewAdapter.this.context, "Please Login!!!", 0).show();
            }
        });
        return convertView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class ViewHolder {
        TextView cutprice;
        TextView discount;
        ImageView fav1;
        ImageView fav2;
        TextView id;
        ImageView image;
        SmallBang mSmallBang;
        TextView price;
        TextView ratingtext;
        TextView title;

        private ViewHolder() {
        }
    }
}