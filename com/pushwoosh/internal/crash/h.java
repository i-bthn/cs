package com.pushwoosh.internal.crash;

import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public class h {
    private final String a;
    private String b;
    private String c;
    private int d = 120000;
    private final Map<String, String> e;

    public h(String str) {
        this.a = str;
        this.e = new HashMap();
        this.e.put("User-Agent", "HockeySDK/Android 6.2.8");
    }

    private static String a(Map<String, String> map, String str) throws UnsupportedEncodingException {
        ArrayList arrayList = new ArrayList();
        for (String str2 : map.keySet()) {
            String encode = URLEncoder.encode(str2, str);
            String encode2 = URLEncoder.encode(map.get(str2), str);
            arrayList.add(encode + "=" + encode2);
        }
        return TextUtils.join("&", arrayList);
    }

    public h a(String str) {
        this.c = str;
        return this;
    }

    public h a(String str, String str2) {
        this.e.put(str, str2);
        return this;
    }

    public h a(Map<String, String> map) {
        if (map.size() <= 25) {
            for (String str : map.keySet()) {
                String str2 = map.get(str);
                if (str2 != null && ((long) str2.length()) > 4194304) {
                    throw new IllegalArgumentException("Form field " + str + " size too large: " + str2.length() + " - max allowed: " + 4194304L);
                }
            }
            try {
                String a2 = a(map, Key.STRING_CHARSET_NAME);
                a("Content-Type", "application/x-www-form-urlencoded");
                a(a2);
                return this;
            } catch (UnsupportedEncodingException e2) {
                throw new RuntimeException(e2);
            }
        } else {
            throw new IllegalArgumentException("Fields size too large: " + map.size() + " - max allowed: " + 25);
        }
    }

    public HttpURLConnection a() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a).openConnection();
        httpURLConnection.setConnectTimeout(this.d);
        httpURLConnection.setReadTimeout(this.d);
        if (!TextUtils.isEmpty(this.b)) {
            httpURLConnection.setRequestMethod(this.b);
            if (!TextUtils.isEmpty(this.c) || this.b.equalsIgnoreCase("POST") || this.b.equalsIgnoreCase("PUT")) {
                httpURLConnection.setDoOutput(true);
            }
        }
        for (String str : this.e.keySet()) {
            httpURLConnection.setRequestProperty(str, this.e.get(str));
        }
        if (!TextUtils.isEmpty(this.c)) {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), Key.STRING_CHARSET_NAME));
            bufferedWriter.write(this.c);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        return httpURLConnection;
    }

    public h b(String str) {
        this.b = str;
        return this;
    }
}
