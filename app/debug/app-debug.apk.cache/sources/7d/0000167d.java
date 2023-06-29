package com.shoeARstore;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class GroomingRecyclerViewAdapter extends RecyclerView.Adapter<DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static MyClickListener myClickListener;
    private ArrayList<Beanlist> bean;
    private Context context;
    String kullaniciid;
    Activity main;
    SharedPreferences prefs;

    /* loaded from: classes9.dex */
    public interface MyClickListener {
        void onItemClick(int i, View view);
    }

    /* loaded from: classes9.dex */
    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cutprice;
        TextView discount;
        ImageView fav1;
        ImageView fav2;
        TextView id;
        ImageView image;
        private SmallBang mSmallBang;
        TextView price;
        RatingBar ratingbar;
        TextView ratingtex;
        TextView title;

        public DataObjectHolder(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(com.mtel.shoe.R.id.image);
            this.title = (TextView) itemView.findViewById(com.mtel.shoe.R.id.title);
            this.price = (TextView) itemView.findViewById(com.mtel.shoe.R.id.price);
            this.cutprice = (TextView) itemView.findViewById(com.mtel.shoe.R.id.cutprice);
            this.discount = (TextView) itemView.findViewById(com.mtel.shoe.R.id.discount);
            this.ratingtex = (TextView) itemView.findViewById(com.mtel.shoe.R.id.ratingtext);
            this.id = (TextView) itemView.findViewById(com.mtel.shoe.R.id.id);
            TextView textView = this.cutprice;
            textView.setPaintFlags(textView.getPaintFlags() | 16);
            RatingBar ratingBar = (RatingBar) itemView.findViewById(com.mtel.shoe.R.id.ratingbar);
            this.ratingbar = ratingBar;
            LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#f8d64e"), PorterDuff.Mode.SRC_ATOP);
            this.fav1 = (ImageView) itemView.findViewById(com.mtel.shoe.R.id.fav1);
            this.fav2 = (ImageView) itemView.findViewById(com.mtel.shoe.R.id.fav2);
            Log.i(GroomingRecyclerViewAdapter.LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            GroomingRecyclerViewAdapter.myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener2) {
        myClickListener = myClickListener2;
    }

    public GroomingRecyclerViewAdapter(Activity activity, Context context, ArrayList<Beanlist> bean) {
        this.main = activity;
        this.context = context;
        this.bean = bean;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.mtel.shoe.R.layout.list, parent, false);
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("login", 0);
        this.prefs = sharedPreferences;
        this.kullaniciid = sharedPreferences.getString("id", "-1");
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        Picasso.get().load(this.bean.get(position).getImage()).into(holder.image);
        holder.title.setText(this.bean.get(position).getTitle());
        if (this.bean.get(position).getPrice().equals("0")) {
            holder.price.setText(this.bean.get(position).getCutprice() + "₹");
            holder.cutprice.setVisibility(4);
            holder.discount.setText("%0");
        } else {
            holder.price.setText(this.bean.get(position).getPrice() + "₹");
            holder.cutprice.setText(this.bean.get(position).getCutprice() + "₹");
            holder.discount.setText(this.bean.get(position).getDiscount());
        }
        holder.ratingtex.setText("(" + this.bean.get(position).getRatingtext() + ")");
        holder.ratingbar.setRating(this.bean.get(position).getRating());
        holder.id.setText(this.bean.get(position).getId());
        if (this.bean.get(position).getFavorites().equals("1")) {
            holder.fav2.setVisibility(0);
            holder.fav1.setVisibility(8);
        }
        holder.mSmallBang = SmallBang.attach2Window(this.main);
        holder.fav1.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.GroomingRecyclerViewAdapter.1
            /* JADX WARN: Type inference failed for: r0v10, types: [com.shoeARstore.GroomingRecyclerViewAdapter$1$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (GroomingRecyclerViewAdapter.this.kullaniciid.equals("-1")) {
                    Toast.makeText(GroomingRecyclerViewAdapter.this.context, "Please Login!!!", 0).show();
                    return;
                }
                holder.fav2.setVisibility(0);
                holder.fav1.setVisibility(8);
                like(v);
                new getData(GroomingRecyclerViewAdapter.this.context) { // from class: com.shoeARstore.GroomingRecyclerViewAdapter.1.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(String s) {
                        if (!s.equals("")) {
                            Toast.makeText(GroomingRecyclerViewAdapter.this.context, "Product Successfully Added to Favorites...", 0).show();
                        } else {
                            Toast.makeText(GroomingRecyclerViewAdapter.this.context, "The Product Could Not Be Added To Favorites...", 0).show();
                        }
                        this.dialog.dismiss();
                        super.onPostExecute((AsyncTaskC00081) s);
                    }
                }.execute(new String[]{MainActivity.server_url + "favorites.php", GroomingRecyclerViewAdapter.this.kullaniciid, ((Beanlist) GroomingRecyclerViewAdapter.this.bean.get(position)).getId() + "-add"});
            }

            public void like(View view) {
                holder.fav2.setImageResource(com.mtel.shoe.R.drawable.f4);
                holder.mSmallBang.bang(view);
                holder.mSmallBang.setmListener(new SmallBangListener() { // from class: com.shoeARstore.GroomingRecyclerViewAdapter.1.2
                    @Override // com.shoeARstore.SmallBangListener
                    public void onAnimationStart() {
                    }

                    @Override // com.shoeARstore.SmallBangListener
                    public void onAnimationEnd() {
                    }
                });
            }
        });
        holder.fav2.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.GroomingRecyclerViewAdapter.2
            /* JADX WARN: Type inference failed for: r0v10, types: [com.shoeARstore.GroomingRecyclerViewAdapter$2$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (GroomingRecyclerViewAdapter.this.kullaniciid.equals("-1")) {
                    Toast.makeText(GroomingRecyclerViewAdapter.this.context, "Please Login!!!", 0).show();
                    return;
                }
                holder.fav2.setVisibility(8);
                holder.fav1.setVisibility(0);
                new getData(GroomingRecyclerViewAdapter.this.context) { // from class: com.shoeARstore.GroomingRecyclerViewAdapter.2.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(String s) {
                        if (!s.equals("")) {
                            Toast.makeText(GroomingRecyclerViewAdapter.this.context, "Product Successfully Removed From Favorites...", 0).show();
                        } else {
                            Toast.makeText(GroomingRecyclerViewAdapter.this.context, "The Product Could Not Be Removed From Favorites...", 0).show();
                        }
                        this.dialog.dismiss();
                        super.onPostExecute((AnonymousClass1) s);
                    }
                }.execute(new String[]{MainActivity.server_url + "favorites.php", GroomingRecyclerViewAdapter.this.kullaniciid, ((Beanlist) GroomingRecyclerViewAdapter.this.bean.get(position)).getId() + "-delete"});
            }
        });
    }

    public void deleteItem(int index) {
        this.bean.remove(index);
        notifyItemRemoved(index);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.bean.size();
    }
}