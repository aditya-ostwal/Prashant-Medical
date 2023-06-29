package yorumlarsayfasi;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mtel.shoe.R;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class BaseAdapterReview extends BaseAdapter {
    ArrayList<BeanclassReview> bean;
    Context context;
    RatingBar ratingbar;

    public BaseAdapterReview(Context context, ArrayList<BeanclassReview> bean) {
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
            convertView = layoutInflater.inflate(R.layout.list_review, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.rateno = (TextView) convertView.findViewById(R.id.rateno);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.username = (TextView) convertView.findViewById(R.id.username);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.like = (TextView) convertView.findViewById(R.id.like);
            RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingbar);
            this.ratingbar = ratingBar;
            LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(convertView.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BeanclassReview bean = (BeanclassReview) getItem(position);
        viewHolder.title.setText(bean.getTitle());
        viewHolder.rateno.setText(bean.getRateno());
        viewHolder.date.setText(bean.getDate());
        viewHolder.username.setText(bean.getUsername());
        viewHolder.description.setText(bean.getDescription());
        viewHolder.like.setText(bean.getLike());
        String rate = bean.getRateno().substring(0, bean.getRateno().indexOf("/"));
        this.ratingbar.setRating(Float.parseFloat(rate));
        return convertView;
    }

    /* loaded from: classes8.dex */
    private class ViewHolder {
        TextView date;
        TextView description;
        TextView like;
        TextView rateno;
        TextView title;
        TextView username;

        private ViewHolder() {
        }
    }
}