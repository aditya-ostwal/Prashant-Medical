package yorumlarsayfasi;

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
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mtel.shoe.R;
import com.shoeARstore.MainActivity;
import com.shoeARstore.getData;
import com.shoeARstore.localdatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class YorumlarActivity extends AppCompatActivity {
    private ArrayList<BeanclassReview> Bean;
    private BaseAdapterReview baseAdapter;
    private String kullaniciid;
    private ExpandableHeightListView listview;
    private Spinner sirala;
    private String stockcode;
    Comparator<BeanclassReview> sortByRate = new Comparator<BeanclassReview>() { // from class: yorumlarsayfasi.YorumlarActivity.6
        @Override // java.util.Comparator
        public int compare(BeanclassReview lhs, BeanclassReview rhs) {
            Float rate1 = Float.valueOf(Float.parseFloat(lhs.getRateno().substring(0, lhs.getRateno().indexOf("/"))));
            Float rate2 = Float.valueOf(Float.parseFloat(rhs.getRateno().substring(0, rhs.getRateno().indexOf("/"))));
            if (rate1.floatValue() >= rate2.floatValue()) {
                return YorumlarActivity.this.degistir(lhs, rhs);
            }
            return -1;
        }
    };
    Comparator<BeanclassReview> sortByDate = new Comparator<BeanclassReview>() { // from class: yorumlarsayfasi.YorumlarActivity.7
        @Override // java.util.Comparator
        public int compare(BeanclassReview lhs, BeanclassReview rhs) {
            String tarih1 = lhs.getDate();
            String tarih2 = rhs.getDate();
            Calendar date1 = new GregorianCalendar();
            date1.set(Integer.parseInt(tarih1.split("-")[0]), Integer.parseInt(tarih1.split("-")[1]), Integer.parseInt(tarih1.split("-")[2]));
            Calendar date2 = new GregorianCalendar();
            date2.set(Integer.parseInt(tarih2.split("-")[0]), Integer.parseInt(tarih2.split("-")[1]), Integer.parseInt(tarih2.split("-")[2]));
            if (date1.getTimeInMillis() < date2.getTimeInMillis()) {
                return YorumlarActivity.this.degistir(lhs, rhs);
            }
            return -1;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r6v11, types: [yorumlarsayfasi.YorumlarActivity$5] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("login", 0);
        this.kullaniciid = prefs.getString("id", "0");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView backButton = (ImageView) toolbar.findViewById(R.id.backImage);
        backButton.setOnClickListener(new View.OnClickListener() { // from class: yorumlarsayfasi.YorumlarActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                YorumlarActivity.this.finish();
            }
        });
        this.listview = (ExpandableHeightListView) findViewById(R.id.listview);
        this.sirala = (Spinner) findViewById(R.id.sirala);
        FloatingActionButton addComment = (FloatingActionButton) findViewById(R.id.fab);
        addComment.setOnClickListener(new View.OnClickListener() { // from class: yorumlarsayfasi.YorumlarActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (!YorumlarActivity.this.kullaniciid.equals("0")) {
                    if (YorumlarActivity.this.stockcode != null) {
                        Intent gec = new Intent(YorumlarActivity.this, AddCommentActivity.class);
                        gec.putExtra(localdatabase.KEY_ROW_STOCKCODE, YorumlarActivity.this.stockcode);
                        YorumlarActivity.this.startActivity(gec);
                        return;
                    }
                    return;
                }
                Toast.makeText(YorumlarActivity.this, "Please sign in to post a comment!!!", 0).show();
            }
        });
        ArrayAdapter<String> siralaAdapter = new ArrayAdapter<String>(this, R.layout.siralaitemlayout, Arrays.asList(getResources().getStringArray(R.array.siraliitemcomment))) { // from class: yorumlarsayfasi.YorumlarActivity.3
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
        siralaAdapter.setDropDownViewResource(R.layout.siralaitemlayout);
        this.sirala.setAdapter((SpinnerAdapter) siralaAdapter);
        this.sirala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: yorumlarsayfasi.YorumlarActivity.4
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                if (selected.equals("By Rating")) {
                    YorumlarActivity.this.adapterSirala("rate");
                } else if (selected.equals("By Date")) {
                    YorumlarActivity.this.adapterSirala("tarih");
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        this.Bean = new ArrayList<>();
        if (getIntent().getExtras() != null) {
            this.stockcode = getIntent().getExtras().getString(localdatabase.KEY_ROW_STOCKCODE);
            new getData(this) { // from class: yorumlarsayfasi.YorumlarActivity.5
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(String s) {
                    String[] split;
                    if (s != null) {
                        try {
                            ArrayList<String> Rate = new ArrayList<>();
                            ArrayList<String> Date = new ArrayList<>();
                            ArrayList<String> UserName = new ArrayList<>();
                            ArrayList<String> Description = new ArrayList<>();
                            JSONArray jsonArray = new JSONArray(s);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (i < jsonArray.length() - 1) {
                                    Rate.add(jsonObject.getString("Points"));
                                    Date.add(jsonObject.getString("CommentDate"));
                                    Description.add(jsonObject.getString("Review"));
                                }
                                if (i == jsonArray.length() - 1) {
                                    for (String name : jsonObject.getString("Name").split(",")) {
                                        UserName.add(name);
                                    }
                                }
                            }
                            YorumlarActivity.this.gonder(Rate, Date, Description, UserName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    this.dialog.dismiss();
                    super.onPostExecute((AnonymousClass5) s);
                }
            }.execute(new String[]{MainActivity.server_url + "comments.php", "comments", this.stockcode});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int degistir(BeanclassReview lhs, BeanclassReview rhs) {
        lhs.getTitle().compareTo(rhs.getTitle());
        lhs.getRateno().compareTo(rhs.getRateno());
        lhs.getDate().compareTo(rhs.getDate());
        lhs.getUsername().compareTo(rhs.getUsername());
        lhs.getLike().compareTo(rhs.getLike());
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adapterSirala(String kosul) {
        if (kosul.equals("rate")) {
            Collections.sort(this.Bean, this.sortByRate);
        } else if (kosul.equals("tarih")) {
            Collections.sort(this.Bean, this.sortByDate);
        }
        BaseAdapterReview baseAdapterReview = new BaseAdapterReview(this, this.Bean) { // from class: yorumlarsayfasi.YorumlarActivity.8
        };
        this.baseAdapter = baseAdapterReview;
        baseAdapterReview.notifyDataSetChanged();
        this.listview.setAdapter((ListAdapter) this.baseAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gonder(ArrayList<String> rate, ArrayList<String> date, ArrayList<String> description, ArrayList<String> userName) {
        this.Bean = new ArrayList<>();
        for (int i = 0; i < rate.size(); i++) {
            BeanclassReview bean = new BeanclassReview("Güzel", rate.get(i) + "/5", date.get(i), userName.get(i), description.get(i), "12");
            this.Bean.add(bean);
        }
        BaseAdapterReview baseAdapterReview = new BaseAdapterReview(this, this.Bean) { // from class: yorumlarsayfasi.YorumlarActivity.9
        };
        this.baseAdapter = baseAdapterReview;
        this.listview.setAdapter((ListAdapter) baseAdapterReview);
    }
}