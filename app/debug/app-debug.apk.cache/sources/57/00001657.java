package com.shoeARstore;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class BaseAdapterNotification extends BaseAdapter {
    ArrayList<BeanclassNotification> bean;
    Context context;
    Typeface fonts1;
    Typeface fonts2;

    public BaseAdapterNotification(Context context, ArrayList<BeanclassNotification> bean) {
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
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
            convertView = layoutInflater.inflate(com.mtel.shoe.R.layout.list_notification, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(com.mtel.shoe.R.id.title);
            viewHolder.time = (TextView) convertView.findViewById(com.mtel.shoe.R.id.time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BeanclassNotification bean = (BeanclassNotification) getItem(position);
        viewHolder.title.setText(bean.getTitle());
        viewHolder.time.setText(bean.getTime());
        return convertView;
    }

    /* loaded from: classes9.dex */
    private class ViewHolder {
        TextView time;
        TextView title;

        private ViewHolder() {
        }
    }
}