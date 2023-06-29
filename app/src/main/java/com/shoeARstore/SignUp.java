package com.shoeARstore;

import android.app.AlertDialog;
import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

import static com.shoeARstore.HomeFragment.MyPREFERENCES;
import static com.shoeARstore.HomeFragment.id;
import static com.shoeARstore.HomeFragment.name;

import androidx.appcompat.app.AppCompatActivity;


public class SignUp extends AppCompatActivity {
    TextView tSignin,tSignUp;
    EditText eFname,eLname,eMail,ePass,drugnumber;
    CheckBox cSozlesme;

    private static final String PASSWORD_PATTERN = "^(?=.*[@#])(?=.*[0-9])(?=.*[A-Z]).{8,}$";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        tSignin = (TextView) findViewById(R.id.signin);
        tSignUp=(TextView) findViewById(R.id.signin1);
        eFname=(EditText) findViewById(R.id.fname);
        eLname=(EditText) findViewById(R.id.lname);
        eMail=(EditText) findViewById(R.id.email1);
        ePass=(EditText) findViewById(R.id.password);
        drugnumber=(EditText) findViewById(R.id.druglicense);
        cSozlesme=(CheckBox) findViewById(R.id.sozlesme);

        tSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignUp.this, SignIn.class);
                startActivity(it);
            }
        });

        tSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cSozlesme.isChecked()) {
                    final String fname = eFname.getText().toString();
                    final String lname = eLname.getText().toString();
                    String mail = eMail.getText().toString();
                    String pass = ePass.getText().toString();
                    String dlnumber = drugnumber.getText().toString();
                    //if (isValidEmail(mail) != true) {
                    //    uyari("Check Your Email Address!!!");
                    // } else {
                    if (isValidEmail(mail) && isValidPassword(pass) && isValidLicense(dlnumber)) {
                        new getData(SignUp.this) {
                            @Override
                            protected String doInBackground(String... params) {
                                URL url;
                                try {
                                    String post_data = null;
                                    String login_url = params[0];
                                    String name = params[1];
                                    String lname = params[2];
                                    String mail = params[3];
                                    String sifre = params[4];
                                    String drug = params[5];
                                    post_data = URLEncoder.encode("First_Name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                                            URLEncoder.encode("Last_Name", "UTF-8") + "=" + URLEncoder.encode(lname, "UTF-8") + "&" +
                                            URLEncoder.encode("E_Mail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8") + "&" +
                                            URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(sifre, "UTF-8") + "&" +
                                            URLEncoder.encode("Druglicense", "UTF-8") + "=" + URLEncoder.encode(drug, "UTF-8");
                                    url = new URL(login_url);
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
                                    String line;
                                    while ((line = bufferedReader.readLine()) != null) {
                                        result += line;
                                    }
                                    bufferedReader.close();
                                    inStream.close();
                                    httpURLConnection.disconnect();
                                    return result;
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                } catch (ProtocolException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                if (s != null) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(s);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            if (jsonObject.getString("status").equals("1")) {
                                                girisYap(fname , jsonObject.getString("Id"));
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                dialog.dismiss();
                                super.onPostExecute(s);
                            }
                        }.execute(MainActivity.server_url + "membership.php", fname, lname, mail, pass, dlnumber);
                    } else {
                        if (!isValidEmail(mail)) {
                            uyari("Check Your Email Address!!!");
                        } else if (!isValidPassword(pass)) {
                            uyari("Invalid Password! Password must be 8 characters long and contain at least 1 uppercase letter, 1 number, and 1 special character (@#).");
                        } else if (!isValidLicense(dlnumber)) {
                            uyari("Invalid Drug License Number");
                        }
                    }
                } else {
                    Toast.makeText(SignUp.this, "Accept the Terms of Use!!!", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean isValidLicense(String dlnumber) {
                String pattern1 = "[A-Za-z]{2}\\d{2}-\\d{6}"; // Pattern 1: Two letters followed by two digits, a hyphen, and six digits
                String pattern2 = "MHNZ2-[0-9]+-\\d{6}"; // Pattern 2: MHNZ2- followed by one or more digits, a hyphen, and six digits

                Pattern regex1 = Pattern.compile(pattern1);
                Pattern regex2 = Pattern.compile(pattern2);

                Matcher matcher1 = regex1.matcher(dlnumber);
                Matcher matcher2 = regex2.matcher(dlnumber);

                return matcher1.matches() || matcher2.matches();
            }
        });
    }

    private boolean isValidPassword(String pass) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public void uyari(String alert) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
        builder.setTitle("Incorrect operation");
        builder.setMessage(alert);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            String email = target.toString();
            String[] allowedDomains = {"@gmail.com", "@yahoo.com"};

            for (String domain : allowedDomains) {
                if (email.endsWith(domain)) {
                    return true;
                }
            }

            return false;
        }
    }

    SharedPreferences sharedPreferences;

    private void girisYap(String firstName, String gelenid) {
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, firstName);
        editor.putString(id, gelenid);
        editor.commit();
        Toast.makeText(this, "Registration Successful!!!", Toast.LENGTH_SHORT).show();
        Intent gec = new Intent(SignUp.this, HomeFragment.class);
        startActivity(gec);
    }
}
