package adresspage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.mtel.shoe.R;
import com.shoeARstore.MainActivity;
import com.shoeARstore.getData;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class AdressBaseAdapter extends BaseAdapter {
    ArrayList<AdressBeanlist> Bean;
    Context context;
    String kullaniciid;

    public AdressBaseAdapter(ArrayList<AdressBeanlist> bean, Context context) {
        this.Bean = bean;
        this.context = context;
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
        final CustomViewHolder cvh;
        if (convertView == null) {
            cvh = new CustomViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.list_adress_page, (ViewGroup) null);
            cvh.txtId = (TextView) convertView.findViewById(R.id.icerikId);
            cvh.txtTitle = (TextView) convertView.findViewById(R.id.title);
            cvh.txtIcerik = (TextView) convertView.findViewById(R.id.icerik);
            cvh.txtDelete = (TextView) convertView.findViewById(R.id.delete);
            convertView.setTag(cvh);
        } else {
            cvh = (CustomViewHolder) convertView.getTag();
        }
        SharedPreferences prefs = this.context.getSharedPreferences("login", 0);
        this.kullaniciid = prefs.getString("id", "0");
        AdressBeanlist beanlist = (AdressBeanlist) getItem(position);
        cvh.txtId.setText(beanlist.getId());
        cvh.txtTitle.setText(beanlist.getAdressname());
        cvh.txtDelete.setTag(beanlist.getAdressType());
        if (beanlist.getAdressType().equalsIgnoreCase("delivery")) {
            cvh.txtIcerik.setText("Name:" + beanlist.getName() + " " + beanlist.getSurname() + "\nMobile:" + beanlist.getTelephone() + "\nCountry-Province-District:" + beanlist.getCountry() + "/" + beanlist.getCity() + "/" + beanlist.getDistrict() + "\nAddress:" + beanlist.getOpenadress());
        } else {
            cvh.txtIcerik.setText("Full Name:" + beanlist.getName() + " " + beanlist.getSurname() + "\nPhone:" + beanlist.getTelephone() + "\nCountry-Province-District:" + beanlist.getCountry() + "/" + beanlist.getCity() + "/" + beanlist.getDistrict() + "\nAddress:" + beanlist.getOpenadress() + "\nCompany Name:" + beanlist.getCompanyname() + "\nTax Name:" + beanlist.getCompanytaxname() + "\nTax Number:" + beanlist.getCompanytaxnumber());
        }
        cvh.txtDelete.setOnClickListener(new View.OnClickListener() { // from class: adresspage.AdressBaseAdapter.1
            /* JADX WARN: Type inference failed for: r0v0, types: [adresspage.AdressBaseAdapter$1$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                new getData(AdressBaseAdapter.this.context) { // from class: adresspage.AdressBaseAdapter.1.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(String s) {
                        if (s != null || !s.equals("")) {
                            Toast.makeText(AdressBaseAdapter.this.context, "Address Successfully Deleted...", 0).show();
                        }
                        Intent gec = new Intent(AdressBaseAdapter.this.context, MemberAdressActivity.class);
                        AdressBaseAdapter.this.context.startActivity(gec);
                        this.dialog.dismiss();
                        super.onPostExecute((AsyncTaskC00001) s);
                    }
                }.execute(new String[]{MainActivity.server_url + "adress.php", "sil", AdressBaseAdapter.this.kullaniciid + "-" + cvh.txtId.getText().toString() + "," + cvh.txtDelete.getTag()});
            }
        });
        return convertView;
    }

    /* loaded from: classes4.dex */
    class CustomViewHolder {
        public TextView txtDelete;
        public TextView txtIcerik;
        public TextView txtId;
        public TextView txtTitle;

        CustomViewHolder() {
        }
    }
}