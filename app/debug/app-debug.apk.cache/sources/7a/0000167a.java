package com.shoeARstore;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class GridMainCategoryList extends BaseAdapter {
    ArrayList<String> categoryid;
    ArrayList<String> categoryimagepath;
    ArrayList<String> categoryname;
    Context context;
    Typeface fonts1;
    Typeface fonts2;

    public GridMainCategoryList(Context context, ArrayList<String> categoryname, ArrayList<String> categoryid, ArrayList<String> categoryimagepath) {
        this.context = context;
        this.categoryname = categoryname;
        this.categoryid = categoryid;
        this.categoryimagepath = categoryimagepath;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.categoryname.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return null;
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
            convertView = layoutInflater.inflate(com.mtel.shoe.R.layout.maincategory_listitem, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.categoryname = (TextView) convertView.findViewById(com.mtel.shoe.R.id.categoryname);
            viewHolder.categoryid = (TextView) convertView.findViewById(com.mtel.shoe.R.id.categoryid);
            viewHolder.image = (ImageView) convertView.findViewById(com.mtel.shoe.R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.categoryname.setText(this.categoryname.get(position));
        viewHolder.categoryid.setText(this.categoryid.get(position));
        Picasso.get().load(MainActivity.server_url + this.categoryimagepath.get(position)).into(viewHolder.image);
        return convertView;
    }

    /* loaded from: classes9.dex */
    private class ViewHolder {
        TextView categoryid;
        TextView categoryname;
        ImageView image;

        private ViewHolder() {
        }
    }
}