package adresspage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.shoeARstore.MainActivity;
import com.shoeARstore.R;
import com.shoeARstore.getData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Prashant Medical Stores
 */

public class MemberAdressActivity extends AppCompatActivity {
    TextView deliveryadress,invoiceadress;
    Button addDeliveryadress,addInvoiceadress;
    private String kullaniciid;
    ListView teslimatAdressList,faturaAdressList;
    private int teslimatAdresCount,faturaAdresCount;

    ArrayList<AdressBeanlist> BeanTeslimat,BeanFatura;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adress);

        //Component İnitialize
        deliveryadress=(TextView) findViewById(R.id.deliveryadresstext);
        invoiceadress=(TextView) findViewById(R.id.invoiceadresstext);
        addDeliveryadress=(Button) findViewById(R.id.adddeliveryadress);
        addInvoiceadress=(Button) findViewById(R.id.addinvoiceadress);
        teslimatAdressList=(ListView) findViewById(R.id.deliveryadresslist);
        faturaAdressList=(ListView) findViewById(R.id.invoiceadresslist);

        Toolbar toolbar=( Toolbar) findViewById(R.id.toolbar);
        TextView title=(TextView) toolbar.findViewById(R.id.eshop);
        title.setText("MY ADDRESSES");
        ImageView backButton=(ImageView) toolbar.findViewById(R.id.backImage);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences prefs =getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        kullaniciid = prefs.getString("id", "0");


        TabHost tabHost=(TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec mSpec=tabHost.newTabSpec("Delivery");
        mSpec.setContent(R.id.deliveryadress);
        mSpec.setIndicator("My Delivery Addresses");
        tabHost.addTab(mSpec);

        mSpec=tabHost.newTabSpec("Invoice");
        mSpec.setContent(R.id.invoiceadress);
        mSpec.setIndicator("My Billing Addresses");
        tabHost.addTab(mSpec);

        adresCek("Delivery");
        adresCek("Invoice");




        addDeliveryadress.setText(getResources().getString(R.string.adressaddtext,"Invoice"));
        addInvoiceadress.setText(getResources().getString(R.string.adressaddtext,"Invoice"));

        addDeliveryadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityGec("Delivery");
            }
        });
        addInvoiceadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityGec("Invoice");
            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("Delivery")){
                    deliveryadress.setText(getResources().getString(R.string.adresstext,String.valueOf(teslimatAdresCount),"Delivery"));
                }else {
                    invoiceadress.setText(getResources().getString(R.string.adresstext,String.valueOf(faturaAdresCount),"Invoice"));
                }
            }
        });
    }

    protected void adresCek(final String adresTip) {
        BeanTeslimat=new ArrayList<>();
        BeanFatura=new ArrayList<>();
        new getData(MemberAdressActivity.this){
            @Override
            protected void onPostExecute(String s) {
                if(s!=null){
                    try {
                        JSONArray jsonArray=new JSONArray(s);
                        if(adresTip.equalsIgnoreCase("Delivery")) teslimatAdresCount=jsonArray.length();
                        else faturaAdresCount=jsonArray.length();
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            if(adresTip.equalsIgnoreCase("Delivery"))
                            {
                                AdressBeanlist adressBeanlist=new AdressBeanlist(jsonObject.getString("Id"),jsonObject.getString("UserName"),jsonObject.getString("UserSurname"),jsonObject.getString("UserTelephone"),jsonObject.getString("AdressCountry"),
                                                    jsonObject.getString("AdressCity"),jsonObject.getString("AdressDistrict"),jsonObject.getString("OpenAdress"),jsonObject.getString("AdressName"),"","","",adresTip);
                                BeanTeslimat.add(adressBeanlist);

                            }else{
                                AdressBeanlist adressBeanlist=new AdressBeanlist(jsonObject.getString("Id"),jsonObject.getString("UserName"),jsonObject.getString("UserSurname"),jsonObject.getString("UserTelephone"),jsonObject.getString("AdressCountry"),
                                        jsonObject.getString("AdressCity"),jsonObject.getString("AdressDistrict"),jsonObject.getString("OpenAdress"),jsonObject.getString("AdressName"),jsonObject.getString("CompanyName"),jsonObject.getString("CompanyTaxName"),jsonObject.getString("CompanyTaxNumber"),adresTip);
                                BeanFatura.add(adressBeanlist);

                            }

                        }
                        AdressBaseAdapter adapter;
                        //ArrayAdapter<String> details=new ArrayAdapter<String>(MemberAdressActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,detaylar);
                        if((adresTip.equalsIgnoreCase("Delivery"))){
                            adapter=new AdressBaseAdapter(BeanTeslimat,MemberAdressActivity.this);
                            teslimatAdressList.setAdapter(adapter);
                        }
                        else{
                            adapter=new AdressBaseAdapter(BeanFatura,MemberAdressActivity.this);
                            faturaAdressList.setAdapter(adapter);
                        }
                        deliveryadress.setText(getResources().getString(R.string.adresstext,String.valueOf(teslimatAdresCount),"Delivery"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                dialog.dismiss();
                super.onPostExecute(s);
            }
        }.execute(MainActivity.server_url+"homepage.php","Adres",adresTip+"-"+kullaniciid);
    }


    private void activityGec(String adressName) {
        Intent gec=new Intent(MemberAdressActivity.this,addAdressActivity.class);
        gec.putExtra("addressName",adressName);
        startActivity(gec);
    }
}
