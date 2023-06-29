package com.shoeARstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class SignUp extends AppCompatActivity {
    CheckBox cSozlesme;
    EditText drugnumber;
    EditText eFname;
    EditText eLname;
    EditText eMail;
    EditText ePass;
    SharedPreferences sharedPreferences;
    TextView tSignUp;
    TextView tSignin;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mtel.shoe.R.layout.signup);
        this.tSignin = (TextView) findViewById(com.mtel.shoe.R.id.signin);
        this.tSignUp = (TextView) findViewById(com.mtel.shoe.R.id.signin1);
        this.eFname = (EditText) findViewById(com.mtel.shoe.R.id.fname);
        this.eLname = (EditText) findViewById(com.mtel.shoe.R.id.lname);
        this.eMail = (EditText) findViewById(com.mtel.shoe.R.id.email1);
        this.ePass = (EditText) findViewById(com.mtel.shoe.R.id.password);
        this.drugnumber = (EditText) findViewById(com.mtel.shoe.R.id.druglicense);
        this.cSozlesme = (CheckBox) findViewById(com.mtel.shoe.R.id.sozlesme);
        this.tSignin.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.SignUp.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent it = new Intent(SignUp.this, SignIn.class);
                SignUp.this.startActivity(it);
            }
        });
        this.tSignUp.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.SignUp.2
            /* JADX WARN: Type inference failed for: r6v4, types: [com.shoeARstore.SignUp$2$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (SignUp.this.cSozlesme.isChecked()) {
                    final String fname = SignUp.this.eFname.getText().toString();
                    final String lname = SignUp.this.eLname.getText().toString();
                    String mail = SignUp.this.eMail.getText().toString();
                    String pass = SignUp.this.ePass.getText().toString();
                    String dlnumber = SignUp.this.drugnumber.getText().toString();
                    if (!fname.equals("") && !lname.equals("") && !pass.equals("")) {
                        if (!isValidLicense(dlnumber)) {
                            SignUp.this.uyari("Invalid Drug License Number");
                            return;
                        } else {
                            new getData(SignUp.this) { // from class: com.shoeARstore.SignUp.2.1
                                /* JADX INFO: Access modifiers changed from: protected */
                                @Override // com.shoeARstore.getData, android.os.AsyncTask
                                public String doInBackground(String... params) {
                                    try {
                                        String login_url = params[0];
                                        String name = params[1];
                                        String lname2 = params[2];
                                        String mail2 = params[3];
                                        String sifre = params[4];
                                        String drug = params[5];
                                        String post_data = URLEncoder.encode("First_Name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("Last_Name", "UTF-8") + "=" + URLEncoder.encode(lname2, "UTF-8") + "&" + URLEncoder.encode("E_Mail", "UTF-8") + "=" + URLEncoder.encode(mail2, "UTF-8") + "&" + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(sifre, "UTF-8") + "&" + URLEncoder.encode("Druglicense", "UTF-8") + "=" + URLEncoder.encode(drug, "UTF-8");
                                        URL url = new URL(login_url);
                                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                                        httpURLConnection.setRequestMethod("POST");
                                        httpURLConnection.setDoOutput(true);
                                        httpURLConnection.setDoInput(true);
                                        OutputStream outputStream = httpURLConnection.getOutputStream();
                                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                                        bufferedWriter.write(post_data);
                                        bufferedWriter.flush();
                                        bufferedWriter.close();
                                        outputStream.close();
                                        InputStream inStream = httpURLConnection.getInputStream();
                                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream, "iso-8859-9"));
                                        String result = "";
                                        while (true) {
                                            String line = bufferedReader.readLine();
                                            if (line != null) {
                                                result = result + line;
                                            } else {
                                                bufferedReader.close();
                                                inStream.close();
                                                httpURLConnection.disconnect();
                                                return result;
                                            }
                                        }
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        return null;
                                    } catch (MalformedURLException e2) {
                                        e2.printStackTrace();
                                        return null;
                                    } catch (ProtocolException e3) {
                                        e3.printStackTrace();
                                        return null;
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                        return null;
                                    }
                                }

                                /* JADX INFO: Access modifiers changed from: protected */
                                @Override // android.os.AsyncTask
                                public void onPostExecute(String s) {
                                    if (s != null) {
                                        try {
                                            JSONArray jsonArray = new JSONArray(s);
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                if (jsonObject.getString(NotificationCompat.CATEGORY_STATUS).equals("1")) {
                                                    SignUp.this.girisYap(fname + lname, jsonObject.getString("Id"));
                                                }
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    this.dialog.dismiss();
                                    super.onPostExecute((AnonymousClass1) s);
                                }
                            }.execute(new String[]{MainActivity.server_url + "membership.php", fname, lname, mail, pass, dlnumber});
                            return;
                        }
                    }
                    SignUp.this.uyari("Please Do Not Leave Blank Space!!!");
                    return;
                }
                Toast.makeText(SignUp.this, "Accept the Terms of Use!!!", 0).show();
            }

            private boolean isValidLicense(String dlnumber) {
                Pattern regex1 = Pattern.compile("[A-Za-z]{2}\\d{2}-\\d{6}");
                Pattern regex2 = Pattern.compile("MHNZ2-[0-9]+-\\d{6}");
                Matcher matcher1 = regex1.matcher(dlnumber);
                Matcher matcher2 = regex2.matcher(dlnumber);
                return matcher1.matches() || matcher2.matches();
            }
        });
    }

    public void uyari(String alert) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Incorrect operation");
        builder.setMessage(alert);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: com.shoeARstore.SignUp.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void girisYap(String firstName, String gelenid) {
        SharedPreferences sharedPreferences = getSharedPreferences("login", 0);
        this.sharedPreferences = sharedPreferences;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", firstName);
        editor.putString("id", gelenid);
        editor.commit();
        Toast.makeText(this, "Registration Successful!!!", 0).show();
        Intent gec = new Intent(this, HomeFragment.class);
        startActivity(gec);
    }
}