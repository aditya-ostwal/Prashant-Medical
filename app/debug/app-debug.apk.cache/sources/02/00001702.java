package com.shoeARstore;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/* loaded from: classes9.dex */
public class getData extends AsyncTask<String, Void, String> {
    Context c;
    public Dialog dialog;

    public getData(Context c) {
        this.c = c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.AsyncTask
    public String doInBackground(String... params) {
        InputStream is = null;
        String login_url = params[0];
        String tablo = params[1];
        String kosul = params[2];
        try {
            try {
                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("tabload", "UTF-8") + "=" + URLEncoder.encode(tablo, "UTF-8") + "&" + URLEncoder.encode("kosul", "UTF-8") + "=" + URLEncoder.encode(kosul, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    is = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    StringBuffer sb = new StringBuffer();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line + "\n");
                    }
                    String stringBuffer = sb.toString();
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return stringBuffer;
                } catch (MalformedURLException e2) {
                    e2.printStackTrace();
                    if (is != null) {
                        is.close();
                        return null;
                    }
                    return null;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    if (is != null) {
                        is.close();
                        return null;
                    }
                    return null;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
                return null;
            }
        } catch (Throwable th) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        Dialog dialog = new Dialog(this.c);
        this.dialog = dialog;
        dialog.requestWindowFeature(1);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setContentView(com.mtel.shoe.R.layout.progress);
        ((TextView) this.dialog.findViewById(com.mtel.shoe.R.id.textview)).startAnimation(AnimationUtils.loadAnimation(this.c, 17432578));
        this.dialog.show();
        super.onPreExecute();
    }
}