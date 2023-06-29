package com.shoeARstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/* loaded from: classes9.dex */
class SubCategoryAdapter extends ArrayAdapter<String> {
    Context c;
    String[] categoryid;
    String[] categoryname;
    LayoutInflater inflater;

    public SubCategoryAdapter(Context context, String[] categoryname, String[] categoryid) {
        super(context, (int) com.mtel.shoe.R.layout.subcategories_listview_item, categoryname);
        this.categoryname = categoryname;
        this.categoryid = categoryid;
        this.c = context;
    }

    /* loaded from: classes9.dex */
    public class ViewHolder {
        TextView categoryid;
        TextView categoryname;

        public ViewHolder() {
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.c.getSystemService("layout_inflater");
            this.inflater = layoutInflater;
            convertView = layoutInflater.inflate(com.mtel.shoe.R.layout.subcategories_listview_item, (ViewGroup) null, true);
        }
        ViewHolder holder = new ViewHolder();
        holder.categoryname = (TextView) convertView.findViewById(com.mtel.shoe.R.id.categoryname);
        holder.categoryid = (TextView) convertView.findViewById(com.mtel.shoe.R.id.categoryid);
        holder.categoryname.setText(this.categoryname[position]);
        holder.categoryid.setText(this.categoryid[position]);
        return convertView;
    }
}