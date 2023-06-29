package orderspage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.mtel.shoe.R;
import com.shoeARstore.ExpandableHeightListView;
import com.shoeARstore.MainActivity;
import com.shoeARstore.getData;
import java.util.ArrayList;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OrderDetailActivity extends AppCompatActivity {
    private ArrayList<BeanclassOrderDetail> Bean;
    private ListBaseAdapterOrder baseAdapter;
    String kullaniciid;
    private ExpandableHeightListView listview;
    private String orderno;
    SharedPreferences prefs;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r3v14, types: [orderspage.OrderDetailActivity$2] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.eshop);
        ImageView backButton = (ImageView) toolbar.findViewById(R.id.backImage);
        backButton.setOnClickListener(new View.OnClickListener() { // from class: orderspage.OrderDetailActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                OrderDetailActivity.this.finish();
            }
        });
        this.Bean = new ArrayList<>();
        this.listview = (ExpandableHeightListView) findViewById(R.id.listview);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", 0);
        this.prefs = sharedPreferences;
        this.kullaniciid = sharedPreferences.getString("id", "-1");
        if (getIntent().getExtras() != null) {
            this.orderno = getIntent().getExtras().getString("orderno");
            title.setText("ORDER DETAIL:" + this.orderno);
        } else {
            this.orderno = null;
        }
        if (this.orderno != null && !this.kullaniciid.equals("-1")) {
            new getData(this) { // from class: orderspage.OrderDetailActivity.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(String s) {
                    if (!s.equals("") && s != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            Random rnd = new Random(100L);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                BeanclassOrderDetail beanclassOrderDetail = new BeanclassOrderDetail(jsonObject.getString("ProductImagePath"), jsonObject.getString("OrderComplatedDate"), jsonObject.getString("ProductName"), jsonObject.getString("Piece"), jsonObject.getString("OrderComplatedDate"), jsonObject.getString("OrdersId"), rnd.nextInt(10) * 5);
                                OrderDetailActivity.this.Bean.add(beanclassOrderDetail);
                            }
                            OrderDetailActivity orderDetailActivity = OrderDetailActivity.this;
                            OrderDetailActivity orderDetailActivity2 = OrderDetailActivity.this;
                            orderDetailActivity.baseAdapter = new ListBaseAdapterOrder(orderDetailActivity2, orderDetailActivity2.Bean);
                            OrderDetailActivity.this.listview.setAdapter((ListAdapter) OrderDetailActivity.this.baseAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(OrderDetailActivity.this, "Mistake!!!", 0).show();
                    }
                    this.dialog.dismiss();
                    super.onPostExecute((AnonymousClass2) s);
                }
            }.execute(new String[]{MainActivity.server_url + "orderdetail.php", this.kullaniciid, this.orderno});
        }
    }
}