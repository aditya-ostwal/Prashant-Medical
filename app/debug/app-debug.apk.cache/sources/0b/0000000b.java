package adresspage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import com.mtel.shoe.R;
import com.shoeARstore.MainActivity;
import com.shoeARstore.getData;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class addAdressActivity extends AppCompatActivity {
    EditText acikadres;
    EditText ad;
    LinearLayout adresKaydet;
    EditText adresad;
    String adressAd;
    RadioGroup faturaTip;
    EditText firmaad;
    EditText firmavergidairesi;
    EditText firmavergino;
    Spinner il;
    Spinner ilce;
    String kullaniciid;
    LinearLayout layoutFatura;
    LinearLayout layoutFaturaKurumsal;
    EditText soyad;
    ArrayAdapter<CharSequence> spinnerAdapter;
    EditText telefon;
    TextView textTitle;
    Spinner ulke;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_adress);
        this.adresKaydet = (LinearLayout) findViewById(R.id.save);
        this.layoutFatura = (LinearLayout) findViewById(R.id.layoutFatura);
        this.layoutFaturaKurumsal = (LinearLayout) findViewById(R.id.layoutFaturaKurumsal);
        this.faturaTip = (RadioGroup) findViewById(R.id.radioFaturaTip);
        this.ad = (EditText) findViewById(R.id.firstname);
        this.soyad = (EditText) findViewById(R.id.lastname);
        this.telefon = (EditText) findViewById(R.id.phone);
        this.acikadres = (EditText) findViewById(R.id.openAdressLine);
        this.adresad = (EditText) findViewById(R.id.adressName);
        this.firmaad = (EditText) findViewById(R.id.companyname);
        this.firmavergidairesi = (EditText) findViewById(R.id.companytaxname);
        this.firmavergino = (EditText) findViewById(R.id.companytaxnumber);
        this.ulke = (Spinner) findViewById(R.id.spinnerCountry);
        this.il = (Spinner) findViewById(R.id.spinnerCity);
        this.ilce = (Spinner) findViewById(R.id.spinnerDistrict);
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this, R.array.ulkeler, 17367048);
        this.spinnerAdapter = createFromResource;
        createFromResource.setDropDownViewResource(17367049);
        this.ulke.setAdapter((SpinnerAdapter) this.spinnerAdapter);
        ArrayAdapter<CharSequence> createFromResource2 = ArrayAdapter.createFromResource(this, R.array.iller, 17367048);
        this.spinnerAdapter = createFromResource2;
        this.il.setAdapter((SpinnerAdapter) createFromResource2);
        this.ilce.setAdapter((SpinnerAdapter) this.spinnerAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.eshop);
        title.setText("Add Address");
        ImageView backButton = (ImageView) toolbar.findViewById(R.id.backImage);
        backButton.setOnClickListener(new View.OnClickListener() { // from class: adresspage.addAdressActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                addAdressActivity.this.finish();
            }
        });
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("login", 0);
        this.kullaniciid = prefs.getString("id", "0");
        this.textTitle = (TextView) findViewById(R.id.textTitle);
        if (getIntent().getExtras() != null) {
            String string = getIntent().getExtras().getString("addressName");
            this.adressAd = string;
            if (string.equalsIgnoreCase("Invoice")) {
                this.layoutFatura.setVisibility(0);
            }
            this.textTitle.setText(getResources().getString(R.string.adressAdd, this.adressAd));
        }
        this.faturaTip.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: adresspage.addAdressActivity.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioFaturaBireysel /* 2131296775 */:
                        addAdressActivity.this.layoutFaturaKurumsal.setVisibility(8);
                        return;
                    case R.id.radioFaturaKurumsal /* 2131296776 */:
                        addAdressActivity.this.layoutFaturaKurumsal.setVisibility(0);
                        return;
                    default:
                        return;
                }
            }
        });
        this.adresKaydet.setOnClickListener(new View.OnClickListener() { // from class: adresspage.addAdressActivity.3
            /* JADX WARN: Type inference failed for: r0v0, types: [adresspage.addAdressActivity$3$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                new getData(addAdressActivity.this) { // from class: adresspage.addAdressActivity.3.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Type inference failed for: r3v81 */
                    @Override // com.shoeARstore.getData, android.os.AsyncTask
                    public String doInBackground(String... params) {
                        Throwable th;
                        BufferedInputStream is;
                        BufferedInputStream bufferedInputStream;
                        String kullaniciid;
                        String login_url = params[0];
                        String ad = params[1];
                        String soyad = params[2];
                        String telefon = params[3];
                        String acikadres = params[4];
                        String ulke = params[5];
                        String il = params[6];
                        String ilce = params[7];
                        String adresad = params[8];
                        String firmaad = params[9];
                        String firmavergi = params[10];
                        String firmavergino = params[11];
                        String adrestip = params[12];
                        String adrestip2 = params[13];
                        try {
                            try {
                                try {
                                    URL url = new URL(login_url);
                                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                                    try {
                                        httpURLConnection.setRequestMethod("POST");
                                        httpURLConnection.setDoOutput(true);
                                        httpURLConnection.setDoInput(true);
                                        OutputStream outputStream = httpURLConnection.getOutputStream();
                                        try {
                                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                                            StringBuilder append = new StringBuilder().append(URLEncoder.encode("ad", "UTF-8")).append("=").append(URLEncoder.encode(ad, "UTF-8")).append("&").append(URLEncoder.encode("soyad", "UTF-8")).append("=").append(URLEncoder.encode(soyad, "UTF-8")).append("&").append(URLEncoder.encode("telefon", "UTF-8")).append("=").append(URLEncoder.encode(telefon, "UTF-8")).append("&").append(URLEncoder.encode("acikadres", "UTF-8")).append("=").append(URLEncoder.encode(acikadres, "UTF-8")).append("&").append(URLEncoder.encode("ulke", "UTF-8")).append("=").append(URLEncoder.encode(ulke, "UTF-8")).append("&").append(URLEncoder.encode("il", "UTF-8")).append("=").append(URLEncoder.encode(il, "UTF-8")).append("&").append(URLEncoder.encode("ilce", "UTF-8")).append("=").append(URLEncoder.encode(ilce, "UTF-8")).append("&").append(URLEncoder.encode("adresad", "UTF-8")).append("=").append(URLEncoder.encode(adresad, "UTF-8")).append("&").append(URLEncoder.encode("firmaad", "UTF-8")).append("=").append(URLEncoder.encode(firmaad, "UTF-8")).append("&").append(URLEncoder.encode("firmavergi", "UTF-8")).append("=").append(URLEncoder.encode(firmavergi, "UTF-8")).append("&").append(URLEncoder.encode("firmavergino", "UTF-8")).append("=");
                                            try {
                                                String ad2 = URLEncoder.encode(firmavergino, "UTF-8");
                                                StringBuilder append2 = append.append(ad2).append("&").append(URLEncoder.encode("adrestip", "UTF-8")).append("=");
                                                try {
                                                    String firmavergino2 = URLEncoder.encode(adrestip, "UTF-8");
                                                    kullaniciid = adrestip2;
                                                    try {
                                                        String post_data = append2.append(firmavergino2).append("&").append(URLEncoder.encode("kullaniciid", "UTF-8")).append("=").append(URLEncoder.encode(kullaniciid, "UTF-8")).toString();
                                                        bufferedWriter.write(post_data);
                                                        bufferedWriter.flush();
                                                        bufferedWriter.close();
                                                        outputStream.close();
                                                        bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                                                    } catch (MalformedURLException e) {
                                                        e = e;
                                                        bufferedInputStream = null;
                                                    } catch (IOException e2) {
                                                        e = e2;
                                                        bufferedInputStream = null;
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        is = null;
                                                    }
                                                } catch (MalformedURLException e3) {
                                                    e = e3;
                                                    bufferedInputStream = null;
                                                } catch (IOException e4) {
                                                    e = e4;
                                                    bufferedInputStream = null;
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    is = null;
                                                }
                                                try {
                                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
                                                    StringBuffer sb = new StringBuffer();
                                                    while (true) {
                                                        String line = bufferedReader.readLine();
                                                        if (line == null) {
                                                            break;
                                                        }
                                                        String kullaniciid2 = kullaniciid;
                                                        try {
                                                            StringBuffer sb2 = sb;
                                                            sb2.append(line + "\n");
                                                            sb = sb2;
                                                            kullaniciid = kullaniciid2;
                                                        } catch (MalformedURLException e5) {
                                                            e = e5;
                                                            e.printStackTrace();
                                                            if (bufferedInputStream != null) {
                                                                bufferedInputStream.close();
                                                                return null;
                                                            }
                                                            return null;
                                                        } catch (IOException e6) {
                                                            e = e6;
                                                            e.printStackTrace();
                                                            if (bufferedInputStream != null) {
                                                                bufferedInputStream.close();
                                                                return null;
                                                            }
                                                            return null;
                                                        }
                                                    }
                                                    String stringBuffer = sb.toString();
                                                    try {
                                                        bufferedInputStream.close();
                                                    } catch (IOException e7) {
                                                        e7.printStackTrace();
                                                    }
                                                    return stringBuffer;
                                                } catch (MalformedURLException e8) {
                                                    e = e8;
                                                } catch (IOException e9) {
                                                    e = e9;
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    is = bufferedInputStream;
                                                    if (is != null) {
                                                        try {
                                                            is.close();
                                                        } catch (IOException e10) {
                                                            e10.printStackTrace();
                                                        }
                                                    }
                                                    throw th;
                                                }
                                            } catch (MalformedURLException e11) {
                                                e = e11;
                                                bufferedInputStream = null;
                                            } catch (IOException e12) {
                                                e = e12;
                                                bufferedInputStream = null;
                                            } catch (Throwable th5) {
                                                th = th5;
                                                is = null;
                                            }
                                        } catch (MalformedURLException e13) {
                                            e = e13;
                                            bufferedInputStream = null;
                                        } catch (IOException e14) {
                                            e = e14;
                                            bufferedInputStream = null;
                                        } catch (Throwable th6) {
                                            th = th6;
                                            is = null;
                                        }
                                    } catch (MalformedURLException e15) {
                                        e = e15;
                                        bufferedInputStream = null;
                                    } catch (IOException e16) {
                                        e = e16;
                                        bufferedInputStream = null;
                                    } catch (Throwable th7) {
                                        th = th7;
                                        is = null;
                                    }
                                } catch (MalformedURLException e17) {
                                    e = e17;
                                    bufferedInputStream = null;
                                } catch (IOException e18) {
                                    e = e18;
                                    bufferedInputStream = null;
                                } catch (Throwable th8) {
                                    th = th8;
                                    is = null;
                                }
                            } catch (Throwable th9) {
                                th = th9;
                                is = adrestip2;
                            }
                        } catch (IOException e19) {
                            e19.printStackTrace();
                            return null;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(String s) {
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            if (((JSONObject) jsonArray.get(0)).getString(NotificationCompat.CATEGORY_STATUS).equals("1")) {
                                Toast.makeText(addAdressActivity.this, "Address Successfully Saved...", 0).show();
                                addAdressActivity.this.activityGec();
                            } else {
                                Toast.makeText(addAdressActivity.this, "Address Could Not Be Saved...Please Check The Information You Have Entered!!!", 0).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        this.dialog.dismiss();
                        super.onPostExecute((AnonymousClass1) s);
                    }
                }.execute(new String[]{MainActivity.server_url + "addAdress.php", addAdressActivity.this.ad.getText().toString(), addAdressActivity.this.soyad.getText().toString(), addAdressActivity.this.telefon.getText().toString(), addAdressActivity.this.acikadres.getText().toString(), addAdressActivity.this.ulke.getSelectedItem().toString(), addAdressActivity.this.il.getSelectedItem().toString(), addAdressActivity.this.ilce.getSelectedItem().toString(), addAdressActivity.this.adresad.getText().toString(), addAdressActivity.this.firmaad.getText().toString(), addAdressActivity.this.firmavergidairesi.getText().toString(), addAdressActivity.this.firmavergino.getText().toString(), addAdressActivity.this.adressAd, addAdressActivity.this.kullaniciid});
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activityGec() {
        Intent gec = new Intent(this, MemberAdressActivity.class);
        startActivity(gec);
    }
}