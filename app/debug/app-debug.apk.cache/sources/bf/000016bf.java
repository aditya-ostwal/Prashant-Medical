package com.shoeARstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class ProductListActivity extends AppCompatActivity {
    private ArrayList<Beanlist> Bean;
    private ListviewAdapter baseAdapter;
    private ExpandableHeightGridView gridview;
    ImageView gridviewicon;
    String kullaniciid;
    private ExpandableHeightListView listview;
    ImageView listviewicon;
    SharedPreferences prefs;
    private Spinner sirala;
    private String maincategoryid = "yok";
    private String subcategoryid = "yok";
    private String subsubcategoryid = "yok";
    Comparator<Beanlist> sortByDownPrice = new Comparator<Beanlist>() { // from class: com.shoeARstore.ProductListActivity.11
        @Override // java.util.Comparator
        public int compare(Beanlist lhs, Beanlist rhs) {
            Float fiyat1 = Float.valueOf(Float.parseFloat(lhs.getPrice()));
            Float fiyat2 = Float.valueOf(Float.parseFloat(rhs.getPrice()));
            if (fiyat1.floatValue() <= fiyat2.floatValue()) {
                return ProductListActivity.this.degistir(lhs, rhs);
            }
            return -1;
        }
    };
    Comparator<Beanlist> sortByUpPrice = new Comparator<Beanlist>() { // from class: com.shoeARstore.ProductListActivity.12
        @Override // java.util.Comparator
        public int compare(Beanlist lhs, Beanlist rhs) {
            Float fiyat1 = Float.valueOf(Float.parseFloat(lhs.getPrice()));
            Float fiyat2 = Float.valueOf(Float.parseFloat(rhs.getPrice()));
            if (fiyat1.floatValue() > fiyat2.floatValue()) {
                return ProductListActivity.this.degistir(lhs, rhs);
            }
            return -1;
        }
    };
    Comparator<Beanlist> sortByComment = new Comparator<Beanlist>() { // from class: com.shoeARstore.ProductListActivity.13
        @Override // java.util.Comparator
        public int compare(Beanlist lhs, Beanlist rhs) {
            int yorumsayi1 = Integer.parseInt(lhs.getRatingtext());
            int yorumsayi2 = Integer.parseInt(rhs.getRatingtext());
            if (yorumsayi1 <= yorumsayi2) {
                return ProductListActivity.this.degistir(lhs, rhs);
            }
            return -1;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r7v5, types: [com.shoeARstore.ProductListActivity$4] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mtel.shoe.R.layout.activity_productlist);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", 0);
        this.prefs = sharedPreferences;
        this.kullaniciid = sharedPreferences.getString("id", "0");
        Toolbar toolbar = (Toolbar) findViewById(com.mtel.shoe.R.id.toolbar);
        ImageView backbutton = (ImageView) toolbar.findViewById(com.mtel.shoe.R.id.buttonexit);
        backbutton.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.ProductListActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ProductListActivity.this.finish();
            }
        });
        this.Bean = new ArrayList<>();
        this.sirala = (Spinner) findViewById(com.mtel.shoe.R.id.sirala);
        if (getIntent().getExtras() != null) {
            String[] idler = getIntent().getExtras().getString("categoryid").split("-");
            this.maincategoryid = idler[0];
            this.subcategoryid = idler[1];
            this.subsubcategoryid = idler[2];
        }
        ArrayAdapter<String> siralaAdapter = new ArrayAdapter<String>(this, com.mtel.shoe.R.layout.siralaitemlayout, Arrays.asList(getResources().getStringArray(com.mtel.shoe.R.array.siralaitemproduct))) { // from class: com.shoeARstore.ProductListActivity.2
            @Override // android.widget.BaseAdapter, android.widget.ListAdapter
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                }
                return true;
            }

            @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(-7829368);
                } else {
                    tv.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }
                return view;
            }
        };
        siralaAdapter.setDropDownViewResource(com.mtel.shoe.R.layout.siralaitemlayout);
        this.sirala.setAdapter((SpinnerAdapter) siralaAdapter);
        this.sirala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.shoeARstore.ProductListActivity.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                if (selected.equals("Fiyata Göre Azalan")) {
                    ProductListActivity.this.adapterSirala("downprice");
                } else if (selected.equals("Fiyata Göre Artan")) {
                    ProductListActivity.this.adapterSirala("upprice");
                } else if (selected.equals("Yorum Sayısı")) {
                    ProductListActivity.this.adapterSirala("comment");
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        new getData(this) { // from class: com.shoeARstore.ProductListActivity.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String s) {
                JSONObject jsonObject;
                JSONArray jsonArray;
                AnonymousClass4 anonymousClass4 = this;
                String str = "FirstPrice";
                if (s != null) {
                    ArrayList<String> image = new ArrayList<>();
                    ArrayList<String> name = new ArrayList<>();
                    ArrayList<String> price = new ArrayList<>();
                    ArrayList<String> cutprice = new ArrayList<>();
                    ArrayList<String> discount = new ArrayList<>();
                    ArrayList<String> ratingtext = new ArrayList<>();
                    ArrayList<String> rating = new ArrayList<>();
                    ArrayList<String> id = new ArrayList<>();
                    ArrayList<String> favorites = new ArrayList<>();
                    try {
                        JSONArray jsonArray2 = new JSONArray(s);
                        int i = 0;
                        while (i < jsonArray2.length()) {
                            try {
                                try {
                                    jsonObject = jsonArray2.getJSONObject(i);
                                    jsonArray = jsonArray2;
                                } catch (JSONException e) {
                                    e = e;
                                    anonymousClass4 = this;
                                }
                            } catch (JSONException e2) {
                                e = e2;
                            }
                            try {
                                String path = jsonObject.getString("resim").replace("\\", "/");
                                image.add(MainActivity.server_url + path);
                                name.add(jsonObject.getString("ProductName"));
                                price.add(jsonObject.getString("LastPrice"));
                                cutprice.add(jsonObject.getString(str));
                                int sonfiyat = (int) Float.parseFloat(jsonObject.getString(str));
                                int ilkfiyat = (int) Float.parseFloat(jsonObject.getString("LastPrice"));
                                int fark = sonfiyat - ilkfiyat;
                                Integer indirim = Integer.valueOf(Integer.parseInt(String.valueOf((fark * 100) / sonfiyat)));
                                String str2 = str;
                                discount.add("%" + String.valueOf(indirim));
                                ratingtext.add(jsonObject.getString("Comment"));
                                rating.add(jsonObject.getString("Rating"));
                                id.add(jsonObject.getString("StockCode"));
                                favorites.add(jsonObject.getString("Favorites"));
                                i++;
                                anonymousClass4 = this;
                                jsonArray2 = jsonArray;
                                str = str2;
                            } catch (JSONException e3) {
                                e = e3;
                                anonymousClass4 = this;
                                e.printStackTrace();
                                anonymousClass4.dialog.dismiss();
                                super.onPostExecute((AnonymousClass4) s);
                            }
                        }
                        anonymousClass4 = this;
                        try {
                            try {
                                ProductListActivity.this.gonder(image, name, price, cutprice, discount, ratingtext, rating, id, favorites);
                            } catch (JSONException e4) {
                                e = e4;
                                e.printStackTrace();
                                anonymousClass4.dialog.dismiss();
                                super.onPostExecute((AnonymousClass4) s);
                            }
                        } catch (JSONException e5) {
                            e = e5;
                        }
                    } catch (JSONException e6) {
                        e = e6;
                    }
                }
                anonymousClass4.dialog.dismiss();
                super.onPostExecute((AnonymousClass4) s);
            }
        }.execute(new String[]{MainActivity.server_url + "productslist.php", this.kullaniciid + "-" + this.maincategoryid, this.subcategoryid + "-" + this.subsubcategoryid});
        this.gridview = (ExpandableHeightGridView) findViewById(com.mtel.shoe.R.id.gridview);
        ListviewAdapter listviewAdapter = new ListviewAdapter(this, this, this.Bean, com.mtel.shoe.R.layout.grid_productlist) { // from class: com.shoeARstore.ProductListActivity.5
        };
        this.baseAdapter = listviewAdapter;
        this.gridview.setAdapter((ListAdapter) listviewAdapter);
        this.listview = (ExpandableHeightListView) findViewById(com.mtel.shoe.R.id.listview);
        ListviewAdapter listviewAdapter2 = new ListviewAdapter(this, this, this.Bean, com.mtel.shoe.R.layout.listview_productlist) { // from class: com.shoeARstore.ProductListActivity.6
        };
        this.baseAdapter = listviewAdapter2;
        this.listview.setAdapter((ListAdapter) listviewAdapter2);
        this.gridviewicon = (ImageView) findViewById(com.mtel.shoe.R.id.gridviewicon);
        this.listviewicon = (ImageView) findViewById(com.mtel.shoe.R.id.listviewicon);
        this.gridviewicon.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.ProductListActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ProductListActivity.this.listviewicon.setVisibility(0);
                ProductListActivity.this.gridviewicon.setVisibility(8);
                ProductListActivity.this.listview.setVisibility(0);
                ProductListActivity.this.gridview.setVisibility(8);
            }
        });
        this.listviewicon.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.ProductListActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ProductListActivity.this.listviewicon.setVisibility(8);
                ProductListActivity.this.gridviewicon.setVisibility(0);
                ProductListActivity.this.listview.setVisibility(8);
                ProductListActivity.this.gridview.setVisibility(0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adapterSirala(String kosul) {
        if (kosul.equals("upprice")) {
            Collections.sort(this.Bean, this.sortByUpPrice);
        } else if (kosul.equals("downprice")) {
            Collections.sort(this.Bean, this.sortByDownPrice);
        } else if (kosul.equals("comment")) {
            Collections.sort(this.Bean, this.sortByComment);
        }
        ListviewAdapter listviewAdapter = new ListviewAdapter(this, this, this.Bean, com.mtel.shoe.R.layout.listview_productlist) { // from class: com.shoeARstore.ProductListActivity.9
        };
        this.baseAdapter = listviewAdapter;
        this.listview.setAdapter((ListAdapter) listviewAdapter);
        ListviewAdapter listviewAdapter2 = new ListviewAdapter(this, this, this.Bean, com.mtel.shoe.R.layout.grid_productlist) { // from class: com.shoeARstore.ProductListActivity.10
        };
        this.baseAdapter = listviewAdapter2;
        this.gridview.setAdapter((ListAdapter) listviewAdapter2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int degistir(Beanlist lhs, Beanlist rhs) {
        lhs.getTitle().compareTo(rhs.getTitle());
        lhs.getCutprice().compareTo(rhs.getCutprice());
        lhs.getDiscount().compareTo(rhs.getDiscount());
        lhs.getId().compareTo(rhs.getId());
        lhs.getImage().compareTo(rhs.getImage());
        lhs.getPrice().compareTo(rhs.getPrice());
        String.valueOf(lhs.getRating()).compareTo(String.valueOf(rhs.getRating()));
        lhs.getRatingtext().compareTo(rhs.getRatingtext());
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gonder(ArrayList<String> image, ArrayList<String> title, ArrayList<String> price, ArrayList<String> cutprice, ArrayList<String> discount, ArrayList<String> ratingtext, ArrayList<String> rating, ArrayList<String> id, ArrayList<String> favorites) {
        this.Bean = new ArrayList<>();
        for (int i = 0; i < image.size(); i++) {
            Beanlist BeanlistList = new Beanlist(image.get(i), title.get(i), price.get(i), cutprice.get(i), discount.get(i), ratingtext.get(i), Float.parseFloat(rating.get(i)), id.get(i), favorites.get(i));
            this.Bean.add(BeanlistList);
        }
        ListviewAdapter listviewAdapter = new ListviewAdapter(this, this, this.Bean, com.mtel.shoe.R.layout.listview_productlist) { // from class: com.shoeARstore.ProductListActivity.14
        };
        this.baseAdapter = listviewAdapter;
        this.listview.setAdapter((ListAdapter) listviewAdapter);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.shoeARstore.ProductListActivity.15
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id2) {
                TextView textView = (TextView) view.findViewById(com.mtel.shoe.R.id.id);
                Intent gec = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                gec.putExtra("StockCode", textView.getText().toString());
                ProductListActivity.this.startActivity(gec);
            }
        });
        ListviewAdapter listviewAdapter2 = new ListviewAdapter(this, this, this.Bean, com.mtel.shoe.R.layout.grid_productlist) { // from class: com.shoeARstore.ProductListActivity.16
        };
        this.baseAdapter = listviewAdapter2;
        this.gridview.setAdapter((ListAdapter) listviewAdapter2);
        this.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.shoeARstore.ProductListActivity.17
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id2) {
                TextView textView = (TextView) view.findViewById(com.mtel.shoe.R.id.id);
                Intent gec = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                gec.putExtra("StockCode", textView.getText().toString());
                ProductListActivity.this.startActivity(gec);
            }
        });
    }
}